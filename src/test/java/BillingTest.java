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
import service.SubscriptionService;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingTest {

    public SubscriptionService subscriptionService = new SubscriptionService();

    @Test
    public void it_should_change_billing_debt_when_billed() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeFixedSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");


        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota()+1; i++) {
            NotificationService.sendNotification(notification);
        }

        Double billAmount = subs.calculateBill();
        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        assertThat(businessUser.getBillingInfo().getDebt()).isEqualTo(billAmount);

    }

    @Test
    public void it_should_return_last_bill_fixed_subscription() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeFixedSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");


        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota()+1; i++) {
            NotificationService.sendNotification(notification);
        }

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        assertThat(businessUser.getBillingInfo().getLastBillingAmount()).isEqualTo(subs.getSubscriptionInfo().getSubscriptionPrice() * 2);

    }


    @Test
    public void it_should_return_last_bill_dynamic_subscription_when_quota_exceeded() {

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeDynamicSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota()+1; i++) {
            NotificationService.sendNotification(notification);
        }

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        assertThat(businessUser.getBillingInfo().getLastBillingAmount()).isEqualTo((subs).getSubscriptionInfo().getSubscriptionPrice() + 2 * 0.10);

    }

    @Test
    public void it_should_reset_subscription_usage() {
        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeFixedSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

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
        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        subscriptionService.subscribeFixedSmsPackage(businessUser);
        AbstractSubscriptionType subs = subscriptionService.getAbstractSubscriptionType(0);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for (int i = 0; i <= subs.getSubscriptionInfo().getUsageQuota(); i++) {
            NotificationService.sendNotification(notification);
        }

        BillingService.calculateMonthlyBill(businessUser, businessUser.getSmsSubscription());

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getAdditionalQuotaCount() ).isEqualTo(0);

    }
}
