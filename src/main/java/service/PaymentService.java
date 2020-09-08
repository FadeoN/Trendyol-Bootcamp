package service;

import model.exception.LocalizedException;
import model.logger.ExceptionLogger;
import model.user.BusinessUser;

public class PaymentService {

    public static void payDebt(BusinessUser user, Double paymentAmount){
        try{
            user.getBillingInfo().payDebt(paymentAmount);
        }
        catch(LocalizedException localException){
            ExceptionLogger.localizeErrors(localException, user.getUserInfo().getLanguage());
        }
    }

}
