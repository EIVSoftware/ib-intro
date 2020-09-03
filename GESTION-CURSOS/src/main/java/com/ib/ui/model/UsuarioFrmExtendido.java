package com.ib.ui.model;

import com.ib.data.entity.UsuarioEntity;

public class UsuarioFrmExtendido extends UsuarioFrm {

    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    
    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }
    
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }
    
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    
    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    
    public Boolean getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void llenarCampos(UsuarioEntity usuarioEntity) {
        super.llenarCampos(usuarioEntity);
        
        if (this.getAccountNonExpired() == null) {
            this.setAccountNonExpired(usuarioEntity.getAccountNonExpired());
        }
        
        if (this.getAccountNonLocked() == null) {
            this.setAccountNonLocked(usuarioEntity.getAccountNonLocked());
        }
        
        if (this.getCredentialsNonExpired() == null) {
            this.setCredentialsNonExpired(usuarioEntity.getCredentialsNonExpired());
        }
        
        if (this.getEnabled() == null) {
            this.setEnabled(usuarioEntity.getEnabled());
        }
    }
    
}
