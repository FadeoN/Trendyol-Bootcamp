package model.notification;

import exception.BlacklistedUserException;
import exception.InactiveSubscriptionException;
import model.user.BusinessUser;
import model.user.User;

public class Email extends Notification{

    public Email(BusinessUser sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }


    @Override
    public void send() {
        if(sender.isBlacklisted()){
            throw new BlacklistedUserException();
        }
        else if(!sender.isEmailSubscriptionActive()){
            throw new InactiveSubscriptionException();
        }

        sender.getEmailSubscription().incrementUsageCount();
        System.out.println("Message Content: " + content);
    }
}
