package model.exception;

import model.language.Language;

public class NotificationGroupNotInitalizedException extends LocalizedException {
    public NotificationGroupNotInitalizedException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logNotificationGroupNotInitalizedException();
    }
}
