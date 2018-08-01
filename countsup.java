import java.io.*;
import java.util.*;

/**
 *for spmf
 * 
 * 
 * 
 */
public class countsup {

    public static void main(String[] args)throws IOException{
        BufferedReader bfr = new BufferedReader(
            new FileReader("../shopname.txt"));       
        String line = "";
        
        while(( line = bfr.readLine()) != null ){
            String shop = line ;
            String[] t = line.split("-");
            int i = 0;
            String Line = "";
            BufferedReader bfr1 = new BufferedReader(
                new FileReader("../每間店VIPASSOCIATION/" + t[0] + "/"+ shop));
            
            while((Line = bfr1.readLine()) != null){
                i++;
            }
            System.out.println(shop + " : " + i );
            bfr1.close();
            
        }
        
        bfr.close();
        System.out.println("done and finish: ");
    }
}