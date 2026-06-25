package br.com.hotel.duartepalace.dto;

import br.com.hotel.duartepalace.model.enums.RoleUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioListaDTO(@NotNull Long id,
                              @NotBlank String username,
                              @NotNull RoleUsuario role) {}
