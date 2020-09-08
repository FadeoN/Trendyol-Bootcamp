package model.exception;

import model.language.Language;

public class SubscriptionAlreadyExistsException extends LocalizedException {

    public SubscriptionAlreadyExistsException(){

    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logSubscriptionAlreadyExistsException();
    }
}
