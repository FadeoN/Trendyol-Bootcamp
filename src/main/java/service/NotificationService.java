package service;

import model.exception.LocalizedException;
import model.logger.ExceptionLogger;
import model.notification.Notification;

public class NotificationService {

    public static void sendNotification(Notification notification){
        try{
            notification.send();
            }
        catch(LocalizedException localException){
        ExceptionLogger.localizeErrors(localException, notification.getSender().getUserInfo().getLanguage());
            }
    }


}
