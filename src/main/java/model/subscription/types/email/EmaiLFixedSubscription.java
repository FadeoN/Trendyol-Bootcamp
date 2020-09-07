package model.subscription.types.email;

import model.subscription.SubscriptionFixedType;
import model.subscription.info.SubscriptionInfo;

public class EmaiLFixedSubscription extends SubscriptionFixedType implements EmailSubscription{

    public EmaiLFixedSubscription(){
        setSubscriptionInfo(new SubscriptionInfo(20., 1000));
    }

}
