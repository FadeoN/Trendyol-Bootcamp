package exception;

import language.Language;

public class NotificationGroupNotFoundException extends LocalizedException {

    public NotificationGroupNotFoundException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logNotificationGroupNotFoundException();
    }
}
