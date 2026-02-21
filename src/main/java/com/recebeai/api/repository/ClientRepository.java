package com.recebeai.api.repository;

import com.recebeai.api.entity.Client;
import com.recebeai.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByUser(User user);

}


