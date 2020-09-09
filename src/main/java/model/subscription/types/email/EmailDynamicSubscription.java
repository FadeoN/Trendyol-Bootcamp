package model.subscription.types.email;

import model.subscription.SubscriptionDynamicType;
import model.subscription.info.SubscriptionInfo;

public class EmailDynamicSubscription extends SubscriptionDynamicType implements EmailSubscription {


    public EmailDynamicSubscription(long id, Double exceededPricing){
        setExceededPricing(exceededPricing);

        setSubscriptionInfo(new SubscriptionInfo(id, 30., 2000));
    }


}
