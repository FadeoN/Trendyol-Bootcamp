package model.subscription;

public interface SubscriptionType {

    void incrementUsageCount();
    Double calculateBill();

}
