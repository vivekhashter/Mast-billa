package tests;

import config.ConfigFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.internal.LogManagerStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import testAPI.CreateTokenApi;
import utils.ExtentManager;
import utils.HttpMethod;
import utils.JSON_Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Method;


public class BaseTest {
    protected final Faker faker = new Faker();
    protected String token;
 public static Response response;

    @BeforeSuite
    public static void startSuite(){
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

    }

    @BeforeMethod
    void startTest(Method method) throws Exception {
        health_Check();
        response = CreateTokenApi.createAuthToken();
        String temp = response.asString();
        JsonPath jsonPath = new JsonPath(temp);
        token = jsonPath.getString("token");
        JSON_Utils.updateTokenInJson(token);
        System.out.println("Token : " + token);
    }


    public static void health_Check() throws Exception {
        HttpMethod obj = new HttpMethod();
        Response response = obj.get(ConfigFactory.getConfig().BASE_URL() + ConfigFactory.getConfig().PING_ENDPOINT())
                .then().statusCode(201).log().all().extract().response();
        System.out.println("Health check successful");
    }



}
