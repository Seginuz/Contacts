package contacts.contacts;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Person extends Contact implements Serializable {

    public String name;
    public String surname;
    public String birth;
    public String gender;

    public Person(String name, String surname, String birth, String gender, String number) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.gender = gender;
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

    public void setSurname(String surname) {
        if (surname.isEmpty()) {
            this.surname = "[no data]";
        } else {
            this.surname = surname;
        }
        setLastUpdated();
    }

    public void setBirth(String birth) {
        if (birth.isEmpty()) {
            this.birth = "[no data]";
        } else {
            this.birth = birth;
        }
        setLastUpdated();
    }

    public void setGender(String gender) {
        if (gender.isEmpty()) {
            this.gender = "[no data]";
        } else {
            this.gender = gender;
        }
        setLastUpdated();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String getFullName() {
        return name + " " + surname;
    }

    public String getBirth() {
        return birth;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Birth date: " + getBirth() + "\n" +
                "Gender: " + getGender() + "\n" +
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
        private String name;
        private String surname;
        private String phoneNumber;
        private String birthDate;
        private String gender;

        public Person.Builder setName(String name) {
            if (name.isEmpty()) {
                this.name = "[no data]";
            } else {
                this.name = name;
            }
            return this;
        }

        public Person.Builder setSurname(String surname) {
            if (surname.isEmpty()) {
                this.surname = "[no data]";
            } else {
                this.surname = surname;
            }
            return this;
        }

        public Person.Builder setBirthDate(String birthDate) {
            if (birthDate.isEmpty()) {
                this.birthDate = "[no data]";
            } else {
                this.birthDate = birthDate;
            }
            return this;
        }

        public Person.Builder setGender(String gender) {
            if (!gender.isEmpty()) {
                gender = String.valueOf(gender.charAt(0));
                if (gender.equals("M") || gender.equals("F")) {
                    this.gender = gender;
                    return this;
                }
            }
            this.gender = "[no data]";
            return this;
        }

        public Person.Builder setPhoneNumber(String phoneNumber) {
            if (checkPhoneNumber(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            } else {
                this.phoneNumber = "[no number]";
            }
            return this;
        }

        public Contact build() {
            return new Person(this.name, this.surname, this.birthDate, this.gender, this.phoneNumber);
        }
    }
}
