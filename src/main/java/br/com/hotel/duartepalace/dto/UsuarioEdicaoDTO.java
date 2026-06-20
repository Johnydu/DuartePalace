package br.com.hotel.duartepalace.dto;

import br.com.hotel.duartepalace.model.enums.RoleUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEdicaoDTO {

    private Long id;
    private String username;
    private String novaSenha; // opcional — só preenche se quiser trocar
    private RoleUsuario role;
}