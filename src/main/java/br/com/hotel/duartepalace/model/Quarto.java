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
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private Integer numeroQuarto;

    @NotNull
    private BigDecimal valorDiaria;


    @Enumerated(EnumType.STRING)
    private TipoQuarto tipoQuarto;

    @Enumerated(EnumType.STRING)
    private StatusQuarto statusQuarto;


}
