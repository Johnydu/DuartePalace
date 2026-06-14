package br.com.hotel.duartepalace.model;

import br.com.hotel.duartepalace.model.enums.FormaPagamento;
import br.com.hotel.duartepalace.model.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false)
    private TipoPagamento tipoPagamento;

    @Column(name = "valor_retido",precision = 10, scale = 2)
    private BigDecimal valorRetido;

    @ManyToOne
    @JoinColumn(name ="hospedagem_id", nullable = false)
    private Hospedagem hospedagem;
}
