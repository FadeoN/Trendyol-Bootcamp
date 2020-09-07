package model.subscription.types.sms;

import model.subscription.SubscriptionDynamicType;
import model.subscription.info.SubscriptionInfo;

public class SmsDynamicSubscription extends SubscriptionDynamicType implements SmsSubscription{

    public SmsDynamicSubscription(Double exceededPricing){
        setExceededPricing(exceededPricing);

        setSubscriptionInfo(new SubscriptionInfo(30., 2000));
    }


}
