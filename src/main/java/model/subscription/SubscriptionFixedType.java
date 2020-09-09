package model.subscription;

import model.exception.QuotaExceededException;

public class SubscriptionFixedType extends AbstractSubscriptionType {


    public void renewExceededSubscription() {
        subscriptionInfo.renewSubscription();

    }

    @Override
    public void incrementUsageCount() {
        subscriptionInfo.incrementUsageCount();
        if(subscriptionInfo.isQuotaReached()){
            renewExceededSubscription();
            throw new QuotaExceededException();
        }
    }

    @Override
    public Double calculateBill() {
        return (subscriptionInfo.getAdditionalQuotaCount() + 1) * subscriptionInfo.getSubscriptionPrice();
    }


}
