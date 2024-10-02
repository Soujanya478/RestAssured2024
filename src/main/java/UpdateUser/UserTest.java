package UpdateUser;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTest {
	
	public String RandomEmailID()
	{
		return "APIAutomation"+System.currentTimeMillis()+"@test.com";
	}
	
	@Test
	public void updateUserWith_Lombok() {
		
		//Payload updated using lombok, with the lombok pre-defined annotations constructor with all args and no args will be created
		// By creating the object to the lombok class, we can pass all arguments and store it in the object reference, we can use that reference variable in the body placeholder
		
		
		RestAssured.baseURI="https://gorest.co.in";
		
		//User user = new User("Soujanya", "Soujanya@test.com", "Female", "Active");
		
		UserLombok user = new UserLombok("Sam",RandomEmailID(), "female", "Active");
		
		Response postResponse = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
			.body(user)
				.when().log().all()
					.post("/public/v2/users");
		
		postResponse.prettyPrint();
		int UserId = postResponse.jsonPath().get("id");
		System.out.println("User Id = "+UserId);
	}
	
	@Test
	public void updateUserWith_Builder() {
		
		//Creating user class object using lombok builder pattern
		
		RestAssured.baseURI="https://gorest.co.in";
		
		//User user = new User("Soujanya", "Soujanya@test.com", "Female", "Active");
		
		//UserLombok user = new UserLombok("Sam", "Sam@test.com", "female", "Active");
		
		UserLombok userlombok = new UserLombok.UserLombokBuilder()
			.name("Soujanya")
			.email(RandomEmailID())
			.gender("female")
			.status("Active")
			.build();
		
		Response postResponse = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
			.body(userlombok)
				.when().log().all()
					.post("/public/v2/users");
		
		postResponse.prettyPrint();
		int UserId = postResponse.jsonPath().get("id");
		System.out.println("User Id = "+UserId);
	}
}
