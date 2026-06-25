package br.com.hotel.duartepalace.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String tratarResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("mensagem", ex.getMessage());
        return "pages/erro/404";
    }
}