import model.language.Turkish;
import model.notification.Notification;
import service.NotificationGroupService;
import model.notification.Sms;
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

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        Notification notification = new Sms(businessUser, user, "Oku");

        for(int i =0; i < 5000; i++){
            NotificationService.sendNotification(notification);

        }

        BillingService.calculateMonthlyBill(businessUser, subs);
        PaymentService.payDebt(businessUser, 60.);
        System.out.println(businessUser.getBillingInfo().toString());

        NotificationGroupService notificationGroupService = new NotificationGroupService();

        List<User> userGroup = new ArrayList<>();


        notificationGroupService.createNotificationGroup(businessUser, userGroup);
        notificationGroupService.sendSmsUsingGroupID(businessUser, 0, "Sa");

    }
}
