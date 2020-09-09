package exception;

import language.Language;

public abstract class LocalizedException extends RuntimeException{


    public abstract void throwLocalizedException(Language language);
}
