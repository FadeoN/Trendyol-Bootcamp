package model.user;


import model.subscription.SubscriptionHub;
import model.billing.BillingInfo;

public class BusinessUser extends SubscriptionHub {

    private UserInfo userInfo;
    private BillingInfo billingInfo;

    public BusinessUser(UserInfo userInfo){
        this.userInfo = userInfo;
        billingInfo = new BillingInfo();
    }


    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public boolean isBlacklisted(){
        return billingInfo.isBlacklisted();
    }

}
