package br.com.hotel.duartepalace.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


//Período ativo de estadia — nasce no check-in

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "hospedagem")

public class Hospedagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in", nullable = false)
    private LocalDateTime checkIn;

    @Column(name = "data_saida_prevista", nullable = false)
    private LocalDate dataSaidaPrevista;

    @Column(name = "check_out_real")
    private LocalDateTime checkOutReal;

    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "hospede_id", nullable = false)
    private Hospede hospede;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = true)
    private Reserva reserva;

    @OneToMany(mappedBy = "hospedagem")
    private List<Pagamento> pagamentos;

    @OneToMany(mappedBy = "hospedagem")
    private List<Consumo> consumos;
}
