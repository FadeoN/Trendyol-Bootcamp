package model.subscription;

import model.subscription.types.email.EmailSubscription;
import model.subscription.types.sms.SmsSubscription;

public class SubscriptionHub {

    SmsSubscription smsSubscription;
    EmailSubscription emailSubscription;

    public void setSubscription(SubscriptionType subscription){
        if(subscription instanceof SmsSubscription){
            smsSubscription = (SmsSubscription) subscription;
        }
        else if (subscription instanceof  EmailSubscription){
            emailSubscription = (EmailSubscription) subscription;
        }
    }

    public void setSmsSubscription(SmsSubscription smsSubscription) {

        if(smsSubscription == null){
            System.out.print("Subscription Already exists");
            return;
            //TODO : Do something update raise error?
        }
        this.smsSubscription = smsSubscription;
    }

    public void setEmailSubscription(EmailSubscription emailSubscription) {

        if(emailSubscription == null){
            System.out.print("Subscription Already exists");
            return;
            //TODO : Do something update raise error?
        }
        this.emailSubscription = emailSubscription;
    }
}
