package model.subscription.embeddedkey;

import java.time.LocalDate;
import java.util.Objects;

public class SubscriptionKey {

    private long id;
    private LocalDate startingDate;

    public SubscriptionKey(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionKey)) return false;
        SubscriptionKey that = (SubscriptionKey) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getStartingDate(), that.getStartingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartingDate());
    }

}
