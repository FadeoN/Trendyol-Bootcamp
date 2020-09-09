package service;

import exception.LocalizedException;
import exception.NotificationGroupNotFoundException;
import logger.ExceptionLogger;
import model.notification.NotificationGroup;
import model.user.BusinessUser;
import model.user.User;


import java.util.HashMap;
import java.util.List;

public class NotificationGroupService {

    private HashMap<NotificationGroup, NotificationGroup> savedNotificationGroups = new HashMap<>(); // No need for Hashmap if db is used
    private Integer groupID = 0;

    public void createNotificationGroup(BusinessUser sender, List<User> receivers){
        NotificationGroup notificationGroup = new NotificationGroup(groupID, sender, receivers);
        savedNotificationGroups.put(notificationGroup, notificationGroup);

        groupID += 1;
    }

    public void addUserToNotificationGroup(BusinessUser sender, Integer groupID, User user){
        try{
            NotificationGroup notificationGroup = getNotificationGroup(new NotificationGroup(groupID, sender));
            notificationGroup = getNotificationGroup(notificationGroup);
            notificationGroup.attach(user);
        }
        catch(LocalizedException localizedException){
            ExceptionLogger.localizeErrors(localizedException, sender.getUserInfo().getLanguage());
        }


    }

    public NotificationGroup getNotificationGroup(NotificationGroup notificationGroup){
        if(savedNotificationGroups.containsKey(notificationGroup)) {
            return savedNotificationGroups.get(notificationGroup);
        }
        throw new NotificationGroupNotFoundException();
    }

    public void sendEmailUsingGroupID(BusinessUser sender, Integer groupID, String content){
        try{
            NotificationGroup notificationGroup = getNotificationGroup(new NotificationGroup(groupID, sender));
            notificationGroup.sendEmail(content);
        }
        catch(LocalizedException localizedException){
            ExceptionLogger.localizeErrors(localizedException, sender.getUserInfo().getLanguage());
        }


    }

    public void sendSmsUsingGroupID(BusinessUser sender, Integer groupID, String content){
        try{
            NotificationGroup notificationGroup = getNotificationGroup(new NotificationGroup(groupID, sender));
            notificationGroup.sendSms(content);
        }
        catch(LocalizedException localizedException){
            ExceptionLogger.localizeErrors(localizedException, sender.getUserInfo().getLanguage());
        }

    }

    public Integer getGroupID() {
        return groupID;
    }
}
