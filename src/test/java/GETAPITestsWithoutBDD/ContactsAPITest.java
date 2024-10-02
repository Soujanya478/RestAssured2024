package GETAPITestsWithoutBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ContactsAPITest {
	
	@Test
	public void getContactsTest() {
	
	RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
	RequestSpecification request = RestAssured.given();
	request.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NmQ5MGY5YTJiMTUyNDAwMTM3MDBkNWUiLCJpYXQiOjE3MjU1MDEzMzh9.1WNpYXjI8FOVQ8xHenfgyidouDcwlLT15UGhaqbt9gc");
	Response response = request.get("/contacts");
	int statusCode = response.statusCode();
	System.out.println("Status code" +statusCode);
	
	String statusLine = response.statusLine();
	System.out.println("Status Line" +statusLine);
	
	Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
	String resBody = response.prettyPrint();
	System.out.println(resBody);
	}
}
