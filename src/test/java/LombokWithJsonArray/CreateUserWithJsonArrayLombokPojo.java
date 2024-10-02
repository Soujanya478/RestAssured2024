package LombokWithJsonArray;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

public class CreateUserWithJsonArrayLombokPojo {
	
	public String getRandomEmailID() {
		return "APIAutomation"+System.currentTimeMillis()+"@test.com";
	}
	
	@Test
	public void CreateUserWithJsonArrayLombokPojo() {
		
		RestAssured.baseURI="httpbin.org";
		
		User.UserData user1 = new User.UserData(1, getRandomEmailID(), "soujanya","ambati","https:://reqres.in/img/faces/3-image.jpg" );
		User.UserData user2 = new User.UserData(2, getRandomEmailID(), "soujanya","ambati","https:://reqres.in/img/faces/3-image.jpg" );
		User.UserData user3 = new User.UserData(3, getRandomEmailID(), "soujanya","ambati","https:://reqres.in/img/faces/3-image.jpg" );
		User.UserData user4 = new User.UserData(4, getRandomEmailID(), "soujanya","ambati","https:://reqres.in/img/faces/3-image.jpg" );
		User.UserData user5 = new User.UserData(5, getRandomEmailID(), "soujanya","ambati","https:://reqres.in/img/faces/3-image.jpg" );
		
		User.Support support=new User.Support("https:://reqres.in/support-heading", " To keep ReqRes free. contributions towards service costs are appreciated");
		
		User user = new User(
				1,
				6,
				12,
				2,
				Arrays.asList(user1, user2, user3, user4, user5),
				support);
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(user)
				.when().log().all()
					.post("/post")
						.then().log().all()
						.assertThat()
							.statusCode(200);
							
				
		
		
		
		
		
	}
	

}
