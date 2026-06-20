package br.com.hotel.duartepalace.mapper;

import br.com.hotel.duartepalace.dto.UsuarioDto;
import br.com.hotel.duartepalace.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDto usuarioDto);
}
