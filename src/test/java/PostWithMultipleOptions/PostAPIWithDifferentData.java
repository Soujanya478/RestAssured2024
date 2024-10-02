package PostWithMultipleOptions;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class PostAPIWithDifferentData {
	
	public void BodyWithTextTest() {
		
		RestAssured.baseURI="https://postman-echo.com";
		
		String payload = "This is request payload";
		
		given()
					.body(payload)
						.when()
							.post("/users")
								.then()
									.assertThat()
										.statusCode(200);
	}

}
