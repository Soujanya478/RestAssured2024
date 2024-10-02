package GETAPITests_WithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseData {
	
	@Test
	public void getContactAPITest_WithInvalidToken() {
		
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		
		given().log().all()
		    .header("Authorization", "Bearer 4ytyutyyh")
		    		.when().log().all()
		    				.get("/contacts")
		    					.then().log().all()
		    						.assertThat()
		    							.statusCode(401)
		    								.and()
		    										.body("error", equalTo("Please authenticate."));
		
	}
		@Test
		public void getContactAPITest_WithInvalidToken_ExtractFromResponse() {
			
			RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
			
			String  errorMsg = given().log().all()
			    .header("Authorization", "Bearer 4ytyutyyh")
			    		.when().log().all()
			    				.get("/contacts")
			    					.then().log().all()
			    						.extract()
			    							.response()
			    								.path("error");
			
			System.out.println(errorMsg);
			Assert.assertEquals(errorMsg, "Please authenticate.");
			
		}
		
		@Test
		public void getSingleUser_fetchUserData() {
			
			RestAssured.baseURI= "https://gorest.co.in";
			
			Response response = given()
				.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
					.when()
						.get("public/v2/users/7386796");
			
			System.out.println("Status Code " + response.statusCode());
			System.out.println("Status Line " +response.statusLine());
			response.prettyPrint();
			
			JsonPath js = response.jsonPath();
			int UserId = js.getInt("id");
			System.out.println(UserId);
			
			String name = js.getString("name");
			System.out.println(name);
			
			String status = js.getString("status");
					System.out.println(status);
					
		}
			
		@Test
		public void getMutlipleUser_fetchUserData() {
			
			RestAssured.baseURI="https://gorest.co.in";
			Response response = given()
				.header("Authorization", "Bearer 05946aeb76236f86df2e8eb428054816b5b49e2c506b6522fb085a3d05519394")
					.when()	
						.get("public/v2/users");
			
			
			System.out.println("Status Code " +response.statusCode() );
			System.out.println(response.prettyPrint());
			JsonPath js = response.jsonPath();
			List<Integer> Id = js.getList("id");
			System.out.println(Id);
			
			List<String> names = js.getList("name");
			System.out.println(names);
			
			for(Integer e : Id)
			{
				System.out.println(e);
			}
		
		}
		
		@Test
		public void getProduct_fetchNestedData()
		{
			RestAssured.baseURI="https://fakestoreapi.com";
			
			Response response = given()
			.when()
			.get("/products");
			
			System.out.println(response.statusCode());
			System.out.println(response.statusLine());
			System.out.println(response.prettyPrint());
			
			JsonPath js = response.jsonPath();
			
			List<Integer> ids = js.getList("id");
			System.out.println(ids);
			List<Float> prices = js.getList("price");
			System.out.println(prices);
			
			List<Float> rates = js.getList("rating.rate");
			System.out.println(rates);
			
			List<Integer> Count = js.getList("rating.count");
			System.out.println(Count);
			
			for(int i=0;i<ids.size();i++ ) {
				int id = ids.get(i);
				Object price = prices.get(i);
				Object rate = rates.get(i);
				int count = Count.get(i);
				
				System.out.println("ID :  " +id +" Price : " +price +" Rate : " +rate +" Count : " +count);
				
				Assert.assertTrue(rates.contains(4.8f));
				
			}
		}
		
			
						
			
			
}
