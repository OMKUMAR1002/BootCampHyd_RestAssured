package RestAssuredDemo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestTest {

    public static void main(String[] args) {
    	
        Response response = RestAssured.get("https://dummy.restapiexample.com/api/v1/employees");

        System.out.println("Status code:" + response.getStatusCode());
        System.out.println("Complete Response:" + response.asPrettyString());

        System.out.println("Response Body: ");
        System.out.println(response.getBody().asPrettyString());

        System.out.println("Status Line:" + response.getStatusLine());
    }
}