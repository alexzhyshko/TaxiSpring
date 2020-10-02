package io.github.zhyshko.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "coordinates")
public class Coordinates {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String longitude;
	private String latitude;
}
