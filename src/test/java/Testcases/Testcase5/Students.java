package Testcases.Testcase5;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class Students {


    @Test
    public void shoudldbelogin(){


        //add File class
        //add mutipart with 2 paratmer
        // 1 parameter   > key
        // 2 parameter   > File path
        File json = new File("src/test/resources/Login.json");


        given().baseUri("https://todo.qacart.com")
                .multiPart("file",json)
                .when()
                .post("/api/v1/users/login")
                .then()
                .assertThat().extract().response();


    }
}


