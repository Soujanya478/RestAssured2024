package POSTAPITestWithAuthAPI;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials;

public class AuthAPItest {
	
	@Test
	public void AuthTokenTest_WithJson()
	{
		
		RestAssured.baseURI =  "https://restful-booker.herokuapp.com";
		String tokenId = given()
			.contentType(ContentType.JSON)
				.body("{\n"
				    +"    \"username\" : \"admin\",\n"
				    +"   \"password\" : \"password123\"\n"
				    		+"}")
								.when()
									.post("/auth")
											.then()
											.assertThat()
												.statusCode(200)
													.extract()
													.path("token");
		
		System.out.println("token ID : " +tokenId);
		Assert.assertNotNull(tokenId);

	}
	
	@Test
	public void AuthTokenTest_WithJsonFile() {
		RestAssured.baseURI ="https://restful-booker.herokuapp.com";
		
	String tokenId = given()
			.contentType(ContentType.JSON)
				.body(new File("./src/test/resources/Jsons/auth.json"))
					.when()
						.post("/auth")
							.then()
								.assertThat()
									.extract()
										.path("token");
		
		System.out.println("token ID : " +tokenId);
		Assert.assertNotNull(tokenId);
			
	}
	
	//WIP - To be done with ObjectMapper class that converts POJO to JSON
	
	@Test
	public void AuthTokenTest_WithPojoClass() {
		RestAssured.baseURI ="https://restful-booker.herokuapp.com";
		
		Credentials cred = new Credentials("admin","password123");
		
	String tokenId = given()
			.contentType(ContentType.JSON)
				.body(cred) // pojo to json --> ObjectMapper(Jackson) --> Serialization
					.when()
						.post("/auth")
							.then()
								.assertThat()
									.extract()
										.path("token");
		
		System.out.println("token ID : " +tokenId);
		Assert.assertNotNull(tokenId);
		
		//json --> Pojo --> Deserialization
			
	}
}
