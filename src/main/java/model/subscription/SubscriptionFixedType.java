package model.subscription;

import model.subscription.info.SubscriptionInfo;

public class SubscriptionFixedType extends AbstractSubscriptionType {

    private SubscriptionInfo subscriptionInfo;

    public void renewExceededSubscription() {
        subscriptionInfo.renewSubscription();

    }

    @Override
    public Double calculateBill() {
        return subscriptionInfo.getAdditionalQuotaCount() * subscriptionInfo.getSubscriptionPrice();
    }


}
