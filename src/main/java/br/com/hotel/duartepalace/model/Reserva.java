package br.com.hotel.duartepalace.model;


import br.com.hotel.duartepalace.model.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

//Acordo entre hóspede e hotel

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "data_saida",nullable = false)
    private LocalDate dataSaida;

    @Column(name = "preco_diaria", nullable = false, scale = 2,precision = 20)
    private BigDecimal precoDiaria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "hospede_id", nullable = false)
    private Hospede hospede;

}
