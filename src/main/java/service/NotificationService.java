package service;

import model.notification.Notification;
import model.user.BusinessUser;
import model.user.User;

import java.util.List;

public class NotificationService {

    public static void sendNotification(Notification notification){
        notification.send();
    }


}
