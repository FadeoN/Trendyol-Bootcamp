package service;

import exception.LocalizedException;
import logger.ExceptionLogger;
import model.subscription.SubscriptionType;
import model.user.BusinessUser;

public class PaymentService {

    public static void payDebt(BusinessUser user, SubscriptionType subs){
        try{
            user.getBillingInfo().payDebt(subs);
        }
        catch(LocalizedException localException){
            ExceptionLogger.localizeErrors(localException, user.getUserInfo().getLanguage());
        }
    }

}
