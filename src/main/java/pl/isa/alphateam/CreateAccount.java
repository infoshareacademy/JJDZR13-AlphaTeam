package pl.isa.alphateam;

import java.util.List;
import java.util.Scanner;

public class CreateAccount {
    public static List<Customer> getCustomers() {
        return JSONParserCustomer.getListOfCustomerFromDatabase();
    }

    public static void addCustomer(Customer customer) {
        List<Customer> customers = JSONParserCustomer.getListOfCustomerFromDatabase();
        customers.add(customer);
        JSONParserCustomer.saveCustomerInDatabase(customers);
    }
    public static Customer createCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter surname: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter date of birth (yyyy-MM-dd):");
        String birthdayDate = scanner.nextLine();
        int age = Customer.calculateAge(birthdayDate);
        if (age < 18) {
            System.out.println("You must be 18 years old to rent a boat!");
            System.exit(0);
        }
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter license number:");
        String patentNo = scanner.nextLine();
        System.out.println("Enter country:");
        String country = scanner.nextLine();
        System.out.println("Enter city:");
        String city = scanner.nextLine();
        System.out.println("Enter street:");
        String street = scanner.nextLine();
        System.out.println("Enter street number:");
        String streetNo = scanner.nextLine();
        System.out.println("Enter e-mail:");
        String emailAddress = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        Address address = new Address(country, city, street, Integer.parseInt(streetNo));
        return new Customer(firstName, lastName, birthdayDate, phoneNumber, patentNo, address, emailAddress, password);
    }
}
