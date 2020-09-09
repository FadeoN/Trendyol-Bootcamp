package model.notification;

import exception.EmptyNotificationGroupException;
import exception.NotificationGroupNotInitalizedException;
import model.user.BusinessUser;
import model.user.User;
import service.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationGroup {

    private Integer groupID;
    private BusinessUser sender; // userid
    private List<User> receiverUsers;

    public NotificationGroup(Integer groupID, BusinessUser sender) {
        this.groupID = groupID;
        this.sender = sender;
    }

    public NotificationGroup(Integer groupID, BusinessUser sender, List<User> receiverUsers) {
        this.groupID = groupID;
        this.sender = sender;
        this.receiverUsers = receiverUsers;
    }

    public void attach(User user){
        if(receiverUsers == null){
            receiverUsers = new ArrayList<>();
        }
        this.receiverUsers.add(user);
    }

    public void sendEmail(String content){
        if(!isReceiversValid()){
            return;
        }
        for (User receiver : receiverUsers) {
            Notification notification = new Email(sender, receiver, content);
            NotificationService.sendNotification(notification);
        }
    }

    public void sendSms(String content){
        if(!isReceiversValid()){
            return;
        }
        for (User receiver : receiverUsers) {
            Notification notification = new Sms(sender, receiver, content);
            NotificationService.sendNotification(notification);
        }
    }

    public boolean  isReceiversValid(){
        if(receiverUsers == null){
            throw new NotificationGroupNotInitalizedException();
        }
        if(receiverUsers.isEmpty()){
            throw new EmptyNotificationGroupException();
        }

        return true;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public BusinessUser getSender() {
        return sender;
    }

    public List<User> getReceiverUsers() {
        return receiverUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationGroup)) return false;
        NotificationGroup that = (NotificationGroup) o;
        return Objects.equals(getGroupID(), that.getGroupID()) &&
                Objects.equals(getSender(), that.getSender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupID(), getSender());
    }


}
