package model.subscription;

import model.subscription.info.SubscriptionInfo;

public abstract class AbstractSubscriptionType {

    protected SubscriptionInfo subscriptionInfo;

    abstract Double calculateBill();

    public void incrementUsageCount() {
        if(subscriptionInfo.isQuotaReached()){
            // TODO: raise Informative Exception
            System.out.println("Package Quota reached, new payment method .");
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
