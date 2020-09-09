import model.exception.NotificationGroupNotFoundException;
import model.language.Turkish;
import model.notification.NotificationGroup;

import org.junit.jupiter.api.Test;
import service.NotificationGroupService;
import model.subscription.AbstractSubscriptionType;
import model.subscription.SubscriptionType;
import model.subscription.types.email.EmailDynamicSubscription;
import model.subscription.types.sms.SmsDynamicSubscription;
import model.user.BusinessUser;
import model.user.User;
import model.user.UserInfo;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotificationGroupTest {

    @Test
    public void it_should_increment_id_when_notification_group_created(){
        SubscriptionType subs = new SmsDynamicSubscription(0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        NotificationGroupService notificationGroupService = new NotificationGroupService();

        List<User> userGroup = new ArrayList<>();

        Integer notificationGroupIDBefore = notificationGroupService.getGroupID();
        notificationGroupService.createNotificationGroup(businessUser, userGroup);
        assertThat(notificationGroupService.getGroupID()).isNotEqualTo(notificationGroupIDBefore);
    }

    @Test
    public void it_should_throw_notification_not_found_when_group_does_not_exists(){
        SubscriptionType subs = new SmsDynamicSubscription(0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        NotificationGroupService notificationGroupService = new NotificationGroupService();

        List<User> userGroup = new ArrayList<>();


        notificationGroupService.createNotificationGroup(businessUser, userGroup);
        assertThrows(NotificationGroupNotFoundException.class, () -> notificationGroupService.getNotificationGroup(new NotificationGroup(-1, businessUser)));
    }

    @Test
    public void it_should_add_user_to_notification_group(){
        SubscriptionType subs = new SmsDynamicSubscription(0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));


        NotificationGroupService notificationGroupService = new NotificationGroupService();

        List<User> userGroup = new ArrayList<>();


        notificationGroupService.createNotificationGroup(businessUser, userGroup);
        notificationGroupService.addUserToNotificationGroup(businessUser, 0, user);
        assertThat(notificationGroupService.getNotificationGroup(new NotificationGroup(0, businessUser)).getReceiverUsers().get(0)).isEqualTo(user);
    }

    @Test
    public void it_should_send_sms_to_notification_group(){
        SubscriptionType subs = new SmsDynamicSubscription(0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));

        NotificationGroupService notificationGroupService = new NotificationGroupService();

        List<User> userGroup = new ArrayList<>();
        userGroup.add(user);
        userGroup.add(user);



        notificationGroupService.createNotificationGroup(businessUser, userGroup);
        notificationGroupService.sendSmsUsingGroupID(businessUser, 0, "Sa");

        assertThat(((AbstractSubscriptionType) businessUser.getSmsSubscription()).getSubscriptionInfo().getUsageCount()).isEqualTo(2);
    }

    @Test
    public void it_should_send_email_to_notification_group(){
        SubscriptionType subs = new EmailDynamicSubscription(0.10);

        BusinessUser businessUser = new BusinessUser(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));
        businessUser.setSubscription(subs);

        User user = new User(new UserInfo("Name Surname", "test@email.com", "1", new Turkish()));

        NotificationGroupService notificationGroupService = new NotificationGroupService();

        List<User> userGroup = new ArrayList<>();
        userGroup.add(user);
        userGroup.add(user);



        notificationGroupService.createNotificationGroup(businessUser, userGroup);
        notificationGroupService.sendEmailUsingGroupID(businessUser, 0, "Sa");

        assertThat(((AbstractSubscriptionType) businessUser.getEmailSubscription()).getSubscriptionInfo().getUsageCount()).isEqualTo(2);
    }
}
