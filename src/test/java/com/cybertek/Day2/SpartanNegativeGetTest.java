package com.cybertek.Day2;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init(){
        //Save baseurl inside this variable so that we don't need
        baseURI = "http://3.239.15.23:8000";
    }

     /*TASK
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;application/xml;charset=UTF-8
    */

    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML)
                .when()
                .get("api/spartans/10");

        //verify status code
        assertEquals(406, response.statusCode());

        //verify content type is application/xml
        assertEquals(response.contentType(), "application/xml;charset=UTF-8");
    }



}
