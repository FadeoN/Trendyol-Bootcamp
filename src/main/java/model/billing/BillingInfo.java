package model.billing;


import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;

public class BillingInfo {

    private HashMap<LocalDate, Double> billingDetail = new HashMap<LocalDate, Double>(); // Billing Date, billing amount
    private LocalDate lastPayment;
    private Double billingDebt;

    private static final double blacklistDays = 60.;


    public BillingInfo() {
        this.billingDebt = 0.;
        this.lastPayment = LocalDate.now();
    }

   /*
   * TODO: Remaning amount must be returned
   * */
    public void payDebt(Double paymentAmount){
        this.billingDebt = Math.max(billingDebt - paymentAmount, 0);
        this.lastPayment = LocalDate.now();
    }

    public void addNewBill(Double billingAmount){
        /*Check last billing*/
        billingDetail.put(LocalDate.now(), billingAmount);
        billingDebt += billingAmount;
    }

    public boolean isBlacklisted(){
        return Duration.between(LocalDate.now().atStartOfDay(), lastPayment.atStartOfDay()).toDays() >= blacklistDays;
    }

    @Override
    public String toString(){
        return "Current Debt: " + billingDebt +  " Last Payment: " + lastPayment;
    }
}
