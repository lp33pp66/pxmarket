import java.io.*;
import java.util.*;

/**
 * schema : invoiceID, T_Time, Deal_No, VIP_No, Goo_No
 * 處理modeler的檔案成ＳＰＭＦ
 * modeler 合併，清除
 * 
 *
 */
public class spmf_for_rfm{

    public static void main(String[] args)throws IOException{
        for(int i = 1; i < 5; i++){
            BufferedReader bfr = new BufferedReader(
                new FileReader("../RFM_Association/士林華榮店/士林華榮201309201408/士林華榮201309201408_" + i + ".csv"));
            /*BufferedReader bfr1 = new BufferedReader(
                new FileReader("../每間店VIPASSOCIATION/松花-201309201408.csv"));*/
            
            HashMap <String, String> map = new HashMap <String,String>();
            
            HashSet <String> set_invoiceID = new HashSet<String>();
            HashSet <String> set_VIP = new HashSet<String>();
            int item_count = 0;
            String line = "";

            
            while (( line = bfr.readLine()) != null ) {
                
                item_count++;
                String[] data = line.split(",");
                set_invoiceID.add(data[0].trim());
                set_VIP.add(data[3].trim());
                
                //date_vip primary key
                String key_str = data[1].substring(0, 8) + "_" + data[3];
                if (map.containsKey(key_str)) {
                    map.put(key_str, map.get(key_str) + " " + data[4]);
                } else {
                    map.put(key_str, data[4]);
                }
            }
            
            BufferedWriter bfw = new BufferedWriter(
                new FileWriter("../RFM_Association/士林華榮店/士林華榮201309201408/士林華榮201309201408_" + i + ".txt"));

            for (String key : map.keySet()) {
                String data[] = map.get(key).split(" ");
                TreeSet<String> set = new TreeSet<String>();
                for(String s : data){
                    set.add(s);
                }
                for(String s : set){
                    bfw.append(s + " ");
                }
                bfw.newLine();
            }

            bfr.close();
            //bfr1.close();
            bfw.flush();
            bfw.close();
            System.out.println("士林華榮201309201408 " + i + "\n"
            + "交易筆數：  sup[" + i + "]=" + map.size() + ";\n" 
            + "總發票數：" + set_invoiceID.size() + "\n" 
            + "總會員數：" + set_VIP.size() + "\n"    
            + "總商品數：" + item_count + "\n" );
        }    
    }
}