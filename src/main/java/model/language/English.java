package model.language;

public class English implements Language {

    @Override
    public void logBlacklistedUserException() {
        System.out.println("BlacklistedUserException - EN");
    }

    @Override
    public void logInactivateSubscriptionException() {
        System.out.println("InactivateSubscriptionExceptio -EN");
    }

    @Override
    public void logQuotaExceededException() {
        System.out.println("QuotaExceededException - EN");
    }

    @Override
    public void logSubscriptionAlreadyExistsException() {
        System.out.println("Subscription already exists.");
    }
}
