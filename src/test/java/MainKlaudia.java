import pl.isa.alphateam.Address;
import pl.isa.alphateam.CreateAccount;
import pl.isa.alphateam.Customer;
import pl.isa.alphateam.JSONParserCustomer;

import java.util.List;
import java.util.Scanner;

public class MainKlaudia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        testCreateAndAddCustomer(scanner); // Uruchom metodę testową
    }

    public static void testCreateAndAddCustomer(Scanner scanner) {

        System.out.println("Enter name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter surname: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter date of birth (yyyy-MM-dd):");
        String birthdayDate = scanner.nextLine();
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
        int streetNo = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter e-mail:");
        String emailAddress = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        Address address = new Address(country, city, street, streetNo);
        Customer customer = new Customer(firstName, lastName, birthdayDate, phoneNumber, patentNo, address, emailAddress, password);

        addCustomer(customer);
    }

    public static void addCustomer(Customer customer) {
        List<Customer> customers = JSONParserCustomer.getListOfCustomerFromDatabase();

        // Dodanie nowego klienta do listy klientów
        customers.add(customer);

        // Zapis całej listy klientów do pliku JSON
        JSONParserCustomer.saveCustomerInDatabase(customers);

        System.out.println("You successfully created new account.");
    }
}
