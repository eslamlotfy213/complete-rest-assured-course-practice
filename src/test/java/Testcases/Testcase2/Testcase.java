package Testcases.Testcase2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Testcase {

    @Test
    public void test1(){
    Response res=   given().baseUri("https://65253a7667cfb1e59ce6ea08.mockapi.io")
            .log().method()
            .log().parameters()
                  .when().get("/api/v1/users")
                  .then().log().ifError().assertThat()
            .statusCode(200).extract().response();


       String name = JsonPath.from(res.asString()).getString("[0].name");

        System.out.println(name);

}






}
