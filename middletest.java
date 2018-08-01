import java.io.*;
import java.util.*;

/**
 *
 * 
 * 
 * 
 */
public class middletest {

    public static void main(String[] args)throws IOException{
        BufferedReader bfr = new BufferedReader(
            new FileReader("../association/每間店/文山萬年/文山萬年-201509201608.txt"));
        
        BufferedReader bfr1 = new BufferedReader(
            new FileReader("../產品檔_NEW.csv"));
        BufferedWriter bfw = new BufferedWriter(
            new FileWriter("../association/中類/文山萬年/文山萬年-L3-201509201608.txt"));
        
        TreeMap <Integer, String> map = new TreeMap <Integer, String>();       
        String line;
        int i = 0;
        int j = 0;
        int k  = 0;
        TreeMap <String, String> cmap = new TreeMap <String, String>();
        
        while(( line = bfr1.readLine()) != null ){
            String[] data = line.split(",");
            cmap.put(data[0], data[3]);
        }
        cmap.put("01010001", "0101");

        while(( line = bfr.readLine()) != null ){
            String[] data = line.split(" ");
            String str = "";
            
            for(String s : data){
                k++;
                if(cmap.containsKey(s)){
                    str = str + cmap.get(s) + " ";
                    i++;
                }else{
                    str = str + "++++" + " ";
                    j++;
                }
            }
            bfw.append(str);
            bfw.newLine();
        }
        
 
 /*       for (String key : map.keySet()) {
            bfw.append(map.get(key));
            bfw.newLine();    
        }*/
/*
        for (String key : cmap.keySet()) {
            bfw.append(key + " : " + cmap.get(key));
            bfw.newLine();
        }
*/        bfr.close();
        bfr1.close();
        bfw.flush();
        bfw.close();
        System.out.println("done and finish: " + i);
        System.out.println("i: " + i);
        System.out.println("j: " + j);
        System.out.println("k: " + k);
    }
}