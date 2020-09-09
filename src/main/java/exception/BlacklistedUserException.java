package exception;

import language.Language;

public class BlacklistedUserException  extends LocalizedException {

    public BlacklistedUserException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logBlacklistedUserException();
    }
}
