package service;

import model.notification.Email;
import model.subscription.AbstractSubscriptionType;
import model.subscription.SubscriptionType;
import model.subscription.info.SubscriptionInfo;
import model.subscription.types.email.EmaiLFixedSubscription;
import model.subscription.types.email.EmailDynamicSubscription;
import model.subscription.types.sms.SmsDynamicSubscription;
import model.subscription.types.sms.SmsFixedSubscription;
import model.user.BusinessUser;

import java.util.HashMap;

public class SubscriptionService {

    HashMap<Long, SubscriptionType> subscriptions = new HashMap<>();
    private long groupID = 0;

    public void subscribeFixedSmsPackage(BusinessUser businessUser){
        SubscriptionType subs = new SmsFixedSubscription(groupID);

        subscriptions.put(groupID, subs);
        businessUser.setSubscription(subs);
        groupID += 1;

    }

    public void subscribeFixedEmailPackage(BusinessUser businessUser){
        SubscriptionType subs = new EmaiLFixedSubscription(groupID);

        subscriptions.put(groupID, subs);
        businessUser.setSubscription(subs);
        groupID += 1;

    }

    public void subscribeDynamicSmsPackage(BusinessUser businessUser){
        SubscriptionType subs = new SmsDynamicSubscription(groupID,0.10);

        subscriptions.put(groupID, subs);
        businessUser.setSubscription(subs);
        groupID += 1;

    }

    public void subscribeDynamicEmailPackage(BusinessUser businessUser){
        SubscriptionType subs = new EmailDynamicSubscription(groupID,0.10);

        subscriptions.put(groupID, subs);
        businessUser.setSubscription(subs);
        groupID += 1;

    }


    public SubscriptionType getSubscriptionType(long id){
        return subscriptions.get(id);
    }

    public AbstractSubscriptionType getAbstractSubscriptionType(long id){
        return (AbstractSubscriptionType) subscriptions.get(id);
    }

    public SubscriptionInfo getSubscriptionInfo(long id){
        return ((AbstractSubscriptionType) subscriptions.get(id)).getSubscriptionInfo();
    }

}
