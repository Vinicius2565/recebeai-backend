package com.recebeai.api.service;

import com.recebeai.api.entity.Client;
import com.recebeai.api.entity.User;
import com.recebeai.api.repository.ClientRepository;
import com.recebeai.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public Client createClient(Long userId, Client client) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        client.setUser(user);

        return clientRepository.save(client);
    }

    public List<Client> listClientsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return clientRepository.findByUser(user);
    }

    @Transactional
    public void deleteClient(Long clientId) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clientRepository.delete(client);
    }

}
