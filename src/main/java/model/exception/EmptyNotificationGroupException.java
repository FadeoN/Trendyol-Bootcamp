package model.exception;

import model.language.Language;

public class EmptyNotificationGroupException extends LocalizedException {

    public EmptyNotificationGroupException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logEmptyNotificationGroupException();
    }
}
