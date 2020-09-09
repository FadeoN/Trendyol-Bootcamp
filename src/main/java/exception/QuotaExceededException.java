package exception;


import language.Language;

public class QuotaExceededException  extends LocalizedException{

    public QuotaExceededException(){
    }

    @Override
    public void throwLocalizedException(Language language) {
        language.logQuotaExceededException();
    }
}
