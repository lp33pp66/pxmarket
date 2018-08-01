import java.io.*;
import java.util.*;

/**
 *for spmf
 * 
 * 
 * 
 */
public class change2CHtest {

    public static void main(String[] args)throws IOException{
        for(int i = 1; i < 5; i++){
            BufferedReader bfr = new BufferedReader(
                new FileReader("../產品檔_NEW.csv"));
            BufferedWriter bfw = new BufferedWriter(
                new FileWriter("../RFM_Association/三興店/三興CH2014_" + i + ".csv"));
            TreeMap <String, String> map = new TreeMap <String, String>();       
            String line;
            map.put("1010001", "亞培　亞培心美力１嬰兒奶粉０－");
            //產品
            while(( line = bfr.readLine()) != null ){
                String[] data = line.split(",");
                map.put(data[0], data[1].trim());
            } 
    
            BufferedReader bfr1 = new BufferedReader(
                new FileReader("../RFM_Association/三興店/三興apr2014_" + i + ".txt"));
            bfw.append("Pattern" + "," + "Sup" + "," + "Conf");
            bfw.newLine();    
            while(( line = bfr1.readLine()) != null ){
                String[] data = line.split("#");
                String[] subdata = data[0].split(" ");
                String[] supdata = data[1].split(" ");
                String[] confdata = data[2].split(" ");
                
                String str = "";
                String zero = "0";
    
                for(String s : subdata){
                    if(map.containsKey(s)){
                        str = str + map.get(s) + " ";
                    }else{
                        if(s.matches(" *[0-9]{7}")){
                            s = zero + s;
                            str = str + map.get(s) + " ";
                        }else{
                            str = str + s + " ";
                        }
                    }
                }
                
                
                bfw.append(str.trim() + "," + supdata[1] + "," + confdata[1]);
                bfw.newLine();
            }
    
            bfw.flush();
            bfw.close();
            bfr.close();
            bfr1.close();
            
            System.out.println("done and finish: " + i );
        }
        
    }
}