import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


class Asset{
  
  private String name;
  private int Qty;
  private double price;
  private double percentNav;
  
  public Asset(String in_name,int in_Qty,double in_price){
    name = in_name;
    Qty = in_Qty;
    price = in_price;
  }
  
  public String getName(){
    return name;
  }
  
  public int getQty(){
    return Qty;
  }
  
  public double getPrice(){
    return price;
  }
  
  public double getPercentNav(){
    return percentNav;
  }
  public void setPercentNav(double percentNavIn){
    percentNav = percentNavIn;
  }
}


public class Main {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    ArrayList<Asset> p = new ArrayList();
    ArrayList<Asset> b = new ArrayList();
    
    
    while ((line = in.readLine()) != null) {
      
      List<String> InputStringList = Arrays.asList(line.split("\\|"));
      String t_port = InputStringList.get(0);
      String t_bench = InputStringList.get(1);
      
      List<String> portlist_contains_name = Arrays.asList(t_port.split("PORT:"));
      List<String> benchlist_contains_name = Arrays.asList(t_bench.split("BENCH:"));
      
      String port = portlist_contains_name.get(1);
      String bench = benchlist_contains_name.get(1);

      List<String> portlist = Arrays.asList(port.split(";"));
      List<String> benchlist = Arrays.asList(bench.split(";"));
 
      for(int i=0;i<portlist.size();i++){
        List<String> tmp = Arrays.asList(portlist.get(i).split(","));
        Asset tmpAss = new Asset(tmp.get(0), Integer.parseInt(tmp.get(1)), Double.parseDouble(tmp.get(2)));
        p.add(tmpAss);

      }
      
      for(int i=0;i<benchlist.size();i++){
        List<String> tmp = Arrays.asList(benchlist.get(i).split(","));
        Asset tmpAss = new Asset(tmp.get(0), Integer.parseInt(tmp.get(1)), Double.parseDouble(tmp.get(2)));
        b.add(tmpAss);

      }
      
      
      Collections.sort(p, new Comparator<Asset>() {
        public int compare(Asset a1, Asset a2) {
        return a1.getName().compareTo(a2.getName());
        }
      });
      
      Collections.sort(b, new Comparator<Asset>() {
        public int compare(Asset a1, Asset a2) {
        return a1.getName().compareTo(a2.getName());
        }
      });
      
      double acc = 0;
      for(int i=0;i<p.size();i++){
        acc += p.get(i).getQty()*p.get(i).getPrice();
      }
      double PNav = acc;
      
      acc = 0;
      for(int i=0;i<b.size();i++){
        acc += b.get(i).getQty()*b.get(i).getPrice();
      }
      double BNav = acc;
      
      //Math.round(monthly_payment*100.0)/100.0
      
      for(int i=0;i<p.size();i++){
        p.get(i).setPercentNav(    (p.get(i).getQty()*p.get(i).getPrice())/PNav     );
      }
      for(int i=0;i<b.size();i++){
        b.get(i).setPercentNav(   (b.get(i).getQty()*b.get(i).getPrice())/BNav   );
      }
      
      for(int i=0;i<p.size();i++){
        System.out.println(p.get(i).getName() + p.get(i).getPercentNav());
      }
      for(int i=0;i<b.size();i++){
        System.out.println(b.get(i).getName() + b.get(i).getPercentNav());
      }
      
      int leftp = 0;
      int rightb = 0;
      while(leftp<p.size()&&rightb<b.size()){
        if(   (p.get(leftp).getName().compareTo(b.get(rightb).getName()))>0  ){
          leftp++;
        }else if(  (p.get(leftp).getName().compareTo(b.get(rightb).getName()))<0   ){
          rightb++;
        }else{
          double haha = p.get(leftp).getPercentNav() - b.get(rightb).getPercentNav();
          System.out.println(p.get(leftp).getName() +haha);

        }
      }
      
      
      /*
      for(int i=0;i<p.size();i++){
        boolean same = false;
        int position=0;
        for(int j=0;j<b.size();j++){
          if(p.get(i).getName() == b.get(j).getName()){
            same = true;
            position = j;
          }
        }
        
        
        

        if(same){
          System.out.println(p.get(i).getName() +":"+Math.round((p.get(i).getPercentNav()-b.get(position).getPercentNav())*100.0)/100.0 +"," );

        }else{
          System.out.println(p.get(i).getName() +":"+ Math.round((p.get(i).getPercentNav())*100.0)/100.0 +"," );
        }
        
      }
      */
      
//      System.out.println(p.get(1).getPercentNav());
    }
  }
}
