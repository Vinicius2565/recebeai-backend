package com.recebeai.api.service;

import com.recebeai.api.entity.Order;
import com.recebeai.api.entity.Client;
import com.recebeai.api.entity.StatusVenda;
import com.recebeai.api.repository.OrderRepository;
import com.recebeai.api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public Order createOrder(Long clientId, Order order) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        order.setClient(client);

        order.setStatus(StatusVenda.PENDENTE);

        return orderRepository.save(order);
    }

    public List<Order> listOrdersByClient(Long clientId) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return orderRepository.findByClient(client);
    }

    public Order updateStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        order.setStatus(StatusVenda.valueOf(status));

        return orderRepository.save(order);
    }
}