import model.subscription.types.email.EmailDynamicSubscription;
import model.user.BusinessUser;
import model.subscription.types.sms.SmsDynamicSubscription;
import model.subscription.SubscriptionType;

public class TrendyolApplication {
    public static void main(String[] args) {
        BusinessUser user = new BusinessUser();
        user.setFullName("Name Surname");
        user.setId(1);

        SubscriptionType subs = new SmsDynamicSubscription(0.10);
        user.setSubscription(subs);

        for(int i =0; i < 5000; i++){
            subs.incrementUsageCount();

        }
        System.out.println(subs.calculateBill());

    }
}
