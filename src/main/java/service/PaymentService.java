package service;

import model.user.BusinessUser;

public class PaymentService {

    public static void payDebt(BusinessUser user, Double paymentAmount){

        user.getBillingInfo().payDebt(paymentAmount);

    }

}
