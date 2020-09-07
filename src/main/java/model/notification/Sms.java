package model.notification;

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
            System.out.println("Blacklisted User");
            // TODO raise Error
            return;
        }
        else if(!sender.isSmsSubscriptionActive()){
            System.out.println("Sms Subscription does not exists.");
            //TODO raise Error
            return;
        }

        sender.getSmsSubscription().incrementUsageCount();
        System.out.println("Message Content: " + content);
    }
}
