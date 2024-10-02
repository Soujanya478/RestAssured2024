package FakeStoreAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import FakeStoreAPI.User.Address;
import FakeStoreAPI.User.Address.GeoLocation;

public class CreateFakeUserTest {
	

	@Test
	public void createUserTest() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		User.Address.GeoLocation geolocation = new User.Address.GeoLocation("-98.900", "98.77");
		
		User.Address address = new User.Address("Vijayawada","new Road",2343,"08044",geolocation );
		
		User.Name name = new User.Name("Soujanya","Ambati");
		
		User user = new User("soujanyaAPITesting@gmail.com","Soujanyambati","soujanya@123","999-987-9879",name,address);
		
		
		given().log().all()
			.body(user)
				.when().log().all()
					.post("/users")
						.then().log().all()
							.assertThat()
								.statusCode(200);
		
	}
	
	@Test
	public void createUserTest_LombokBuilder() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		User.Address.GeoLocation geolocation = new User.Address.GeoLocation.GeoLocationBuilder()
																				.lat("-98.087")
	                                                                        	.longitude("-987.90")
		                                                                        .build();
		
		User.Address address = new User.Address.AddressBuilder()
													.City("Vijayawada")
													.Street("Ambedhkar road")
													.number(2340)
													.zipcode("08044")
													.GeoLocation(geolocation)
													.build();
		
	User.Name name=	new User.Name.NameBuilder()
									.firstname("Soujanya")
									.lastname("ambati")
									.build();
	
	User user = new User.UserBuilder()
							.email("soujanya@test.com")
							.username("Soujanya")
							.password("Soujanya@123")
							.phone("524-875-7252")
							.Name(name)
							.Address(address)
							.build();

	
	given().log().all()
	.body(user)
		.when().log().all()
			.post("/users")
				.then()
					.assertThat()
						.statusCode(200);
													

}
	
}
