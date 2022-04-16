package contacts.contacts;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Organization extends Contact implements Serializable {

    public String name;
    public String address;

    public Organization(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = "[no data]";
        } else {
            this.name = name;
        }
        setLastUpdated();
    }

    public void setAddress(String address) {
        if (address.isEmpty()) {
            this.address = "[no data]";
        } else {
            this.address = address;
        }
        setLastUpdated();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getFullName() {
        return name;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return "Organization name: " + getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Number: " + getNumber() + "\n" +
                "Time created: " + getCreated().toString() + "\n" +
                "Time last edit: " + getUpdated().toString();
    }

    @Override
    public Field[] getAllFields() {
        return getClass().getFields();
    }

    @Override
    public void setField(String fieldName, String fieldValue) {
        String methodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        try {
            Method method = this.getClass().getMethod(methodName, String.class);
            method.invoke(this, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("How did you manage to mess up this badly?");
        }
    }

    @Override
    public String getField(Field field) {
        try {
            return (String) field.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Builder {
        private String orgName;
        private String address;
        private String phoneNumber;

        public Organization.Builder setOrgName(String orgName) {
            if (orgName.isEmpty()) {
                this.orgName = "[no data]";
            } else {
                this.orgName = orgName;
            }
            return this;
        }

        public Organization.Builder setAddress(String address) {
            if (address.isEmpty()) {
                this.address = "[no data]";
            } else {
                this.address = address;
            }
            return this;
        }

        public Organization.Builder setPhoneNumber(String phoneNumber) {
            if (checkPhoneNumber(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            } else {
                this.phoneNumber = "[no number]";
            }
            return this;
        }

        public Contact build() {
            System.out.println(this.orgName + this.address + this.phoneNumber);
            return new Organization(this.orgName, this.address, this.phoneNumber);
        }
    }
}
