package model.subscription;

import model.exception.SubscriptionAlreadyExistsException;
import model.subscription.types.email.EmailSubscription;
import model.subscription.types.sms.SmsSubscription;

public class SubscriptionHub{

    SmsSubscription smsSubscription;
    EmailSubscription emailSubscription;


    public void setSubscription(SubscriptionType subscription){
        if(subscription instanceof SmsSubscription){
            setSmsSubscription((SmsSubscription) subscription);
        }
        else if (subscription instanceof  EmailSubscription){
            setEmailSubscription((EmailSubscription) subscription);
        }
    }

    public void setSmsSubscription(SmsSubscription smsSubscription) {

        if(this.smsSubscription != null){
            throw new SubscriptionAlreadyExistsException();

        }
        this.smsSubscription = smsSubscription;
    }

    public void setEmailSubscription(EmailSubscription emailSubscription) {

        if(this.emailSubscription != null){
            throw new SubscriptionAlreadyExistsException();
        }
        this.emailSubscription = emailSubscription;
    }

    public SmsSubscription getSmsSubscription() {
        return smsSubscription;
    }

    public EmailSubscription getEmailSubscription() {
        return emailSubscription;
    }

    public boolean isSmsSubscriptionActive() {
        return smsSubscription != null;
    }

    public boolean isEmailSubscriptionActive() {
        return emailSubscription != null;
    }
}
