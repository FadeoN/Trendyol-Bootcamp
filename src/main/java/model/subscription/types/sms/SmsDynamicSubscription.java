package model.subscription.types.sms;

import model.subscription.SubscriptionDynamicType;
import model.subscription.info.SubscriptionInfo;

public class SmsDynamicSubscription extends SubscriptionDynamicType implements SmsSubscription{

    public SmsDynamicSubscription(long id, Double exceededPricing){
        setExceededPricing(exceededPricing);

        setSubscriptionInfo(new SubscriptionInfo(id, 30., 2000));
    }


}
