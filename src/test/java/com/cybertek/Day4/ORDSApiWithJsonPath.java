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

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionIDs2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryNameWithRegionIDs2);

    }

    @DisplayName("GET request / employees with query param")
    @Test
    public void test2(){
        
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");
        
        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("employeeITProgs = " + employeeITProgs);
        
        //get me first name of employees who is making more than 10000
        List<String> employeesMoreThan10000 = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("employeesMoreThan10000 = " + employeesMoreThan10000);
    }



}
