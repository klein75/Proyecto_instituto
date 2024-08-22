package com.irojas.demojwt.User.Persisten.entity;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.irojas.demojwt.Auth.AuthResponse.AuthResponseBuilder;
import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;
import com.irojas.demojwt.Utilities.Enum.DoctypeEnum;
import com.irojas.demojwt.Utilities.Enum.GenderEnum;
import com.irojas.demojwt.Utilities.Enum.QuestionEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_uno")
    private String nombreUno;

    @Column(name = "nombre_dos")
    private String nombreDos;

    @Column(name = "apellido_uno")
    private String apellidoUno;

    @Column(name = "apellido_dos")
    private String apellidoDos;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_docu")
    private DoctypeEnum docType;

    @Column(name = "documento") 
    private String documento;

    @Column(name = "fecha_exp")
    private Date fechaExp;

    @Column(name = "lugar_exp")
    private String lugarExp;

    @Column(name = "fecha_naci")
    private Date fechaNaci;

    @Column(name = "lugar_naci")
    private String lugarNaci;

    @Column(name = "edad")
    private String edad;

    @Column(name = "tipo_sangre")
    private String tipoSangre;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private GenderEnum sexo;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "apodo")
    private String apodo;

    @Column(name = "imagen")
    private String imagen;

    @Enumerated(EnumType.STRING)
    @Column(name = "pregunta")  
    private QuestionEnum pregunta;

    @Column(name = "respuesta")
    private String respuesta;

    @Column(name = "acudiente")
    private boolean acudiente;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private StateEnum estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles", 
               joinColumns = @JoinColumn(name = "usuario"), 
               inverseJoinColumns = @JoinColumn(name = "rol"))
    private Set<RoleEntity> roles = new HashSet<>();


    public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
            .flatMap(role -> role.getPermissions().stream())
            .map(permission -> new SimpleGrantedAuthority(permission.getPermiso()))
            .collect(Collectors.toList());
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

}
