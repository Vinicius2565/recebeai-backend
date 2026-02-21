package com.recebeai.api.controller;

import com.recebeai.api.entity.Order;
import com.recebeai.api.entity.User;
import com.recebeai.api.service.OrderService;
import com.recebeai.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;
    private final OrderService orderService;

    @PostMapping("/cadastrar")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return service.login(user.getLogin(), user.getPass());
    }

    @PatchMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return orderService.updateStatus(id, status);
    }

}
