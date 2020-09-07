import model.notification.Email;
import model.notification.Notification;
import model.notification.NotificationGroupHub;
import model.notification.Sms;
import model.subscription.types.email.EmailDynamicSubscription;
import model.user.BusinessUser;
import model.subscription.types.sms.SmsDynamicSubscription;
import model.subscription.SubscriptionType;
import model.user.User;
import model.user.UserInfo;
import service.BillingService;
import service.NotificationService;
import service.PaymentService;

import java.util.ArrayList;
import java.util.List;

public class TrendyolApplication {
    public static void main(String[] args) {
        SubscriptionType subs = new SmsDynamicSubscription(0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1"));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1"));


        Notification notification = new Sms(businessUser, user, "Oku");

        for(int i =0; i < 5000; i++){
            NotificationService.sendNotification(notification);

        }



        BillingService.calculateMonthlyBill(businessUser, subs);
        PaymentService.payDebt(businessUser, 60.);
        System.out.println(businessUser.getBillingInfo().toString());

        NotificationGroupHub notificationGroupHub = new NotificationGroupHub();

        List<User> userGroup = new ArrayList<>();
        userGroup.add(user);
        userGroup.add(user);

        notificationGroupHub.createNotificationGroup(businessUser, userGroup);
        notificationGroupHub.sendSmsUsingGroupID(businessUser, 0, "Sa");

    }
}
