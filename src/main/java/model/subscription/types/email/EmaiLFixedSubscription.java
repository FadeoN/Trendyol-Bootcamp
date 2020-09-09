package model.subscription.types.email;

import model.subscription.SubscriptionFixedType;
import model.subscription.info.SubscriptionInfo;

public class EmaiLFixedSubscription extends SubscriptionFixedType implements EmailSubscription{

    public EmaiLFixedSubscription(long id){
        setSubscriptionInfo(new SubscriptionInfo(id,20., 1000));
    }

}
