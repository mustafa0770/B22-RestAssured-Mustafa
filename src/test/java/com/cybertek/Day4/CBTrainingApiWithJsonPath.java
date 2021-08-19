package com.cybertek.Day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://api.cybertektraining.com/";
    }
    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){

        //send a get request to student id 23401 as a path parameter and accept header application/json
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 23401)
                .when().get("/student/{id}");

        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        assertEquals(response.statusCode(), 200);
        assertEquals("application/json;charset=UTF-8", response.header("Content-Type"));
        assertEquals("gzip", response.header("Content-Encoding"));

        //verify Date header exists
        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();


                //assert that
                //firstName Vera
                String firstName = jsonPath.getString("students[0].firstName");
                System.out.println("firstName = " + firstName);
                assertEquals("Vera", firstName);

                //batch 14
                int batch = jsonPath.getInt("students[0].batch");
                System.out.println("batch = " + batch);
                assertEquals(14, batch);

                //section 12
                String section = jsonPath.getString("students[0].section");
                System.out.println("section = " + section);
                assertEquals("12", section);

                //emailAddress aaa@gmail.com
                String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
                System.out.println("emailAddress = " + emailAddress);
                assertEquals("aaa@gmail.com", emailAddress);

                //companyName Cybertek
                String companyName = jsonPath.getString("students[0].company.companyName");
                System.out.println("companyName = " + companyName);
                assertEquals("Cybertek", companyName);

                //state IL
                String state = jsonPath.getString("students[0].company.address.state");
                System.out.println("state = " + state);
                assertEquals("IL", state);

                //zipCode 60606
                int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
                System.out.println("zipCode = " + zipCode);
                assertEquals(60606, zipCode);




    }

}