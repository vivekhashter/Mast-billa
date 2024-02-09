package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpMethod {

    private RequestSpecification request;

    public static Response response;


    public Response get(String url){
       response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json").log().all().when().get(url);
       return response;
    }

    public Response getWithQueryParam(String url, String paraName1, String paraName2, String paraVal1, String paraVal2){
        response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json").queryParam(paraName1, paraVal1)
                .queryParam(paraName2, paraVal2).log().all().when().get(url);
        return response;
    }

    public Response getWithPathParam(String url, String id){
        response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json").log().all().when().get(url+id);
        return response;
    }


    public Response post(String body, String url){
        response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json").log().all().body(body).when().post(url);
        return response;
    }

    public Response postWithToken(String token, String body, String url){
        response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization", "Bearer " + token).log().all().body(body).when().post(url);
        return response;
    }
    public Response put(String token, String body, String url, String id){
        response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json").header("Cookie", "token="+token)
                .log().all().body(body).when().put(url+id);
        return response;
    }

    public Response delete(String token, String url, String id){
        response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json").header("Cookie", "token=" + token)
                .log().all().when().delete(url+id);
        return response;
    }
}
