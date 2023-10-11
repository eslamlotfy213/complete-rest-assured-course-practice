package Testcases.Spec;

import Testcases.pojo.Login;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class TestRequestSpecification {

    JsonPath jsonPath;

    String token;

    SessionFilter sessionFilter = new SessionFilter();
    Login login;
    io.restassured.specification.RequestSpecification request;

    @BeforeClass
    public void prerequesit()
    {
        //1 Build   -Request Spec Builder-
        request = new RequestSpecBuilder()
                .setBaseUri("https://todo.qacart.com")
                .setContentType(ContentType.JSON).build();
    }


    @Test(priority = 1)
    public void getToken(){
        login = new Login();
        login.setEmail("hatem@example.com");
        login.setPassword("Test1234");

      RequestSpecification res= given().spec(request).body(login);

      Response response= res.filter(sessionFilter).when().post("/api/v1/users/login").then().log().all().statusCode(200).extract().response();

      jsonPath = new JsonPath(response.asString());
      token = jsonPath.getString("access_token");

      System.out.println("token " + token);
    }


    @Test(priority = 2)
    public void shoudldbelogin(){
        given()
                .baseUri("https://todo.qacart.com")
                .log().headers()
                .auth().oauth2(token)
                .filter(sessionFilter)
        .when()
                .get("/api/v1/tasks")
        .then().log().all().statusCode(200).extract().response();
    }


}
