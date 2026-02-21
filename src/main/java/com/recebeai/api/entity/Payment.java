package com.recebeai.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
