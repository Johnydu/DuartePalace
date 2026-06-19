package br.com.hotel.duartepalace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

//Pessoa que usa o hotel

@Entity
@Getter
@Setter
@Table(name = "hospede")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Hospede {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @CPF(message = "CPF inválido")
    @Column(name="cpf",nullable = false,unique = true)
    private String cpf;

    @NotNull
    @Column(nullable = false,length = 20)
    @Pattern(
            regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
            message = "Telefone inválido")
    private String telefone;

    @NotNull
    @Email(message = "email invalido")
    @Column(name = "email",nullable = false)
    private String email;

    @OneToMany(mappedBy = "hospede")
    private List<Reserva>  reservas;

}


