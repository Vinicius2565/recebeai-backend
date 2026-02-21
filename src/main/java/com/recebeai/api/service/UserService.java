package com.recebeai.api.service;

import com.recebeai.api.entity.User;
import com.recebeai.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User register(User user) {

        if (repository.findByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("Login já existe!");
        }

        return repository.save(user);
    }

    public User login(String login, String pass) {

        User user = repository.findByLogin(login)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!user.getPass().equals(pass)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
        }

        return user;
    }
}
