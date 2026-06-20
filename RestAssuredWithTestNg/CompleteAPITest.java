package RestAssuredTestNG;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CompleteAPITest {

	public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        RestAssured.basePath="";
        
	}
	@Test(priority=1)
	public void testTraditionalApproach() {
		JSONObject requestBody = new JSONObject();
		 requestBody.put("title", "Traditional Post");
	        requestBody.put("body", "Testing Traditional approach");
	        requestBody.put("userId", 1);
	        
	        RequestSpecification request = given();
	        request.header("Content-Type", "application/json");
            request.body(requestBody.toString());
            Response response = request.post("https://jsonplaceholder.typicode.com/posts");
            printResponseDetails(response,"Traditional");
            
	        
	}
	@Test(priority = 2)
	
    public void testBDDApproach() throws Exception {
		Thread.sleep(2000);
		JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Test Post BDD");
        requestBody.put("body", "This is a test Post body using BDD");
        requestBody.put("userId", 2);
        given()
        .header("Content-Type", "application/json")
        .body(requestBody.toString())
    .when()
        .post("https://jsonplaceholder.typicode.com/posts")
    .then()
        .statusCode(201)
        .statusLine(containsString("201"))
        .contentType("application/json")
        .body("title", equalTo("Test Post BDD"))
        .body("userId", equalTo(2))
        .time(lessThan(6000L))
        .log().all();

	}
	
	@Test(priority = 3)
	public void testBDDApproachWithExtraction() {
		  JSONObject requestBody = new JSONObject();
	        requestBody.put("title", " BDD With Extraction");
	        requestBody.put("body", "Testing BDD with response extraction");
	        requestBody.put("userId", 3);
	  Response response=given()
			  .header("Content-Type", "application/json")
	          .body(requestBody.toString())
	        .when().post("https://jsonplaceholder.typicode.com/posts")
	        .then().statusCode(201).extract().response();
	  
	        printResponseDetails(response,"BDD Extraction")	;
	}
	
	private  void printResponseDetails(Response response, String string) {
		   System.out.println("Status code: " + response.getStatusCode());
	        System.out.println("Complete Response: " + response.asPrettyString());

	        System.out.println("Response Body: ");
	        System.out.println(response.getBody().asPrettyString());

	        System.out.println("Status Line: " + response.getStatusLine());

	      
	        System.out.println("Content Type: " + response.getContentType());

	       
	        System.out.println("Response Time: " + response.getTime()+"ms");
	      
	}

	
}
