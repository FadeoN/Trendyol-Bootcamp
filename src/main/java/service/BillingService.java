package service;

import model.subscription.SubscriptionType;
import model.user.BusinessUser;


public class BillingService {

    public static void calculateMonthlyBill(BusinessUser user, SubscriptionType subscription){

        user.getBillingInfo().addNewBill(subscription.calculateBill());

    }

}
