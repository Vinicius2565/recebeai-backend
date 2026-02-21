package com.recebeai.api.controller;

import com.recebeai.api.entity.Order;
import com.recebeai.api.entity.StatusVenda;
import com.recebeai.api.repository.OrderRepository;
import com.recebeai.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @Autowired
    private final OrderRepository orderRepository;

    @PostMapping("/cliente/{clientId}")
    public Order createOrder(
            @PathVariable Long clientId,
            @RequestBody Order order
    ) {
        return service.createOrder(clientId, order);
    }

    @GetMapping("/cliente/{clientId}")
    public List<Order> listOrders(@PathVariable Long clientId) {
        return service.listOrdersByClient(clientId);
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizarPedido(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        order.setStatus(StatusVenda.FINALIZADO);

        orderRepository.save(order);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {

        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        orderRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}