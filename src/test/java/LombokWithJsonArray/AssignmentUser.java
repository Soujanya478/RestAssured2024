package LombokWithJsonArray;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignmentUser {
	
	private int id;
	private String name;
	private String status;
	private List<String> photoURLs;
	private Category category;
	private List<Tag> tags;
	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Category{
		private int categoryId;
		private String categoryName;
		
	}	
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	private static class Tag {
		private int tagId;
		private String tagName;
		
	}
	

}
