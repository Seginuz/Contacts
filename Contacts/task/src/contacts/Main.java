package contacts;

import contacts.contacts.Contact;
import contacts.factories.ContactFactory;
import contacts.factories.OrganizationFactory;
import contacts.factories.PersonFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactFactory organizationFactory = new OrganizationFactory();
    private static final ContactFactory personFactory = new PersonFactory();
    private static PhoneBook phoneBook;
    private static String fileLocation = null;

    public static void main(String[] args) {

        if (args.length != 0) {
            fileLocation = args[0];

            phoneBook = PhoneBook.deserializePhonebook(fileLocation);
            if (phoneBook == null) {
                phoneBook = new PhoneBook();
            }
        } else {
            phoneBook = new PhoneBook();
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String action = scanner.nextLine();

            switch (action) {
                case "add":
                    addContact();
                    if (fileLocation != null) {
                        PhoneBook.serializePhonebook(phoneBook, fileLocation);
                    }
                    break;
                case "list":
                    listContacts();
                    break;
                case "search":
                    searchContacts();
                    break;
                case "count":
                    countContacts();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Action not supported.");
                    break;
            }

            System.out.println();
        }
    }

    public static void addContact() {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();

        Contact contact;
        if ("person".equals(type)) {
            contact = personFactory.createContact();
        } else if ("organization".equals(type)) {
            contact = organizationFactory.createContact();
        } else {
            System.out.println("That isn't a contact type!");
            return;
        }

        phoneBook.addContact(contact);
        System.out.println("The record was added.");
    }

    public static void listContacts() {
        if (phoneBook.getContactCount() != 0) {
            ArrayList<Contact> contacts = phoneBook.getContacts();

            for (int i = 0; i < phoneBook.getContactCount(); i++) {
                Contact contact = contacts.get(i);
                System.out.println((i + 1) + ". " + contact.getFullName());
            }

            System.out.println("[list] Enter action ([number], back):");
            String action = scanner.nextLine();

            if (!"back".equals(action)) {
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(action);
                if (matcher.matches()) {
                    int recordID = Integer.parseInt(action) - 1;
                    Contact contact = phoneBook.getContact(recordID);
                    if (contact != null) {
                        showRecord(phoneBook.getContact(recordID));
                    } else {
                        System.out.println("That record doesn't exist.");
                    }
                } else {
                    System.out.println("Action not supported.");
                }
            }
        } else {
            System.out.println("No records found!");
        }
    }

    public static void searchContacts() {
        boolean searchExit = false;
        while (!searchExit) {
            try {
                System.out.println("Enter search query:");
                Pattern searchPattern = Pattern.compile(scanner.nextLine(), Pattern.CASE_INSENSITIVE);
                ArrayList<Contact> foundContacts = new ArrayList<>();

                for (Contact contact : phoneBook.getContacts()) {
                    StringBuilder allFields = new StringBuilder();

                    for (Field field : contact.getAllFields()) {
                        if (field.get(contact) != null) {
                            allFields.append((String) field.get(contact));
                        }
                    }

                    Matcher matcher = searchPattern.matcher(allFields);
                    if (matcher.find()) {
                        foundContacts.add(contact);
                    }
                }

                if (!foundContacts.isEmpty()) {
                    String endOfString = (foundContacts.size() == 1) ? " result:" : " results:";
                    System.out.println("Found " + foundContacts.size() + endOfString);

                    for (int i = 0; i < foundContacts.size(); i++) {
                        System.out.println((i + 1) + ". " + foundContacts.get(i).getFullName());
                    }
                    System.out.println();
                } else {
                    System.out.println("No results found");
                    searchExit = true;
                }

                System.out.println("[search] Enter action ([number], back, again):");
                String action = scanner.nextLine();

                switch (action) {
                    case "back":
                        searchExit = true;
                        break;
                    case "again":
                        break;
                    default:
                        Pattern pattern = Pattern.compile("\\d+");
                        Matcher matcher = pattern.matcher(action);
                        if (matcher.matches()) {
                            int recordID = Integer.parseInt(action) - 1;
                            showRecord(foundContacts.get(recordID));
                        } else {
                            System.out.println("Action not supported.");
                        }
                        searchExit = true;
                        break;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void countContacts() {
        String endOfString = (phoneBook.getContactCount() == 1) ? " record." : " records.";
        System.out.println("The Phone Book has " + phoneBook.getContactCount() + endOfString);
    }

    public static void showRecord(Contact contact) {
        boolean exitRecord = false;

        while (!exitRecord) {
            System.out.println(contact.toString());
            System.out.println();

            System.out.println("[record] Enter action (edit, delete, menu):");
            String action = scanner.nextLine();

            switch (action) {
                case "edit":
                    editContact(contact);
                    if (fileLocation != null) {
                        PhoneBook.serializePhonebook(phoneBook, fileLocation);
                    }
                    break;
                case "delete":
                    phoneBook.removeContact(contact);
                    System.out.println("Record successfully deleted.");
                    if (fileLocation != null) {
                        PhoneBook.serializePhonebook(phoneBook, fileLocation);
                    }
                    exitRecord = true;
                    break;
                default:
                    exitRecord = true;
            }
        }
    }

    public static void editContact(Contact contact) {
        Field[] fields = contact.getAllFields();

        System.out.print("Select a field (");
        for (int i = 0; i < fields.length; i++) {
            System.out.print(fields[i].getName());
            if (i + 1 != fields.length) {
                System.out.print(", ");
            }
        }
        System.out.println("):");

        String fieldName = scanner.nextLine();

        boolean hasField = false;
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                hasField = true;
                break;
            }
        }

        if (!hasField) {
            System.out.println("The contact doesn't contain this field!");
            return;
        }

        System.out.println("Enter " + fieldName + ":");
        String fieldValue = scanner.nextLine();
        contact.setField(fieldName, fieldValue);

        System.out.println("The record was updated.");
    }
}
