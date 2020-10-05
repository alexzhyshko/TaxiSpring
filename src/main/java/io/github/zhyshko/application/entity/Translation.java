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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="translations")
public class Translation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String text_RU;
	private String text_UA;
	private String text_EN;
	
}
