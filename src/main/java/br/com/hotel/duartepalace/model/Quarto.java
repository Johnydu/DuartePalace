package br.com.hotel.duartepalace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quarto")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String numero;

    @NotNull
    @Column(name = "tipo",nullable = false)
    private String tipo;

    @NotNull
    @Column(name = "capacidade", nullable = false)
    private Integer capacidade;

    @NotNull
    @Column(name = "preco_diaria", nullable = false)
    private BigDecimal precoDiaria;

    @NotNull
    @Column(name = "em_manutencao")
    private Boolean emManutencao;

}
