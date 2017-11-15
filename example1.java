import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    Vector v_in=new Vector();
    int res=0;

    while ((line = in.readLine()) != null) {
      List<String> elephantList = Arrays.asList(line.split(","));
      
      
      for(int i=0;i<elephantList.size();i++){
        
        int temp = Integer.parseInt(elephantList.get(i).replaceAll("\\s+",""));
        v_in.addElement(temp);
      }

      for(int start=0;start<v_in.size();start++){
        for(int end=0;end<v_in.size();end++){
          if(start<=end){
            int acc=0;
            for(int i=start;i<=end;i++){
              int a=(Integer)v_in.elementAt(i);
              acc+= a  ;
            
            }
            if(acc>=res){
              res=acc;
            }
          }
        }
      }
      System.out.println(res);
    }
  }
}
