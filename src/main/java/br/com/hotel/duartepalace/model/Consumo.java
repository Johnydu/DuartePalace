package br.com.hotel.duartepalace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "valor", nullable = false, scale = 2, precision = 10)
    private BigDecimal valor;

    @NotNull
    @Column(name = "data_consumo", nullable = false)
    private LocalDateTime dataConsumo;

    @ManyToOne
    @JoinColumn(name = "hospedagem_id", nullable = false)
    private Hospedagem hospedagem;
}
