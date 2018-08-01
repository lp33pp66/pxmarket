import java.io.*;
import java.util.*;

/**
 *for spmf
 * 
 * 
 * 
 */
public class test_spmf {

    public static void main(String[] args)throws IOException{
        BufferedReader bfr = new BufferedReader(
            new FileReader("../蘆竹大竹店/蘆竹大竹店-發票明細-201509201608.csv"));
        
        TreeMap <String, String> map = new TreeMap <String, String>();
               
        String line;
        int i = 0;
        while(( line = bfr.readLine()) != null ){
            String[] data = line.split(",");
            //過濾
            if( data[3].trim().matches(" *9[0-9]{4,}")){

            }else if(data[5].trim().length() == 8){
                if (data[5].trim().matches(" *96[0-9]{6}")) {
                    
                } else {
                    if(data[5].trim().matches(" *[0-9]{8}")){
                        String k = data[2] + "_" + data[3];
                        if(map.containsKey(k)){
                            map.put(k, map.get(k)+ " " + data[5].trim() );
                        }else{
                            map.put(k, data[5].trim());
                        }
                    }   
                }
            }

        }
        BufferedWriter bfw = new BufferedWriter(
            new FileWriter("../association/每間店改/蘆竹大竹/蘆竹大竹-201509201608.txt"));
 
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
        bfw.flush();
        bfw.close();
        System.out.println("done and finish: " + i);
    }
}