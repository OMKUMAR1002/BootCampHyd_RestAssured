package RestAssuredDemo;

import static io.restassured.RestAssured.*;

public class BDDValidation {

    public static void main(String[] args) {

        given()
        .when()
            .get("https://api.restful-api.dev/objects")
        .then()
            .statusCode(200);  
    }
}