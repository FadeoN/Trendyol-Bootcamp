package model.billing;


import model.subscription.AbstractSubscriptionType;
import model.subscription.embeddedkey.SubscriptionKey;
import model.subscription.SubscriptionType;
import model.subscription.info.SubscriptionInfo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;

public class BillingInfo {

    private HashMap<SubscriptionInfo, Double> billingDetail = new HashMap<SubscriptionInfo, Double>(); // Billing Date, billing amount
    private SubscriptionKey lastBilling; // Normally there is no need for this variable if db is used in a sorted way.
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
    public void payDebt(SubscriptionType subscription){
        this.billingDebt = billingDebt - billingDetail.get(((AbstractSubscriptionType) subscription).getSubscriptionInfo());
        this.billingDetail.put(((AbstractSubscriptionType) subscription).getSubscriptionInfo(), (double) 0);
        this.lastPayment = LocalDate.now();
    }
    public Double getDebt(){
        return billingDebt;
    }

    public Double getLastBillingAmount(){

        return billingDetail.get(new SubscriptionInfo(lastBilling));
    }

    public void addNewBill(SubscriptionType subscription){
        SubscriptionInfo subscriptionInfo = ((AbstractSubscriptionType) subscription).getSubscriptionInfo();
        /*Check last billing*/
        Double billingAmount = subscription.calculateBill();
        lastBilling = subscriptionInfo.getSubscriptionKey();
        billingDetail.put(subscriptionInfo, billingAmount);
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
