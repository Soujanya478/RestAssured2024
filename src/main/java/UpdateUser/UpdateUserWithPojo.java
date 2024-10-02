package UpdateUser;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserWithPojo {
	
	public String getRandomEmail()
	{
		return "apitest"+System.currentTimeMillis()+"@test.com";
	}
	
	//pojo class, we have constructor defined and creating the object with the constructor and passing that as a body
	@Test
	public void updateUserWithPojo() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		User user = new User("Soujanya", getRandomEmail(), "Female", "Active");
		
		Response postResponse = given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
			.body(user)
				.when().log().all()
					.post("/public/v2/users");
		
		postResponse.prettyPrint();
		int UserId = postResponse.jsonPath().get("id");
		System.out.println("User Id = "+UserId);
		
		//Update API
		user.setname("Soujanya Ambati");
		user.setStatus("active");
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
			.body(user)
			   .when().log().all()
			   		.put("/public/v2/users/"+UserId)
			   			.then().log().all()
			   				.assertThat()
			   					.statusCode(200)
			   					.and()
			   					.body("name", equalTo(user.getname()))
			   					.and()
			   					.body("status", equalTo(user.getStatus()));

		
	}

}
	
