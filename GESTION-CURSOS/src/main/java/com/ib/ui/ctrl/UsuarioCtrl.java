package com.ib.ui.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ib.data.entity.UsuarioEntity;
import com.ib.data.service.UsuarioService;
import com.ib.ui.model.UsuarioFrm;
import com.ib.ui.model.UsuarioLstItemDto;

@Controller
public class UsuarioCtrl {

    @Autowired private UsuarioService usuarioService;
    
    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        
        List<UsuarioEntity> usuarioEntities = usuarioService.findAll();
        List<UsuarioLstItemDto> usuarios = UsuarioLstItemDto.fromEntities(usuarioEntities);
        
        model.addAttribute("usuarios", usuarios);
        
        return "admin/usuarios-listado";
    }

    @GetMapping("/admin/usuarios/frm")
    public String nuevoUsuarioFrm(
            @ModelAttribute UsuarioFrm usuario) {
        
        return "admin/usuarios-nuevo";
    }

    @PostMapping("/admin/usuarios/frm")
    public String nuevoUsuarioAction(
            @Validated UsuarioFrm usuario, 
            BindingResult result) {
        
        if (result.hasErrors()) {
            return nuevoUsuarioFrm(usuario);
        }
        
        return "redirect:/admin/usuarios";
    }
}
