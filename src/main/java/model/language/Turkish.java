package model.language;

public class Turkish implements Language {

    @Override
    public void logBlacklistedUserException() {
        System.out.println("Kara Listeli Kullanıcı.");
    }

    @Override
    public void logInactivateSubscriptionException() {
        System.out.println("Üyelik bulunamadı.");
    }

    @Override
    public void logQuotaExceededException() {
        System.out.println("Kota aşıldı.");
    }

    @Override
    public void logSubscriptionAlreadyExistsException() {
        System.out.println("Halihazırda paket bulunmaktadır.");
    }
}
