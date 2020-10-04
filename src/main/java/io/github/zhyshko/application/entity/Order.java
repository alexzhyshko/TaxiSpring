package io.github.zhyshko.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="route_id", nullable = false)
	private Route route;
	private Float price;
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name="driving_id", nullable = false)
	private Driving driving;
	private Integer timeToArrival;
	private LocalDateTime dateOfOrder;
	@ManyToOne
	@JoinColumn(name="status_id", nullable = false)
	@ColumnDefault("1")
	private OrderStatus status;

}
