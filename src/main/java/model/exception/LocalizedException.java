package model.exception;

import model.language.Language;

public abstract class LocalizedException extends RuntimeException{


    public abstract void throwLocalizedException(Language language);
}
