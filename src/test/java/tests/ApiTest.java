package tests;

import config.ConfigFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import pojo.Bookingdates;
import testAPI.CreateBookingApi;
import testAPI.UpdateBookingApi;
import utils.HttpMethod;
import static org.hamcrest.Matchers.containsString;

import java.util.PriorityQueue;

public class ApiTest extends BaseTest{
    public static String bookingID = null;

//    Positive stest cases
//    1. Get - Fetch all record
//    2. Post - Create New Booking
//    3. Get - Fetch record with ID
//    4. Put - Update record
//    5. Delete - delete record
    @Test(priority = 1)
    public void fetchAllRecord(){
        HttpMethod obj = new HttpMethod();
        Response response = obj.get(ConfigFactory.getConfig().BASE_URL() + ConfigFactory.getConfig().BOOKING_ENDPOINT())
                .then().statusCode(200).extract().response();
        System.out.println("Response from createAuth :" + response.asString());
        System.out.println("Fetch working..");
    }

    @Test(priority = 2)
    public void createRecord() throws Exception {
        Response response = CreateBookingApi.createBooking(token);
        String temp = response.asString();
        JsonPath jsonPath = new JsonPath(temp);
        bookingID = jsonPath.getString("bookingid");
    }

    @Test(priority = 3)
    public void fetchParticularRecordById() throws Exception {
        createRecord();
        HttpMethod obj = new HttpMethod();
        Response response = obj.getWithPathParam(ConfigFactory.getConfig().BASE_URL() + ConfigFactory.getConfig().BOOKING_ENDPOINT(), bookingID)
                .then().statusCode(200).log().all().extract().response();
        System.out.println("Response from createAuth :" + response.asString());
        System.out.println("Fetch single working..");
    }

    @Test(priority = 4)
    public void updateParticularRecord() throws Exception {
        createRecord();
        Response response = UpdateBookingApi.updateBooking(token, bookingID);

    }

    @Test(priority = 5)
    public void deleteParticularRecord() throws Exception {
        createRecord();
        HttpMethod obj = new HttpMethod();
        Response response = obj.delete(token, ConfigFactory.getConfig().BASE_URL() + ConfigFactory.getConfig().BOOKING_ENDPOINT(), bookingID)
                .then().statusCode(201).assertThat().body(containsString("Created")).log().all().extract().response();
        System.out.println("Response from createAuth :" + response.asString());
        System.out.println("Fetch single working..");
    }

//    Negative Test case -
//    Get - fetch booking with invalid id
    @Test(priority = 6)
    public void fetchParticularRecordByInvalidId(){
        HttpMethod obj = new HttpMethod();
        Response response = obj.getWithPathParam(ConfigFactory.getConfig().BASE_URL() + ConfigFactory.getConfig().BOOKING_ENDPOINT(), "3812381371")
                .then().statusCode(404).assertThat().body(containsString("Not Found")).log().all().extract().response();
        System.out.println("Response from createAuth :" + response.asString());
        System.out.println("Fetch single working..");
    }





}
