package br.com.hotel.duartepalace.mapper;

import br.com.hotel.duartepalace.dto.UsuarioDTO;
import br.com.hotel.duartepalace.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO usuarioDto);
}
