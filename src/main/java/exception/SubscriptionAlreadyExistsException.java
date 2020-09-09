package exception;

import language.Language;

public class SubscriptionAlreadyExistsException extends LocalizedException {

    public SubscriptionAlreadyExistsException(){

    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logSubscriptionAlreadyExistsException();
    }
}
