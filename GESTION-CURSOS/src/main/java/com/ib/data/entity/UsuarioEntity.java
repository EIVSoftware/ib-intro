package com.ib.data.entity;

public class UsuarioEntity {

    private Long id;
    private String username;
    private String password;
    private String apellidos;
    private String nombres;
    private String email;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    
    public UsuarioEntity() {
    }

    public UsuarioEntity(
            Long id, String username, String password, 
            String apellidos, String nombres, String email) {
        this(id, username, password, apellidos, nombres, email, true, true, true, true);
    }

    public UsuarioEntity(
            Long id, String username, String password, String apellidos, 
            String nombres, String email, Boolean accountNonExpired, 
            Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
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
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public UsuarioEntity(Long id) {
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "UsuarioEntity [id=" + id + ", username=" + username + ", enabled=" + enabled + "]";
    }
    
}
