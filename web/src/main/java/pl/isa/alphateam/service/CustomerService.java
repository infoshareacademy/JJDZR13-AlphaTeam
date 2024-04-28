package pl.isa.alphateam.service;


import pl.isa.alphateam.customer.Customer;
import pl.isa.alphateam.customer.CustomerDto;
import pl.isa.alphateam.customer.CustomerMapper;
import pl.isa.alphateam.customer.Login;
import pl.isa.alphateam.parser.JSONParserCustomer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.isa.alphateam.parser.JSONParserCustomer.saveCustomerInDatabase;


public class CustomerService {

    public static void createNewCustomerRecord(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        List<Customer> customerList = new ArrayList<>();
        try {
            customerList = JSONParserCustomer.getCustomers1();

        } catch (IOException e) {
            System.out.println("We have problem with database");
        }

        int nextId = customerList.get(customerList.size()-1).getId() + 1;
        customer.setId(nextId);
        customerList.add(customer);
        saveCustomerInDatabase(customerList);
    }

    public static Customer getCustomerWithGivenLogin(Login login) {
        try {
            List<Customer> customerList = JSONParserCustomer.getCustomers1();
            for (Customer customer : customerList) {
                if (customer.getPassword().equals(login.getPassword()) && customer.getEmailAddress().equals(login.getEmail())) {
                    return customer;
                }
            }
        } catch (IOException e) {
            System.out.println("We have problem with database");
        }
        return null;
    }
    public static Customer getCustomerWithGivenId(int id) {
        try {
            List<Customer> customerList = JSONParserCustomer.getCustomers1();
            for (Customer customer : customerList) {
                if (customer.getId()==(id)) {
                    return customer;
                }
            }
        } catch (IOException e) {
            System.out.println("We have problem with database");
        }
        return null;
    }
}