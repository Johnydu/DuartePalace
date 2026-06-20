package br.com.hotel.duartepalace.controller;


import br.com.hotel.duartepalace.dto.UsuarioDto;
import br.com.hotel.duartepalace.model.Usuario;
import br.com.hotel.duartepalace.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/novo")
    public String formularioNovoUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "pages/usuario/form";

    }

    @PostMapping
    public String novoUsuario(@ModelAttribute UsuarioDto usuarioDto){
        usuarioService.novoUsuario(usuarioDto);
        return "redirect:/admin/usuario";
    }
}
