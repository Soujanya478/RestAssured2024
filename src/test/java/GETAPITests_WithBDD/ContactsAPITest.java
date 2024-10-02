package GETAPITests_WithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ContactsAPITest {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
	}
	
	@Test
	public void getContactAPITest() {
		
		given().log().all()
		    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmQ5MGY5YTJiMTUyNDAwMTM3MDBkNWUiLCJpYXQiOjE3MjU1MDEzMzh9.1WNpYXjI8FOVQ8xHenfgyidouDcwlLT15UGhaqbt9gc")
		    		.when().log().all()
		    				.get("/contacts")
		    					.then().log().all()
		    						.assertThat()
		    							.statusCode(200)
		    								.and()
		    									.statusLine("HTTP/1.1 200 OK")
		    								.and()
		    									.contentType(ContentType.JSON)
											.and()
												.body("$.size()",equalTo(1));
	}
	
	@Test
	public void getContactAPITest_WithInvalidToken() {
		
		given().log().all()
		    .header("Authorization", "Bearer 4ytyutyyh")
		    		.when().log().all()
		    				.get("/contacts")
		    					.then().log().all()
		    						.assertThat()
		    							.statusCode(401);
		    								
	}

}
