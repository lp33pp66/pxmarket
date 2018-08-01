import java.io.*;
import java.util.*;

/**
 * schema : invoiceID, Deal_No, T_Time, VIP_No, Tol_Amt, Goo_No
 * 處理modeler的檔案成ＳＰＭＦ
 * modeler 合併，清除
 * 
 *
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class spmf_for_avip{

    public static void main(String[] args)throws IOException{
        BufferedReader bfr = new BufferedReader(
            new FileReader("../每間店VIPASSOCIATION/松花-201509201608.csv"));
        BufferedReader bfr1 = new BufferedReader(
            new FileReader("../每間店VIPASSOCIATION/松花-201509201608.csv"));
        //退貨
        HashMap <String, String> map = new HashMap <String,String>();
        HashSet <String> set_back = new HashSet<String>();
        String line = "";
        int i = 0, j=0;

        //記錄退貨 OK  Deal_No__VIP_No__Tol_Amt
        while (( line = bfr.readLine()) != null ) {
            String[] data = line.split(",");
            data[5] = data[5].replace("\"", "").trim();
            data[0] = data[0].replace("\"", "").trim();
            data[1] = data[1].replace("\"", "").trim();
            //data[4] = Float.valueOf(data[4]).floatValue()
            if(data[1].trim().matches(" *9[0-9]{4,}")){
                String str = data[1] + "_" + data[3] + "_" + Float.valueOf(data[4]).floatValue();
                String str1 = Integer.valueOf(data[1]) % 900000 + "_" + data[3] + "_" + Math.abs(Float.valueOf(data[4]).floatValue()) ;
                set_back.add(str);
                set_back.add(str1);
            }
        }
        
        while (( line = bfr1.readLine()) != null ) {
            j++;
            String[] data = line.split(",");
            data[5] = data[5].replace("\"", "").trim();
            data[0] = data[0].replace("\"", "").trim(); 
            data[1] = data[1].replace("\"", "").trim();
            String check_str = data[1] + "_" + data[3] + "_" + Float.valueOf(data[4]).floatValue();
            //date_vip primary key
            String key_str = data[2].substring(0, 8) + "_" + data[3];
            if(set_back.contains(check_str) != true){
                i++;
                if (map.containsKey(key_str)) {
                    map.put(key_str, map.get(key_str) + " " + data[5]);
                } else {
                    map.put(key_str, data[5]);
                }
            }
        }
        
        BufferedWriter bfw = new BufferedWriter(
            new FileWriter("../每間店VIPASSOCIATION/松花店-201509201608.txt"));

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
        bfr1.close();
        bfw.flush();
        bfw.close();
        System.out.println("done and finishhhhh: " + i +" "+ j + " " + map.size());
    }
}