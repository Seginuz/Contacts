package contacts.factories;

import contacts.contacts.Contact;

import java.util.Scanner;

public interface ContactFactory {

    Scanner scanner = new Scanner(System.in);

    Contact createContact();
}
