import java.io.*;
import java.util.*;

/**
 *for spmf
 * 
 * 
 * 
 */
public class changeL32CHtest {

    public static void main(String[] args)throws IOException{
        BufferedReader bfr = new BufferedReader(
            new FileReader("../中類分類.csv"));
        BufferedWriter bfw = new BufferedWriter(
            new FileWriter("../association/中類/蘆竹大竹/蘆竹大竹apriCH-L3-201509201608.txt"));
        TreeMap <String, String> map = new TreeMap <String, String>();       
        String line;
        int i = 0, h = 0, k = 0;
        while(( line = bfr.readLine()) != null ){
            String[] data = line.split(",");
            map.put(data[1], data[2].trim());
        } 

        BufferedReader bfr1 = new BufferedReader(
            new FileReader("../association/中類/蘆竹大竹/蘆竹大竹apri-L3-201509201608.txt"));
            
        while(( line = bfr1.readLine()) != null ){
            String[] data = line.split(" ");
            String str = "";
            
            for (String s : data) {
                if (map.containsKey(s)) {
                   str = str + map.get(s) + " ";
                   h++;
                } else {
                    str = str + s + " ";
                    k++; 
                }
            }
            bfw.append(str);
            bfw.newLine();
        }

        bfw.flush();
        bfw.close();
        bfr.close();
        bfr1.close();
        
        System.out.println("done and finish: " + i);
        System.out.println("h:" + h + "   k:" + k);
    }
}