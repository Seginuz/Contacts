package contacts;

import contacts.contacts.Contact;

import java.io.*;
import java.util.ArrayList;

public class PhoneBook implements Serializable {
    private final ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public Contact getContact(int id) {
        Contact contact;
        try {
            contact = this.contacts.get(id);
        } catch (Exception e) {

            return null;
        }
        return contact;
    }

    public ArrayList<Contact> getContacts() {
        return this.contacts;
    }

    public int getContactCount() {
        return contacts.size();
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    public static PhoneBook deserializePhonebook(String fileLocation) {
        try (FileInputStream fis = new FileInputStream(fileLocation);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (PhoneBook) ois.readObject();
        } catch (Exception ignored) {
            return null;
        }
    }

    public static void serializePhonebook(PhoneBook phoneBook, String fileLocation) {
        try (FileOutputStream fos = new FileOutputStream(fileLocation);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(phoneBook);
        } catch (IOException ignored) {

        }
    }
}
