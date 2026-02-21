package com.recebeai.api.controller;

import com.recebeai.api.entity.Client;
import com.recebeai.api.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @PostMapping("/usuario/{userId}")
    public Client createClient(
            @PathVariable Long userId,
            @RequestBody Client client
    ) {
        return service.createClient(userId, client);
    }

    @GetMapping("/usuario/{userId}")
    public List<Client> listClients(@PathVariable Long userId) {
        return service.listClientsByUser(userId);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        service.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

}
