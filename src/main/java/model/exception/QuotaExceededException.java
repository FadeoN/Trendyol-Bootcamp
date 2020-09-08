package model.exception;


import model.language.Language;

public class QuotaExceededException  extends LocalizedException{

    public QuotaExceededException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logQuotaExceededException();
    }
}
