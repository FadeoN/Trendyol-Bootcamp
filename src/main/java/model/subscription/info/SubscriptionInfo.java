package model.subscription.info;

import model.billing.BillingInfo;

public class SubscriptionInfo {

    private BillingInfo billingInfo;
    private Integer additionalQuotaCount; // TODO: Better name?
    private Double subscriptionPrice;
    private Integer usageCount;
    private Integer usageQuota;


    public SubscriptionInfo(Double subscriptionPrice, Integer usageQuota){
        this.subscriptionPrice = subscriptionPrice;
        this.usageQuota = usageQuota;
        this.usageCount = 0;
        this.additionalQuotaCount = 1;
        this.billingInfo = new BillingInfo();

    }

    public void renewSubscription(){
        this.additionalQuotaCount += 1;
    }

    public boolean isQuotaReached(){
        return usageCount >= usageQuota * (additionalQuotaCount+ 1);
    }

    public void incrementUsageCount(){
            this.usageCount += 1;
    }

    public Integer getAdditionalQuotaCount() {
        return additionalQuotaCount;
    }

    public Double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public Integer getUsageQuota() {
        return usageQuota;
    }

    public void setUsageQuota(Integer usageQuota) {
        this.usageQuota = usageQuota;
    }
}
