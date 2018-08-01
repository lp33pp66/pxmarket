import java.io.*;
import java.util.*;
import java.text.NumberFormat;
import java.text.ParseException;
/**
 *轉換成中類中文
 * 
 * 
 * 
 */
public class changefrequent2L3CH {

    public static void main(String[] args)throws IOException{
        BufferedReader bfr_M = new BufferedReader(
            new FileReader("../中類分類.csv"));
        BufferedReader bfr_L = new BufferedReader(
            new FileReader("../大類分類.csv"));
        bfr_L.close();
        BufferedReader bfr_A = new BufferedReader(
            new FileReader("../產品檔_NEW.csv"));
        
        TreeMap <String, String> map_M = new TreeMap <String, String>();       
        String line;
        //put M in map(M_No, M_Name)
        while(( line = bfr_M.readLine()) != null ){
            String[] data = line.split(",");
            map_M.put(data[1], data[2].trim());
        } bfr_M.close();

        //put map(Goo_No, M_No)
        TreeMap <String, String> map_A = new TreeMap <String, String>();
        TreeMap <String, String> map_Name = new TreeMap <String, String>();
        while(( line = bfr_A.readLine()) != null ){
            String[] data = line.split(",");
            map_A.put(data[0], data[3].trim());
            map_Name.put(data[0], data[1].trim());
        } bfr_A.close();
        
        for(int i=1;i<5;i++){
            BufferedReader bfr1 = new BufferedReader(
                new FileReader("../RFM_Association/華山店/華山201409201508/華山201409201508_frequent_"+i+".txt"));
        
            BufferedWriter bfw = new BufferedWriter(
                new FileWriter("../RFM_Association/華山店/華山201409201508/華山201409201508_frequent_CH_"+i+".csv"));                 
            bfw.append("Item,Sup\r\n");  
    
            while(( line = bfr1.readLine()) != null ){
                String[] data = line.split("#");
                String[] subdata = data[0].split(" ");
                String[] supdata = data[1].split(" ");
        
                String str = "";
                String zero = "0";
    
                for(String s : subdata){
                    if(map_A.containsKey(s)){
                        str = str + map_M.get(map_A.get(s)) + "---" + map_Name.get(s) +" ";
                    }else{
                        if(s.matches(" *[0-9]{7}")){
                            
                            str = str + map_M.get(map_A.get(s)) + "---" + map_Name.get(s) +" ";
                        }else{
                            str = str + s + " ";
                        }
                    }
                }
                
                int [] sup ;
                sup = new int[5];
                sup[1]=2977;
                sup[2]=46024;
                sup[3]=282236;
                sup[4]=11355;
                double percent = Double.parseDouble(supdata[1]) / sup[i];
                //获取格式化对象
                NumberFormat nt = NumberFormat.getPercentInstance();
                //设置百分数精确度2即保留两位小数
                nt.setMinimumFractionDigits(3);
                
                bfw.append(str.trim() + "," + nt.format(percent));
                bfw.newLine();
            }
            bfw.flush();
            bfw.close();
            bfr1.close();    
        }
    System.out.println("re");    
    }
    
}
