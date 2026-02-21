package com.recebeai.api.repository;

import com.recebeai.api.entity.Order;
import com.recebeai.api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByClient(Client client);

}