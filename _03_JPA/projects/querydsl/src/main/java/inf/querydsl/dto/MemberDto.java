package inf.querydsl.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberDto {

	private String username;
	private int age;

	public MemberDto(String username, int age) {
		this.username = username;
		this.age = age;
	}

}
