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

    @Override
    public void logEmptyNotificationGroupException() {
        System.out.println("Notification group is empty.");
    }

    @Override
    public void logNotificationGroupNotFoundException() {
        System.out.println("Notification group is not found.");

    }

    @Override
    public void logNotificationGroupNotInitalizedException() {
        System.out.println("Notification group is not initialized.");
    }
}
