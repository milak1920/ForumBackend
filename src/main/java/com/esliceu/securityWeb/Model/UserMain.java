package com.esliceu.securityWeb.Model;


import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Encargada de generar la seguridad
 * Clase que implementa los privilegios de cada usuario
 * UserDetails es una clase propia de Spring Security
 */
public class UserMain implements UserDetails {


    private String usuario;
    private String email;
    private String password;
    // Variable que nos da la autorización (no confundir con autenticación)
    // Coleccion de tipo generico que extendiende
    // de GranthedAuthority de Spring security
    private Collection<? extends GrantedAuthority> authorities;

    //Constructor
    public UserMain(String usuario, String email, String password, Collection<? extends GrantedAuthority> authorities) {

        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    //Metodo que asigna los privilegios (autorización)
    public static UserMain build(User user){
        return new UserMain(user.getName(), user.getEmail(),
                user.getPassword(), user.getRoles(user.getRole()));
    }

    //@Override los que tengan esta anotación
    // significa que son metodos de UserDetails de SpringSecurity

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public String getEmail() {
        return email;
    }
}