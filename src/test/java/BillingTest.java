import model.language.Turkish;
import model.notification.Notification;
import model.notification.Sms;
import model.subscription.AbstractSubscriptionType;
import model.subscription.types.sms.SmsFixedSubscription;
import model.user.BusinessUser;
import model.user.User;
import model.user.UserInfo;
import org.junit.jupiter.api.Test;
import service.BillingService;
import service.NotificationService;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingTest {

    @Test
    public void it_should_return_last_bill_fixed_subscription() {
        SmsFixedSubscription subs = new SmsFixedSubscription();

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        assertThat(businessUser.getBillingInfo().getLastBillingAmount()).isEqualTo(subs.calculateBill());

    }

    @Test
    public void it_should_reset_subscription_usage() {
        SmsFixedSubscription subs = new SmsFixedSubscription();

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getUsageCount() ).isEqualTo(0);

    }

    @Test
    public void it_should_reset_additional_quota() {
        SmsFixedSubscription subs = new SmsFixedSubscription();

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getAdditionalQuotaCount() ).isEqualTo(0);

    }
}
