package testAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import config.ConfigFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.CreateBookingPOJO;
import utils.HttpMethod;
import utils.JSONPojoUtils;
import utils.JSON_Utils;

public class UpdateBookingApi {

    private UpdateBookingApi() {}

    public static Response updateBooking(String token, String bookingId) throws Exception {
        JSON_Utils.updateBookingDetailsInJson();
        CreateBookingPOJO dataset = JSONPojoUtils.createBokk("CreateBoooking_JSON.json");
        String jsonBody = objectToJson(dataset);
//        System.out.println("API Body : " + jsonBody);
        HttpMethod obj = new HttpMethod();
        Response response = obj.put(token, jsonBody, (ConfigFactory.getConfig().BASE_URL() + ConfigFactory.getConfig().BOOKING_ENDPOINT()), bookingId)
                .then().statusCode(200).log().all().extract().response();
        System.out.println("Response from createAuth :" + response);
        System.out.println("Create working..");
        String temp = response.asString();
        JsonPath jsonPath = new JsonPath(temp);
        Assert.assertNotNull(jsonPath.getString("firstname"));
        return response;
    }

    public static String objectToJson(CreateBookingPOJO dataObj) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(dataObj);
    }

}
