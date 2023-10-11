package Testcases.Testcase4;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class Students {


    @Test
    public void shoudldbelogin(){


        //add formurlencode from postman
        // add hashmap
        //add formparams
        //removebody as now we have formparams
        //add content type ContentType.URLENC
        HashMap<String,String> form = new HashMap<>();
        form.put("foo","123");


        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.URLENC)
                .formParams(form)
                .when()
                .post("/api/v1/users/login")
                .then()
                .assertThat().extract().response();


    }
}


