package io.github.zhyshko.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{

}
