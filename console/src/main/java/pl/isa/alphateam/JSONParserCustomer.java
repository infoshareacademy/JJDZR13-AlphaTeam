package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;


public class JSONParserCustomer {
    public static List<Customer> customersList;

    static {
        try {
            customersList = getCustomers1();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static final String CUSTOMERS_JSON_FILE_PATH = "console/src/main/resources/customers.json";

    private JSONParserCustomer() throws IOException {
        throw new IllegalStateException("Utility class");
    }


    public static boolean saveCustomerInDatabase(List<Customer> customerList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(CUSTOMERS_JSON_FILE_PATH), customerList);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static List<Customer> getCustomers1() throws IOException {

        try {
            InputStream inputStream = new FileInputStream(CUSTOMERS_JSON_FILE_PATH);
            String json = IOUtils.toString(inputStream);
            Type listType = new TypeToken<ArrayList<Customer>>() {
            }.getType();
            return new Gson().fromJson(json, listType);
        } catch (IOException e) {
            System.out.println("xxxx");
        }
        return null;
    }

}
