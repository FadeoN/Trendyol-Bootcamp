package model.notification;

import model.user.BusinessUser;
import model.user.User;


import java.util.HashMap;
import java.util.List;

public class NotificationGroupHub {

    private HashMap<NotificationGroup, NotificationGroup> savedNotificationGroups = new HashMap<>(); // No need for Hashmap if db is used
    private Integer groupID = 0;

    public void createNotificationGroup(BusinessUser sender, List<User> receivers){
        NotificationGroup notificationGroup = new NotificationGroup(groupID, sender, receivers);
        savedNotificationGroups.put(notificationGroup, notificationGroup);

        groupID += 1;
    }


    private NotificationGroup getNotificationGroup(NotificationGroup notificationGroup){
        if(savedNotificationGroups.containsKey(notificationGroup)) {
            return savedNotificationGroups.get(notificationGroup);
        }
        // TODO raise error
        return notificationGroup;
    }

    public void sendEmailUsingGroupID(BusinessUser sender, Integer groupID, String content){
        NotificationGroup notificationGroup = getNotificationGroup(new NotificationGroup(groupID, sender));
        notificationGroup.sendEmail(content);

    }

    public void sendSmsUsingGroupID(BusinessUser sender, Integer groupID, String content){
        NotificationGroup notificationGroup = getNotificationGroup(new NotificationGroup(groupID, sender));
        notificationGroup.sendSms(content);
    }

}
