package com.cybertek.Day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.swing.*;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response limit = " + response.path("limit"));
        System.out.println("response hasMore = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("first CountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("second Country name = " + secondCountryName);

        //print "http://3.239.15.23:1000/ords/hr/countries/CA"
        String hrefValue = response.path("items[2].links[0].href");
        System.out.println("hrefValue = " + hrefValue);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("Country Names = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> allRegionsIDs = response.path("items.region_id");

        for (Integer regionsID : allRegionsIDs) {
            System.out.println("regionsID = " + regionsID);
            assertEquals(2, regionsID);

        }

    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);
        }
        //TASK
        //Print each Name of IT_PROGs

    }
}