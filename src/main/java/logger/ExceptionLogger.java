package logger;

import exception.LocalizedException;
import language.Language;

public class ExceptionLogger {

    public static void localizeErrors(LocalizedException exception, Language language){
        exception.throwLocalizedException(language);
    }
}
