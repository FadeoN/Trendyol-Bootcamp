package model.subscription.info;

public class SubscriptionInfo {

    private Integer subscriptionCount; // TODO: Better name?
    private Double subscriptionPrice;
    private Integer usageCount;
    private Integer usageQuota;


    public SubscriptionInfo(Double subscriptionPrice, Integer usageQuota){
        this.subscriptionPrice = subscriptionPrice;
        this.usageQuota = usageQuota;
        this.usageCount = 0;
        this.subscriptionCount = 1;

    }

    public void renewSubscription(){
        this.subscriptionCount += 1;
    }

    public boolean isQuotaReached(){
        return usageCount >= usageQuota * subscriptionCount;
    }

    public void incrementUsageCount(){
            this.usageCount += 1;
    }

    public Integer getSubscriptionCount() {
        return subscriptionCount;
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
