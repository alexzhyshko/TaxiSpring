package io.github.zhyshko.application.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String plate;
	private String manufacturer;
	private String model;
	@ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private Translation translations;
	private Integer passengerCount;
	private Float priceMultiplier;
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="coordinates_id", nullable = false)
	private Coordinates coordinates;
	
}
