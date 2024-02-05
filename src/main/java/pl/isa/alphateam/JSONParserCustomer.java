package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JSONParserCustomer {
    static final String CUSTOMER_JSON_FILE_PATH = "src/main/resources/customers.json";

    private JSONParserCustomer() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Customer> getListOfCustomerFromDatabase() {
        try {
            byte[] data = Files.readAllBytes(Path.of(CUSTOMER_JSON_FILE_PATH));
            ObjectMapper objectMapper = new ObjectMapper();
            Customer[] customersArray = objectMapper.readValue(data, Customer[].class);
            return new ArrayList<>(Arrays.asList(customersArray));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static boolean saveCustomerInDatabase(List<Customer> customerList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(CUSTOMER_JSON_FILE_PATH), customerList);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}