import language.Turkish;
import model.notification.Notification;
import model.notification.Sms;
import model.subscription.AbstractSubscriptionType;
import model.user.BusinessUser;
import model.user.User;
import model.user.UserInfo;
import org.junit.jupiter.api.Test;
import service.BillingService;
import service.NotificationService;
import service.PaymentService;
import service.SubscriptionService;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    public SubscriptionService subscriptionService = new SubscriptionService();

    @Test
    public void it_should_change_debt_to_zero_when_subscription_paid() {
        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeFixedSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());
        PaymentService.payDebt(businessUser, subscriptionService.getSubscriptionType(0));

        assertThat(businessUser.getBillingInfo().getDebt()).isEqualTo(0);

    }
}
