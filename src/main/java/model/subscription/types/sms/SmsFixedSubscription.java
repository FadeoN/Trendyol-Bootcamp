package model.subscription.types.sms;

import model.subscription.SubscriptionFixedType;
import model.subscription.info.SubscriptionInfo;

public class SmsFixedSubscription extends SubscriptionFixedType implements SmsSubscription{

    public SmsFixedSubscription(long id){
        setSubscriptionInfo(new SubscriptionInfo(id, 20., 1000));
    }

}
