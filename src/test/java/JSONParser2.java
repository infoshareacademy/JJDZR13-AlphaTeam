/*
package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class JSONParser2 {
    static ObjectMapper mapper = new ObjectMapper();
    static String boatJSONFilePath = "src/test/java/boats.json";
    static List<Boat> boatList = new ArrayList<>();
    static org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
    private JSONParser2() {
        throw new IllegalStateException("Utility class");
    }

    public static void getListOfBoatsFromDatabase() throws IOException, ParseException {
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(boatJSONFilePath));

        for (Object object : jsonArray) {
            JSONObject boat = (JSONObject) object;
            Boat boatForm = new Boat();

            String name = (String) boat.get("name");
            boatForm.setName(name);

            long capacity = (Long) boat.get("capacity");
            boatForm.setCapacity(Integer.parseInt(String.valueOf(capacity)));

            var costPerDay = (Double) boat.get("costPerDay");
            boatForm.setCostPerDay(costPerDay);

            boolean isAvailable = (Boolean) boat.get("isAvailable");
            boatForm.setAvailable(isAvailable);

            String boatId = (String) boat.get("boatId");
            boatForm.setBoatId(boatId);

            boatList.add(boatForm);
        }

        for (Boat boat : boatList) {
            System.out.println(boat);
        }

    }

    public static void saveBoatInDatabase(Boat boatForBasia) throws IOException, ParseException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", boatForBasia.getName());
        jsonObject.put("capacity", boatForBasia.getCapacity());
        jsonObject.put("costPerDay", boatForBasia.getCostPerDay());
        jsonObject.put("isAvailable", boatForBasia.isAvailable());
        jsonObject.put("boatId", boatForBasia.getBoatId());

        StringWriter out = new StringWriter();
        jsonObject.writeJSONString(out);

        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(boatJSONFilePath));
        jsonArray.add(jsonObject);

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        String jsons = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray);

        FileWriter file = null;
        try {
            file = new FileWriter(boatJSONFilePath);
            file.write(jsons);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert file != null;
            file.close();
        }
        System.out.println("JSON file created: " + json);
    }
}
*/
