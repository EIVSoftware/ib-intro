package com.ib.ui.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ib.data.entity.UsuarioEntity;

public class UsuarioLstItemDto {

    private Long id;
    private String username;
    private String denominacion;
    private String email;
    private List<String> roles;
    
    public UsuarioLstItemDto() {
    }
    
    public UsuarioLstItemDto(UsuarioEntity usuarioEntity) {
        this.id = usuarioEntity.getId();
        this.username = usuarioEntity.getUsername();
        this.denominacion = String.format(
                "%s, %s", usuarioEntity.getApellidos(), usuarioEntity.getNombres());
        this.email = usuarioEntity.getEmail();
        this.roles = Collections.emptyList();    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UsuarioLstItemDto other = (UsuarioLstItemDto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioLstItemDto [id=" + id + ", username=" + username + "]";
    }
    
    public static List<UsuarioLstItemDto> fromEntities(List<UsuarioEntity> usuarioEntities) {
        return usuarioEntities.stream()
                .map(e -> new UsuarioLstItemDto(e))
                .collect(Collectors.toList());
    }
}
