package in.raghu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search_Query {
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
}
