package contacts.factories;

import contacts.contacts.Contact;
import contacts.contacts.Person;

public class PersonFactory implements ContactFactory {

    @Override
    public Contact createContact() {
        Person.Builder builder = new Person.Builder();

        System.out.println("Enter the name:");
        String name = scanner.nextLine();

        System.out.println("Enter the surname:");
        String surname = scanner.nextLine();

        System.out.println("Enter the birth date:");
        String birthDate = scanner.nextLine();

        System.out.println("Enter the gender (M, F):");
        String gender = scanner.nextLine();

        System.out.println("Enter the number:");
        String number = scanner.nextLine();

        return builder
                .setName(name)
                .setSurname(surname)
                .setBirthDate(birthDate)
                .setGender(gender)
                .setPhoneNumber(number)
                .build();
    }
}
