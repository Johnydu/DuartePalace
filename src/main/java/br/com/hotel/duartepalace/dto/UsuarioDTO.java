package br.com.hotel.duartepalace.dto;

import br.com.hotel.duartepalace.model.enums.RoleUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(

        @NotBlank(message = "Campo obrigatório")
        String username,

        @NotBlank(message = "Campo obrigatório")
        String password,

        @NotNull(message = "Campo obrigatório")
        RoleUsuario role

) {}

