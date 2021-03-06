package io.github.zhyshko.application.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "routes")
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="departure_coordinate_id", nullable = false)
	private Coordinates departure;
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="destination_coordinate_id", nullable = false)
	private Coordinates destination;
	private Float distance;
	private Integer time;
}
