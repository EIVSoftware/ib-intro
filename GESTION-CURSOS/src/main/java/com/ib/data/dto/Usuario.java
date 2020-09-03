package com.ib.data.dto;

public interface Usuario {

    public String getUsername();
    
    public String getPassword();
    
    public String getApellidos();
    
    public String getNombres();
    
    public String getEmail();
    
    default public Boolean getAccountNonExpired() {
        return true;
    }
    
    default public Boolean getAccountNonLocked() {
        return true;
    }
    
    default public Boolean getCredentialsNonExpired() {
        return true;
    }
    
    default public Boolean getEnabled() {
        return true;
    }
    
}
