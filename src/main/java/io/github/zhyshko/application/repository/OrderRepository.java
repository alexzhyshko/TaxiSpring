package io.github.zhyshko.application.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer>{

}
