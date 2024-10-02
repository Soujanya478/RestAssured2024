package LombokWithJsonArray;

import io.restassured.RestAssured;

public class CreateAssignmentUserWithJsonArrayLombok {
	
	public void CreateAssignmentUserWithJsonArrayLombok() 
	{
		RestAssured.baseURI ="";
		
		AssignmentUser.Category  category= new AssignmentUser.Category(1234, "Information Technology");
		
		
		
	}
	
	

}
