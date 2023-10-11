package Testcases.Autorization;

import Testcases.pojo.Login;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class TestAuth {


    JsonPath jsonPath;

    String token;

    SessionFilter sessionFilter = new SessionFilter();

    @Test
    public void getToken(){
        Login login = new Login();
        login.setEmail("hatem@example.com");
        login.setPassword("Test1234");

      Response response= given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(login)
                .filter(sessionFilter).when().post("/api/v1/users/login")
                .then().log().all().statusCode(200).extract().response();

              jsonPath = new JsonPath(response.asString());
             token = jsonPath.getString("access_token");

        System.out.println("token " + token);
    }


    @Test
    public void shoudldbelogin(){
        given()
                .baseUri("https://todo.qacart.com")
                .log().headers()
                .auth().oauth2(token)
               // .headers("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0Zjc5NDY5ZDg1OTA2MDAxNDhlM2UyMCIsImZpcnN0TmFtZSI6IkhhdGVtIiwiaWF0IjoxNjk3MDI3OTU2fQ.25rRX3Kt3gEq-2Esz6Pic3rezVgwalQf5hklET5-dqM")
                .filter(sessionFilter).when()
                .get("/api/v1/tasks")
         .then().log().all()
                .statusCode(200);
    }
}
