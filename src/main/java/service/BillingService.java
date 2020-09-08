package service;

import model.exception.LocalizedException;
import model.logger.ExceptionLogger;
import model.subscription.AbstractSubscriptionType;
import model.subscription.SubscriptionType;
import model.user.BusinessUser;


public class BillingService {

    public static void calculateMonthlyBill(BusinessUser user, SubscriptionType subscription){
        try{
            user.getBillingInfo().addNewBill(subscription.calculateBill());
            ((AbstractSubscriptionType) subscription).getSubscriptionInfo().resetSubscriptionUsage();
        }
        catch(LocalizedException localException){
            ExceptionLogger.localizeErrors(localException, user.getUserInfo().getLanguage());
        }
    }

}
