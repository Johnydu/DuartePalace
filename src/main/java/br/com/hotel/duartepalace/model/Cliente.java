package br.com.hotel.duartepalace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Getter
@Setter
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @CPF(message = "CPF inválido")
    @Column(nullable = false,unique = true,length = 14)
    private String cpf;

    @Column(nullable = false,length = 11)
    @Pattern(
            regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
            message = "Telefone inválido")
    private String telefone;

    @NotNull
    private Boolean usuarioPremium;




}


