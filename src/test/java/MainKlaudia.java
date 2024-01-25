import pl.isa.alphateam.Address;
import pl.isa.alphateam.Customer;
import pl.isa.alphateam.Boat;
import pl.isa.alphateam.Reservation;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainKlaudia {
    private static final Map<String, Customer> customersDatabase = new HashMap<>(); //tworzy hashmapę zawierającą użytkowników
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Utwórz nowe konto");
            System.out.println("2. Zaloguj się");
            System.out.println("3. Wyjdź");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Do widzenia!");
                    System.exit(0);
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private static void createAccount() {
        System.out.println("Podaj imię:");
        String firstName = scanner.nextLine();

        System.out.println("Podaj nazwisko:");
        String lastName = scanner.nextLine();

        System.out.println("Podaj datę urodzenia (w formacie yyyy-MM-dd):");
        String birthdayDate = scanner.nextLine();

        System.out.println("Podaj numer telefonu:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Podaj numer patentu:");
        String patentNo = scanner.nextLine();

        System.out.println("Podaj adres: państwo, miasto, ulicę, numer ulicy:");
        String addressInput = scanner.nextLine();

        String[] addressParts = addressInput.split(", "); //cały blok try-catch, bo nie działało wpisywanie adresu

        if (addressParts.length == 4) {
            String country = addressParts[0].trim();
            String city = addressParts[1].trim();
            String street = addressParts[2].trim();

                int streetNo = Integer.parseInt(addressParts[3].trim());
                Address address = new Address(country, city, street, streetNo);

                System.out.println("Podaj adres email:");
                String emailAddress = scanner.nextLine();

                System.out.println("Podaj hasło:");
                String password = scanner.nextLine();

                try {
                    Customer newCustomer = new Customer(firstName, lastName, birthdayDate, phoneNumber, patentNo, new Address(country,city, street, streetNo), emailAddress, password);
                    customersDatabase.put(emailAddress, newCustomer);
                    System.out.println("Konto utworzone pomyślnie.");

                } catch (IllegalArgumentException e) {
                    System.out.println("Błąd podczas tworzenia konta: " + e.getMessage());
                }
        } else {
            System.out.println("Nieprawidłowy format adresu. Wprowadź go w formacie: państwo, miasto, ulica, numer ulicy");
        }
    }

    private static void login() {
        System.out.println("Podaj adres email:");
        String emailAddress = scanner.nextLine();

        System.out.println("Podaj hasło:"); 
        String password = scanner.nextLine();

        Customer customer = customersDatabase.get(emailAddress);

        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Zalogowano pomyślnie. Witaj, " + customer.getFirstName() + "!");
        } else {
            System.out.println("Błąd logowania. Sprawdź adres email i hasło.");
        }
    }
}


