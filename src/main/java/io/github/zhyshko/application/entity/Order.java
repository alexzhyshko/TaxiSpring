package io.github.zhyshko.application.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="route_id", nullable = false)
	private Route route;
	private Float price;
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", nullable = false, columnDefinition="binary(16)")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name="driving_id", nullable = false)
	private Driving driving;
	private Integer timeToArrival;
	private LocalDateTime dateOfOrder;
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@ColumnDefault("1")
	@JoinColumn(name="status_id", nullable = false, columnDefinition="integer default 1")
	private OrderStatus status;

}
