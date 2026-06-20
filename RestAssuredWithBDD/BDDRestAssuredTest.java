package RestAssuredDemo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BDDRestAssuredTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void testpostrequestBDD() {

        System.out.println("===BDD Approach===\n");

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Test Post BDD");
        requestBody.put("body", "This is a test Post body using BDD");
        requestBody.put("userId", 2);

        System.out.println("Request Body: " + requestBody.toString());

        given()
            .log().all()
            .header("Content-Type", "application/json")
            .body(requestBody.toString())
        .when()
            .post("/posts")
        .then()
            .log().all()
            .statusCode(201)
            .statusLine(containsString("201"))
            .contentType("application/json")
            .body("title", equalTo("Test Post BDD"))
            .body("body", equalTo("This is a test Post body using BDD"))
            .body("userId", equalTo(2))
            .body("id", notNullValue())
            .time(lessThan(5000L));

        System.out.println("completed BDD approach");
    }
}
