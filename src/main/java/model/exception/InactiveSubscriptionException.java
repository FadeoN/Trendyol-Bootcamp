package model.exception;

import model.language.Language;

public class InactiveSubscriptionException extends LocalizedException {

    public InactiveSubscriptionException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logInactivateSubscriptionException();
    }
}
