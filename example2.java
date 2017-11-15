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
    while ((line = in.readLine()) != null) {
      
      List<String> InputStringList = Arrays.asList(line.split("~"));
      double total_amount = Double.parseDouble(InputStringList.get(0));
      double total_years = Double.parseDouble(InputStringList.get(1));
      double annual_interest_rate = Double.parseDouble(InputStringList.get(2));
      double down_payment = Double.parseDouble(InputStringList.get(3));

      double total_months = total_years*12;
      double monthly_interest_rate = annual_interest_rate/(100*12);
      double monthly_payment = (monthly_interest_rate*(total_amount-down_payment))/(1-Math.pow(1+monthly_interest_rate,-(total_years*12)));
      double total_payback = monthly_payment*total_months;
      double interst_payment = total_payback-total_amount+down_payment;
      
      double round_mp_to_2dp = Math.round(monthly_payment*100.0)/100.0;
      long round_ip = Math.round(interst_payment);
      System.out.println("$"+round_mp_to_2dp+"~$"+round_ip);
    }
  }
}
