package model.language;

public interface Language {

    void logBlacklistedUserException();
    void logInactivateSubscriptionException();
    void logQuotaExceededException();
    void logSubscriptionAlreadyExistsException();

}
