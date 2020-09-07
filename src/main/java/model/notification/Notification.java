package model.notification;

import model.user.BusinessUser;
import model.user.User;

public abstract class Notification {

    BusinessUser sender;
    User receiver;
    String content;


    public abstract void send();

}
