package Testcases.Testcase1;

import Testcases.pojo.Login;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Students {


    @Test
    public void shoudldbelogin(){
        Login login = new Login();
        login.setEmail("hatem@example.com");
        login.setPassword("Test1234");

        given().baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(login)
        .when()
                .post("/api/v1/users/login")
        .then()
                .assertThat().extract().response();


    }
}
