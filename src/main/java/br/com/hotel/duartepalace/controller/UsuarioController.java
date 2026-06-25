package br.com.hotel.duartepalace.controller;


import br.com.hotel.duartepalace.dto.UsuarioDTO;
import br.com.hotel.duartepalace.exception.BusinessException;
import br.com.hotel.duartepalace.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/novo")
    public String formularioNovoUsuario(Model model){
        model.addAttribute("usuarioDTO", new UsuarioDTO(null,null,null));
        return "pages/usuario/form";

    }

    @PostMapping
    public String novoUsuario(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, BindingResult result,
                              RedirectAttributes attributes, Model model){
        if(result.hasErrors()){
            return "pages/usuario/form";
        }

        try {
            attributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso");
            usuarioService.novoUsuario(usuarioDTO);
            return "redirect:/admin/usuario/novo";
        } catch (BusinessException e) {
            model.addAttribute("erroNegocio", e.getMessage());
            return "pages/usuario/form";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error", "Usuário ou senha inválidos");
        }
        return "pages/login";
    }


    @GetMapping("/listaUsuario")
    public String listagemUsuario(Model model){
        model.addAttribute( "ListaUsuarioDTO",usuarioService.listaDeUsuarios());
        return "pages/usuario/lista";
    }

    @PostMapping("/{id}/excluir")
    public String deletarUsuario(@PathVariable Long id,  RedirectAttributes attributes ){

        try {
            usuarioService.excluirUsuario(id);
            attributes.addFlashAttribute("sucesso", "Usuario deletado com sucesso");
        } catch (BusinessException e) {
            attributes.addFlashAttribute("erroNegocio", e.getMessage());
        }
        return "redirect:/admin/usuario/listaUsuario";
    }


}
