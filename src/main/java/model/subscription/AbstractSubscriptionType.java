package model.subscription;

import model.exception.QuotaExceededException;
import model.subscription.info.SubscriptionInfo;

public abstract class AbstractSubscriptionType {

    protected SubscriptionInfo subscriptionInfo;

    abstract Double calculateBill();

    public void incrementUsageCount() {
        if(subscriptionInfo.isQuotaReached()){
            throw new QuotaExceededException();
        }
        subscriptionInfo.incrementUsageCount();
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

}
