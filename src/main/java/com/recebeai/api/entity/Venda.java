package com.recebeai.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Double valor;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusVenda status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
