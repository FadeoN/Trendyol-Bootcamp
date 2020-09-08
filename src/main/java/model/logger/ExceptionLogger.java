package model.logger;

import model.exception.LocalizedException;
import model.language.Language;

public class ExceptionLogger {

    public static void localizeErrors(LocalizedException exception, Language language){
        exception.throwLocalizedException(language);
    }
}
