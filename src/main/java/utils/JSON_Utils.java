package utils;

import com.github.javafaker.Faker;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class JSON_Utils {
    public static void updateTokenInJson(String tokenValue) {
        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("token", tokenValue);
        try {
            String projectSrcDir = System.getProperty("user.dir");
            String fileName = "tokenValue_JSON.json";
            String filePath = String.format("%s\\src\\test\\resources\\json\\%s".replace("\\", File.separator), projectSrcDir, fileName);
            FileWriter file = new FileWriter(filePath);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + jsonObject);
    }


    public static void updateBookingDetailsInJson() {

        Faker faker = new Faker();
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", faker.name().firstName());
        jsonObject.put("lastname", faker.name().lastName());
        jsonObject.put("totalprice", faker.number().numberBetween(50, 5000));
        jsonObject.put("depositpaid", faker.random().nextBoolean());

        // Create a nested JSON object for "bookingdates"
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        jsonObject.put("bookingdates", bookingDates);
        jsonObject.put("additionalneeds", "Breakfast");

        // Define the file path where you want to save the JSON
        String projectSrcDir = System.getProperty("user.dir");
        String fileName = "CreateBoooking_JSON.json";
        String filePath = String.format("%s\\src\\test\\resources\\json\\%s".replace("\\", File.separator), projectSrcDir, fileName);
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Write the JSON object to the file
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
            System.out.println("JSON data has been written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








//    public
//
//    public class Booking {
//        private String firstname = "Jim";
//        private String lastname = "Brown";
//        private int totalprice = 111;
//        private boolean depositpaid = true;
//        private Map<String, String> bookingdates = new HashMap<>();
//        private String additionalneeds = "Breakfast";
//
//        public Booking() {
//            bookingdates.put("checkin", "2018-01-01");
//            bookingdates.put("checkout", "2019-01-01");
//        }
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import java.io.FileWriter;
//import java.io.IOException;
//
//        public class CreateJSONFile {
//            public static void main(String[] args) {
//                Booking booking = new Booking();
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//                try (FileWriter writer = new FileWriter("booking.json")) {
//                    gson.toJson(booking, writer);
//                    System.out.println("JSON file created successfully.");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
