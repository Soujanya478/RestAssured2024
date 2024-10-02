package BookingAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookingAPIFeatureTests {
	
	String tokenId;
	
	@BeforeMethod
	public void getToken() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		 tokenId = given()
				.contentType(ContentType.JSON)
					.body(new File("src/test/resources/Jsons/auth.json"))
						.when()
							.post("/auth")
								.then()
									.assertThat()
										.extract()
											.path("token");
			
			System.out.println("token ID : " +tokenId);
			Assert.assertNotNull(tokenId);
	}

	@Test
	public void getBookingIdTest()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		Response response = given().log().all()
			.when().log().all()
				.get("/booking")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.extract()
										.response();
		
		JsonPath js = response.jsonPath();
		
		List<Integer> BookingIds = js.getList("bookingid");
		
		System.out.println("All Booking Id's are : "+BookingIds.size());
		
		for(Integer Id : BookingIds) {
			Assert.assertNotNull(Id);
		}	
										
	}
	
	@Test
	public void getBookingidTest	() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		int newBookingId = CreateBooking();
		
		given()
				.pathParam("bookingId", newBookingId)
					.when().log().all()
						.get("/booking/{bookingId}")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.body("firstname", equalTo("Jim"))
												.and()
												.body("bookingdates.checkin", equalTo("2018-01-01"));
	}
	
	@Test
	public void createBookingTest()
	{
		Assert.assertNotNull(CreateBooking());
	}
	
	@Test
	public void updateBookingTest()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		int newBookingId =  CreateBooking();
		
		given()
		.pathParam("bookingId", newBookingId)
			.when().log().all()
				.get("/booking/{bookingId}")
					.then().log().all()
						.assertThat()
							.statusCode(200);
		
	given()
		.pathParam("bookingId", newBookingId)
		.contentType(ContentType.JSON)
		.header("Cookie","token = "+tokenId)
		.body("{\n"
				+ "    \"firstname\" : \"James\",\n"
				+ "    \"lastname\" : \"Brown\",\n"
				+ "    \"totalprice\" : 118,\n"
				+ "    \"depositpaid\" : true,\n"
				+ "    \"bookingdates\" : {\n"
				+ "        \"checkin\" : \"2018-01-01\",\n"
				+ "        \"checkout\" : \"2019-01-01\"\n"
				+ "    },\n"
				+ "    \"additionalneeds\" : \"Dinner\"	\n"
				+ "}")
					.when()
						.put("/booking/{bookingId}")
							.then()
								.assertThat()
									.body("firstname", equalTo("James"))
									.and()
									.body("additionalneeds", equalTo("Dinner"));
	}
	
	@Test
	public void partialBookingTest()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		int newBookingId =  CreateBooking();
		
		given()
		.pathParam("bookingId", newBookingId)
			.when().log().all()
				.get("/booking/{bookingId}")
					.then().log().all()
						.assertThat()
							.statusCode(200);
		
	given()
		.pathParam("bookingId", newBookingId)
		.contentType(ContentType.JSON)
		.header("Cookie","token = "+tokenId)
		.body("{\n"
				+ "    \"firstname\" : \"soujanya\",\n"
				+ "    \"lastname\" : \"Automation\",\n"
				+ "}")
					.when()
						.patch("/booking/{bookingId}")
							.then()
								.assertThat()
									.body("firstname", equalTo("soujanya"))
									.body("lastname", equalTo("Automation"));
					
	}
													
	
	public int CreateBooking() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		 int bookingid = given()
				.contentType(ContentType.JSON)
					.body("{\n"
							+ "    \"firstname\" : \"Jim\",\n"
							+ "    \"lastname\" : \"Brown\",\n"
							+ "    \"totalprice\" : 111,\n"
							+ "    \"depositpaid\" : true,\n"
							+ "    \"bookingdates\" : {\n"
							+ "        \"checkin\" : \"2018-01-01\",\n"
							+ "        \"checkout\" : \"2019-01-01\"\n"
							+ "    },\n"
							+ "    \"additionalneeds\" : \"Breakfast\"	\n"
							+ "}")
						.when()
							.post("/booking")
								.then()
										.extract()
											.path("bookingid");
			
			System.out.println("bookingid : " +bookingid);	
			return bookingid;
		
		
	}

}
