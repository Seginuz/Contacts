package contacts.contacts;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Contact implements Serializable {

    public String number;
    private final LocalDateTime created;
    private LocalDateTime updated;

    public Contact() {
        this.created = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        setLastUpdated();
    }

    public void setNumber(String number) {
        if (checkPhoneNumber(number)) {
            this.number = number;
        } else {
            this.number = "[no number]";
        }
        setLastUpdated();
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setLastUpdated() {
        updated = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    abstract public String getFullName();

    protected static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("[+]?([a-zA-Z\\d]+[ -]\\(?[a-zA-Z\\d]{2,}\\)?|\\(?[a-zA-Z\\d]+\\)?)([ -][a-zA-Z\\d]{2,})*");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) {
            return phoneNumber.equals(matcher.group());
        }
        return false;
    }

    @Override
    abstract public String toString();

    abstract public Field[] getAllFields();

    abstract public void setField(String fieldName, String fieldValue);

    abstract public String getField(Field field);
}