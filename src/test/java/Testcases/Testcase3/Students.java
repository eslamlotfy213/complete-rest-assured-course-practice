package Testcases.Testcase3;

import Testcases.pojo.Login;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


    public class Students {


        @Test
        public void shoudldbelogin(){
            //File body = new File("src/test/resources/Login.json");

            HashMap<String,String> body2 = new HashMap<>();
            body2.put("email","hatem@example.com");
            body2.put("password","Test1234");

            given().baseUri("https://todo.qacart.com")
                    .contentType(ContentType.JSON)
                    .body(body2)
                    .when()
                    .post("/api/v1/users/login")
                    .then()
                    .assertThat().extract().response();


        }
    }


