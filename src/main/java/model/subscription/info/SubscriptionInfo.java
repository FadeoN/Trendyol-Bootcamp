package model.subscription.info;

import model.subscription.embeddedkey.SubscriptionKey;

import java.time.LocalDate;
import java.util.Objects;

public class SubscriptionInfo {

    private SubscriptionKey subscriptionKey;
    private Integer additionalQuotaCount; // TODO: Better name?
    private Double subscriptionPrice;
    private Integer usageCount;
    private Integer usageQuota;


    public SubscriptionInfo(SubscriptionKey subscriptionKey, Integer additionalQuotaCount, Double subscriptionPrice, Integer usageCount, Integer usageQuota) {
        this.subscriptionKey = subscriptionKey;
        this.additionalQuotaCount = additionalQuotaCount;
        this.subscriptionPrice = subscriptionPrice;
        this.usageCount = usageCount;
        this.usageQuota = usageQuota;
    }

    public SubscriptionInfo(long id, Double subscriptionPrice, Integer usageQuota){
        this.subscriptionPrice = subscriptionPrice;
        this.usageQuota = usageQuota;
        this.subscriptionKey = new SubscriptionKey(id);
        this.resetSubscriptionUsage();
    }

    public SubscriptionInfo(SubscriptionKey subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return (SubscriptionInfo) this.clone();

    }

    public void resetSubscriptionUsage(){
        this.usageCount = 0;
        this.additionalQuotaCount = 0;
        this.subscriptionKey.setStartingDate(LocalDate.now());
    }

    public void renewSubscription(){
        this.additionalQuotaCount += 1;
    }

    public boolean isQuotaReached(){
        return usageCount > usageQuota * (additionalQuotaCount+ 1);
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

    public SubscriptionKey getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(SubscriptionKey subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionInfo)) return false;
        SubscriptionInfo that = (SubscriptionInfo) o;
        return Objects.equals(getSubscriptionKey(), that.getSubscriptionKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubscriptionKey());
    }

    @Override
    public Object clone() {
        SubscriptionInfo info = null;
        try {
            info = (SubscriptionInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            info = new SubscriptionInfo(
                    this.getSubscriptionKey(), this.getAdditionalQuotaCount(), this.getSubscriptionPrice(), getUsageCount(), getUsageQuota());
        }

        return info;
    }
}
