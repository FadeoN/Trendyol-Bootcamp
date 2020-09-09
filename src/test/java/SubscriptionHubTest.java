import model.language.Turkish;
import model.notification.Email;
import model.notification.Notification;
import model.notification.Sms;
import model.subscription.AbstractSubscriptionType;
import model.subscription.SubscriptionType;
import model.subscription.types.email.EmailDynamicSubscription;
import model.subscription.types.sms.SmsDynamicSubscription;
import model.subscription.types.sms.SmsFixedSubscription;
import model.user.BusinessUser;
import model.user.User;
import model.user.UserInfo;
import org.junit.jupiter.api.Test;
import service.NotificationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionHubTest {

    @Test
    public void it_should_set_sms_subscription() {
        SubscriptionType subs = new SmsDynamicSubscription(0,0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        assertThat(businessUser.getSmsSubscription()).isEqualTo(subs);
    }

    @Test
    public void it_should_set_email_subscription() {
        SubscriptionType subs = new EmailDynamicSubscription(0,0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        assertThat(businessUser.getEmailSubscription()).isEqualTo(subs);
    }

    @Test
    public void it_should_return_given_email_subs_quota_full() {
        EmailDynamicSubscription subs = new EmailDynamicSubscription(0,0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Email(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        assertTrue(((AbstractSubscriptionType) businessUser.getEmailSubscription()).getSubscriptionInfo().isQuotaReached());
    }

    @Test
    public void it_should_return_given_sms_subs_quota_full() {
        SmsDynamicSubscription subs = new SmsDynamicSubscription(0,0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        assertTrue(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().isQuotaReached());
    }


    @Test
    public void it_should_increment_usage_count_twice() {
        SmsDynamicSubscription subs = new SmsDynamicSubscription(0,0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 1; i <= 2; i++) {
            NotificationService.sendNotification(notification);
        }

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getUsageCount()).isEqualTo(2);
    }

    @Test
    public void it_should_renew_subscription_when_quota_exceeded() {
        SmsFixedSubscription subs = new SmsFixedSubscription(0);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getAdditionalQuotaCount()).isEqualTo(1);
    }


}
