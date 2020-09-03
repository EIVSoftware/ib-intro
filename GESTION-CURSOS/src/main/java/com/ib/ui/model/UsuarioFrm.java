package com.ib.ui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.ib.data.dto.Usuario;
import com.ib.data.entity.UsuarioEntity;

public class UsuarioFrm implements Usuario {

    @NotBlank
    private String username;

    @NotBlank
    @Length(min = 8)
    private String password;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String nombres;

    @NotBlank
    private String email;
    
    @Size(min = 1, max = 3)
    private List<String> roles;
    
    public UsuarioFrm() {
        this.roles = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos == null || apellidos.trim().isEmpty() ? null : apellidos.trim().toUpperCase();
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres == null || nombres.trim().isEmpty() ? null : nombres.trim().toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null || email.trim().isEmpty() ? null : email.trim().toLowerCase();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    public void llenarCampos(UsuarioEntity usuarioEntity) {

        if (this.getApellidos() == null) {
            this.setApellidos(usuarioEntity.getApellidos());
        }
        
        if (this.getNombres() == null) {
            this.setNombres(usuarioEntity.getNombres());
        }
        
        if (this.getUsername() == null) {
            this.setUsername(usuarioEntity.getUsername());
        }
        
        this.setPassword("[------]");
        
        if (this.getEmail() == null) {
            this.setEmail(usuarioEntity.getEmail());
        }
        
        if (this.getRoles() == null) {
            this.setRoles(Collections.emptyList());
        }
    }
}
