package com.recebeai.api.controller;

import com.recebeai.api.entity.Client;
import com.recebeai.api.entity.StatusVenda;
import com.recebeai.api.entity.Venda;
import com.recebeai.api.repository.ClientRepository;
import com.recebeai.api.repository.VendaRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaRepository vendaRepository;
    private final ClientRepository clientRepository;

    public VendaController(VendaRepository vendaRepository,
                           ClientRepository clientRepository) {
        this.vendaRepository = vendaRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/cliente/{clientId}")
    public Venda criar(@PathVariable Long clientId,
                       @RequestBody Venda venda) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow();

        venda.setClient(client);
        venda.setStatus(StatusVenda.PENDENTE);
        venda.setData(LocalDate.now());

        return vendaRepository.save(venda);
    }

    @PutMapping("/{id}/pagar")
    public Venda pagar(@PathVariable Long id) {

        Venda venda = vendaRepository.findById(id)
                .orElseThrow();

        venda.setStatus(StatusVenda.FINALIZADO);

        return vendaRepository.save(venda);
    }

    @GetMapping("/cliente/{clientId}")
    public List<Venda> listarPorCliente(@PathVariable Long clientId) {
        return vendaRepository.findByClientId(clientId);
    }
}
