package model.user;

import language.Language;

public class UserInfo {

    private long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Language language;

    public UserInfo(String fullName, String email, String phoneNumber, Language language) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
