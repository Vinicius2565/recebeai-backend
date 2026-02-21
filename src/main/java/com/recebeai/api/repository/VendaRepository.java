package com.recebeai.api.repository;

import com.recebeai.api.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByClientId(Long clientId);
}

