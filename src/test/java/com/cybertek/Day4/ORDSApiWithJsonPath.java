package com.cybertek.Day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.RescaleOp;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {
    @DisplayName("GET request to Countries")
    @Test
    public void test1(){
        Response response = get("/countries");

        //get the second country name with JsonPath

        //to use JsonPath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();
        
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);
        
        //get all Country IDs
        //items.country_id
        List<String> allCountryIDs = jsonPath.getList("items.country_id");
        System.out.println("allCountryIDs = " + allCountryIDs);

    }


}
