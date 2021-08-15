package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HrGetRequests {

    @BeforeAll
    public static void init(){
        //Save baseurl inside this variable so that we don't need
        RestAssured.baseURI = "http://3.239.15.23:1000/ords/hr";

    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){

        Response response = RestAssured.get("/regions");

        //print the status code
        System.out.println(response.statusCode());
    }

    /*
        Given accept type is json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains Americas
     */

    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                        .get("/regions/2");

        //verify status code
        Assertions.assertEquals(200, response.statusCode());

        //verify content type
        Assertions.assertEquals("application/json", response.contentType());

        response.prettyPrint();

        //verify body contains Americas
        Assertions.assertTrue(response.body().asString().contains("Americas"));

    }

}
