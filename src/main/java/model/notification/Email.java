package model.notification;

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
            System.out.println("Blacklisted User");
            // TODO raise Error
            return;
        }
        else if(!sender.isEmailSubscriptionActive()){
            System.out.println("Email Subscription does not exists.");
            //TODO raise Error
            return;
        }

        sender.getEmailSubscription().incrementUsageCount();
        System.out.println("Message Content: " + content);
    }
}
