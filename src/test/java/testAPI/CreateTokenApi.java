package testAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import config.ConfigFactory;
import io.restassured.response.Response;
import pojo.CreateTokenPOJO;
import tests.BaseTest;
import utils.HttpMethod;
import utils.JSONPojoUtils;

public class CreateTokenApi extends BaseTest {
    private CreateTokenApi() {}

    public static Response createAuthToken() throws Exception {
        CreateTokenPOJO dataset = JSONPojoUtils.authToken("AuthToken_JSON.json");
        String jsonBody = objectToJson(dataset);
//        System.out.println("API Body : " + jsonBody);
        HttpMethod obj = new HttpMethod();
        Response response = obj.post(jsonBody, (ConfigFactory.getConfig().BASE_URL() + ConfigFactory.getConfig().AUTH_ENDPOINT()))
                .then().statusCode(200).log().all().extract().response();
        System.out.println("Response from createAuth :" + response);
        System.out.println("Create working..");
        return response;
    }

    public static String objectToJson(CreateTokenPOJO dataObj) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(dataObj);
    }
}
