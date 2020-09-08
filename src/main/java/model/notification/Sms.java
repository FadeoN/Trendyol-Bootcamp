package model.notification;

import model.exception.BlacklistedUserException;
import model.exception.InactiveSubscriptionException;
import model.user.BusinessUser;
import model.user.User;

public class Sms extends Notification{

    public Sms(BusinessUser sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    @Override
    public void send() {
        if(sender.isBlacklisted()){
            throw new BlacklistedUserException();
        }
        else if(!sender.isSmsSubscriptionActive()){
            throw new InactiveSubscriptionException();
        }

        sender.getSmsSubscription().incrementUsageCount();
        System.out.println("Message Content: " + content);
    }
}
