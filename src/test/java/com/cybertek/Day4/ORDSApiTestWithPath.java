package com.cybertek.Day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


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
        System.out.println("response limit = " +response.path("limit"));
        System.out.println("response hasMore = " +response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("first CountryId = " +firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("second Country name = " +secondCountryName);

        //print "http://3.239.15.23:1000/ords/hr/countries/CA"
        String hrefValue = response.path("items[2].links[0].href");
        System.out.println("hrefValue = " +hrefValue);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("Country Names = " +countryNames);


    }
}