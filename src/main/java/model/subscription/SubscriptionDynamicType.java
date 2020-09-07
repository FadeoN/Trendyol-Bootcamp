package model.subscription;

public class SubscriptionDynamicType extends AbstractSubscriptionType{

    private Double exceededPricing;

    @Override
    public Double calculateBill() {

        return subscriptionInfo.getSubscriptionPrice()  + this.calculateExceededPricing();
    }

    public Double calculateExceededPricing() {
        Integer exceededUsageCount = 0;
        if(subscriptionInfo.isQuotaReached()){
            exceededUsageCount = subscriptionInfo.getUsageCount() - subscriptionInfo.getUsageQuota();
        }
        return  exceededUsageCount * getExceededPricing();
    }

    public Double getExceededPricing() {
        return exceededPricing;
    }

    public void setExceededPricing(Double exceededPricing) {
        this.exceededPricing = exceededPricing;
    }


}
