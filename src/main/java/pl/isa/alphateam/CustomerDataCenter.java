package pl.isa.alphateam;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static pl.isa.alphateam.JSONParserCustomer.saveCustomerInDatabase;

public class CustomerDataCenter {
    private static final Map<Map<String, String>, Customer> loginMap = getLoginMap();

    public static Map<Map<String, String>, Customer> getLoginMap() {
        Map<Map<String, String>, Customer> customerLoginDetails = new HashMap<>();
        try{
            var customerList = JSONParserCustomer.getCustomers1();
            for (Customer customer : customerList) {
                Map<String, String> login = new HashMap<>();
                login.put(customer.getEmailAddress(), customer.getPassword());
                customerLoginDetails.put(login, customer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return customerLoginDetails;
    }

    public static void createNewCustomerRecord(Map<String, String> customerData) {
        Address address = new Address(customerData.get("country"), customerData.get("city"), customerData.get("streetName"), Integer.parseInt(customerData.get("streetNumber")));
        Customer customer = new Customer(customerData.get("firstName"),
                customerData.get("lastName"),
                customerData.get("birthdayDate"),
                customerData.get("patentNo"),
                address,
                customerData.get("emailAddress"),
                customerData.get("password"));

        List<Customer> customerList = new ArrayList<>();
        ;
        try {
            customerList = JSONParserCustomer.getCustomers1();

        } catch (IOException e) {
            System.out.println("We have problem with database");
        }
        customerList.add(customer);
        saveCustomerInDatabase(customerList);


    }
}
