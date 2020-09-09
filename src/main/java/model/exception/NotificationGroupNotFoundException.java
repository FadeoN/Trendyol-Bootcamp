package model.exception;

import model.language.Language;

public class NotificationGroupNotFoundException extends LocalizedException {

    public NotificationGroupNotFoundException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logNotificationGroupNotFoundException();
    }
}
