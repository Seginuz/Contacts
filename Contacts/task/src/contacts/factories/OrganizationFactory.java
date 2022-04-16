package contacts.factories;

import contacts.contacts.Contact;
import contacts.contacts.Organization;

public class OrganizationFactory implements ContactFactory {

    @Override
    public Contact createContact() {
        Organization.Builder builder = new Organization.Builder();

        System.out.println("Enter the organization name:");
        String orgName = scanner.nextLine();

        System.out.println("Enter the address:");
        String address = scanner.nextLine();

        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        return builder
                .setOrgName(orgName)
                .setAddress(address)
                .setPhoneNumber(phoneNumber)
                .build();
    }
}
