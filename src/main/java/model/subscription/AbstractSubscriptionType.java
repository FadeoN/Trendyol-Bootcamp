package model.subscription;

import exception.QuotaExceededException;
import model.subscription.info.SubscriptionInfo;

public abstract class AbstractSubscriptionType {

    protected SubscriptionInfo subscriptionInfo;

    public abstract Double calculateBill();

    public void incrementUsageCount() {
        subscriptionInfo.incrementUsageCount();
        if(subscriptionInfo.isQuotaReached()){
            throw new QuotaExceededException();
        }
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

}
