package com.ib.ui.ctrl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ib.data.entity.UsuarioEntity;
import com.ib.data.service.UsuarioService;
import com.ib.ui.model.UsuarioFrm;
import com.ib.ui.model.UsuarioFrmExtendido;
import com.ib.ui.model.UsuarioLstItemDto;

@Controller
public class UsuarioCtrl {

    @Autowired private UsuarioService usuarioService;
    
    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        
        List<UsuarioEntity> usuarioEntities = usuarioService.findAll();
        List<UsuarioLstItemDto> usuarios = UsuarioLstItemDto.fromEntities(usuarioEntities);
        
        model.addAttribute("usuarios", usuarios);
        
        return "admin/usuarios-listar";
    }

    @GetMapping("/admin/usuarios/frm")
    public String nuevoUsuarioFrm(
            @ModelAttribute UsuarioFrm usuario, Model model) {
        
        return "admin/usuarios-crear";
    }

    @PostMapping("/admin/usuarios/frm")
    public String nuevoUsuarioAction(
            @Valid UsuarioFrm usuario, Errors errors, Model model) {
        
        if (errors.hasErrors()) {
            return nuevoUsuarioFrm(usuario, model);
        }
        
        usuarioService.create(usuario);
        
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/admin/usuarios/{id}/frm")
    public String editarUsuarioFrm(
            @PathVariable("id") Long usuarioId, 
            @ModelAttribute UsuarioFrmExtendido usuario, Model model) {
        
        UsuarioEntity usuarioEntity = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("NO EXISTE UN USUARIO CON ID %s", usuarioId)));
        
        usuario.llenarCampos(usuarioEntity);
        
        return "admin/usuarios-editar";
    }

    @PostMapping("/admin/usuarios/{id}/frm")
    public String editarUsuarioAction(
            @PathVariable("id") Long usuarioId, 
            @Valid UsuarioFrmExtendido usuario, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return editarUsuarioFrm(usuarioId, usuario, model);
        }
        
        UsuarioEntity usuarioEntity = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("NO EXISTE UN USUARIO CON ID %s", usuarioId)));
        
        usuarioService.update(usuarioEntity.getId(), usuario);

        return "redirect:/admin/usuarios";
    }
    
    @DeleteMapping("/admin/usuarios/{id}")
    public ResponseEntity<?> borrarUsuario(
            @PathVariable("id") Long usuarioId) {
        usuarioService.delete(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
