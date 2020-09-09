package exception;

import language.Language;

public class InactiveSubscriptionException extends LocalizedException {

    public InactiveSubscriptionException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logInactivateSubscriptionException();
    }
}
