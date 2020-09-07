package model.user;


import model.notification.Notification;
import model.notification.NotificationGroup;
import model.subscription.SubscriptionHub;
import model.billing.BillingInfo;

import java.util.List;

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

    public boolean isBlacklisted(){
        return billingInfo.isBlacklisted();
    }

}
