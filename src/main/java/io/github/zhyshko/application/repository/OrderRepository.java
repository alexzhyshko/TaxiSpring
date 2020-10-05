package io.github.zhyshko.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.Order;
import io.github.zhyshko.application.entity.OrderStatus;
import io.github.zhyshko.application.entity.User;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer>{

	//@Query("select o from orders o where user_id= :userId")
	List<Order> findAllByUserId(UUID userId);
	
	Optional<Car> findCarById(int id);
	
	@Query("select order_status from OrderStatus order_status where order_status.id= :statusId")
	OrderStatus findOrderStatusById(int statusId);
	
	Page<Order> findAll(Pageable pageable);
	
	Page<Order> findAllByUserOrderByDateOfOrderDesc(User user, Pageable pageable);
	
}
