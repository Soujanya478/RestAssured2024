package POSTAPITestWithAuthAPI;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserAPITest {
	
	@Test
	public void AuthTokenTest_WithJSONFile() {
		RestAssured.baseURI ="https://gorest.co.in";
		
		
	Integer userId = given().log().all()
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
				.contentType(ContentType.JSON)
		        	.body(new File("./src/test/resources/Jsons/user.json"))
					.when().log().all()
						.post("/public/v2/users")
							.then().log().all()
								.assertThat()
									.statusCode(201)
									   .extract()
										  .path("id");
		
		System.out.println("User ID : " +userId);
		Assert.assertNotNull(userId);
			
	}

}
