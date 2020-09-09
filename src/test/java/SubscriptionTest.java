import exception.BlacklistedUserException;
import exception.NotificationGroupNotFoundException;
import language.Turkish;
import model.billing.BillingInfo;
import model.notification.Email;
import model.notification.Notification;
import model.notification.NotificationGroup;
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
import service.BillingService;
import service.NotificationGroupService;
import service.NotificationService;
import service.SubscriptionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionTest {

    public SubscriptionService subscriptionService = new SubscriptionService();


    @Test
    public void it_should_throw_blacklisted_user_exception_when_user_did_not_pay(){

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeDynamicSmsPackage(businessUser);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));

        Notification notification = new Sms(businessUser, user, "Oku");

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        LocalDate billOverDueDate = businessUser.getBillingInfo().getLastPayment().plusDays((long) BillingInfo.getBlacklistDays());
        businessUser.getBillingInfo().setLastPayment(billOverDueDate);

        assertThrows(BlacklistedUserException.class, () -> notification.send());
    }

    @Test
    public void it_should_set_sms_subscription() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeDynamicSmsPackage(businessUser);

        assertThat(businessUser.getSmsSubscription()).isEqualTo(subscriptionService.getSubscriptionType(0));
    }

    @Test
    public void it_should_set_email_subscription() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeDynamicEmailPackage(businessUser);

        assertThat(businessUser.getEmailSubscription()).isEqualTo(subscriptionService.getSubscriptionType(0));
    }

    @Test
    public void it_should_return_given_email_subs_quota_full() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeDynamicEmailPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Email(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        assertTrue(((AbstractSubscriptionType) businessUser.getEmailSubscription()).getSubscriptionInfo().isQuotaReached());
    }

    @Test
    public void it_should_return_given_sms_subs_quota_full() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeDynamicSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        assertTrue(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().isQuotaReached());
    }


    @Test
    public void it_should_increment_usage_count_twice() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeDynamicSmsPackage(businessUser);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 1; i <= 2; i++) {
            NotificationService.sendNotification(notification);
        }

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getUsageCount()).isEqualTo(2);
    }

    @Test
    public void it_should_renew_subscription_when_quota_exceeded() {
        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeFixedSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getAdditionalQuotaCount()).isEqualTo(1);
    }


}
