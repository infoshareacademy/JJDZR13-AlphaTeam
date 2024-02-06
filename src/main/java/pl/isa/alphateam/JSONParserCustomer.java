package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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

    static final String CUSTOMERS_JSON_FILE_PATH = "src/main/resources/customers.json";
    static final String CUSTOMERS_JSON_FILE_PATH_2 = "src/main/resources/customers.json";

    private JSONParserCustomer() throws IOException {
        throw new IllegalStateException("Utility class");
    }

/*    public static List<Customer> getListOfCustomersFromDatabase() throws IOException {
        try {
            byte[] data = Files.readAllBytes(Path.of(CUSTOMERS_JSON_FILE_PATH));
            ObjectMapper objectMapper = new ObjectMapper();

            return Arrays.stream(objectMapper.readValue(data, Customer[].class)).toList();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    public static List<Customer> getListOfReservationsFromDatabase2() {
        try {
            byte[] data = Files.readAllBytes(Path.of(CUSTOMERS_JSON_FILE_PATH));
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.stream(objectMapper.readValue(data, Customer[].class)).toList();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }*/



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

/*    public static Customer[] getCustomers2() throws IOException {
        Gson gson = new Gson();
        File file = new File(CUSTOMERS_JSON_FILE_PATH);
        if (file.exists()) {
            FileReader fileReader= new FileReader(CUSTOMERS_JSON_FILE_PATH);
            JsonReader reader = new JsonReader(fileReader);
            return gson.fromJson(reader, Customer[].class);
        }
        return new Customer[0];
    }

    public static void saveCustomer(List<Customer> customersList) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter= new FileWriter(CUSTOMERS_JSON_FILE_PATH_2);
        gson.toJson(customersList, fileWriter);
        fileWriter.close();
    }*/


}
