package io.github.zhyshko.application.dto;

import java.util.List;

import io.github.zhyshko.application.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
	private Integer id;
	private String username;
	private String name;
	private String surname;
	private Float rating;
	private Role role;
	private String password;
	private List<Order> orders;
}
