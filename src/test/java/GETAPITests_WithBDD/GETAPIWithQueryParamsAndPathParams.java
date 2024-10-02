package GETAPITests_WithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GETAPIWithQueryParamsAndPathParams {
	
	@Test
	public void getUserQueryParams() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
			.queryParam("name", "trivedi")
				.queryParam("status", "inactive")
					.when().log().all()
						.get("/public/v2/users")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.contentType(ContentType.JSON);
	}
	
	@DataProvider
	public Object[][] getUserInfo() {
		return new Object[][] {
			{"trivedi","active","male"}
		};
	}

	@Test(dataProvider = "getUserInfo")
	public void getUserQueryParams_withHashMap(String name, String status, String gender) {
		
		RestAssured.baseURI = "https://gorest.co.in";
//		Map<String, String> queryparam = new HashMap<String,String>();
//		queryparam.put("name", "trivedi");
//		queryparam.put("status","active");
//		queryparam.put("gender","male");
		
		Map<String, String> queryparam = Map.of(
				"name",name, "status",status,"gender",gender );
		
		given().log().all()
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
			.queryParams(queryparam)
					.when().log().all()
						.get("/public/v2/users")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.contentType(ContentType.JSON);
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"7381532"},
		//	{"7381533"},
		//	{"7381534"}
			
		};
	}
	@Test(dataProvider = "getUserData")
	public void getUserAPI_WithPathParams(String UserId) {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given()
			.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
				.pathParam("userid", UserId)
					.when().log().all()
						.get("public/v2/users/{userid}/posts")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
										.and()
											.body("title", hasItem("Comitatus reiciendis congregatio tandem temeritas certo crebro."));
	}
}
