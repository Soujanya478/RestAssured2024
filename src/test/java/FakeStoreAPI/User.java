package FakeStoreAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private String email;
	private String username;
	private String password;
	private String phone;
	private Name Name;
	private Address Address;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Name{
		private String firstname;
		private String lastname;
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Address{
		private String City;
		private String Street;
		private int number;
		private String zipcode;
		private GeoLocation GeoLocation;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class GeoLocation{
		private String lat;
		@JsonProperty("long")
		private String longitude;
		
	}
	}
}
