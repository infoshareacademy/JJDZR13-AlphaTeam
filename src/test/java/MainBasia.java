import pl.isa.alphateam.Address;
import pl.isa.alphateam.Customer;
import pl.isa.alphateam.CustomerDataCenter;
import pl.isa.alphateam.JSONParserCustomer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainBasia {
    public static void main(String[] args) throws IOException {
/*        List<Boat> boats = getListOfBoatsFromDatabase();
        for (Boat boat : boats) {
            System.out.println(boat.getName());
        }

        boolean written = saveBoatInDatabase(boats);
        System.out.println(written);

        Menu.printMainPanelMenu();*/
//////////////////////////
    /*    List<Customer> customers = new ArrayList<>();
        customers= JSONParserCustomer.getCustomers1();
        List<Customer> customers2 = JSONParserCustomer.getListOfCustomersFromDatabase();

        for (Customer customer : customers) {
            System.out.println(customer);
        }

        for (Customer customer : customers2) {
            System.out.println(customer);
        }

        Customer kasia = new Customer("Hope", "Z.", "23/10/11", "1254", new Address("Kraj","Miasto","Ulica",13), "kasie@email.com", "kasiaPass");
        customers.add(kasia);
        JSONParserCustomer.saveCustomer(customers);*/

      //  CustomerDataCenter.getLoginMap();
/////////////////////////////


        Map<Map<String, String>, Customer> customerLoginDetails = new HashMap<>();
        var customersList = JSONParserCustomer.customersList;

        for (Customer customer : customersList) {
            System.out.println(customer);
            Map<String, String> login = new HashMap<>();
            login.put(customer.getFirstName(), customer.getPassword());
            customerLoginDetails.put(login, customer);
        }

        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", "pass");
        Customer customer = CustomerDataCenter.getLoginMap().get(loginDetails);
    }

}


