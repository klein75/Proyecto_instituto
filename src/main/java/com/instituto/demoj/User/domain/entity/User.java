package com.instituto.demoj.User.domain.entity;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.instituto.demoj.Roles.domain.Entity.RoleEntity;
import com.instituto.demoj.Utilities.Enum.DoctypeEnum;
import com.instituto.demoj.Utilities.Enum.GenderEnum;
import com.instituto.demoj.Utilities.Enum.QuestionEnum;

import com.instituto.demoj.Utilities.Enum.StateEnum;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_uno")
    private String name;

    @Column(name = "nombre_dos")
    private String secondName;

    @Column(name = "apellido_uno")
    private String lastName;

    @Column(name = "apellido_dos")
    private String secondLastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_docu")
    private DoctypeEnum docType;

    @Column(name = "documento")
    private String document;

    @Column(name = "fecha_exp")
    private Date dateExp;

    @Column(name = "lugar_exp")
    private String placeExp;

    @Column(name = "fecha_naci")
    private Date birthDate;

    @Column(name = "lugar_naci")
    private String birthPlace;

    @Column(name = "edad")
    private String age;

    @Column(name = "tipo_sangre")
    private String bloodType;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private GenderEnum gender;

    @Column(name = "correo")
    private String email;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "apodo")
    private String nickname;

    @Column(name = "imagen")
    private String image;

    @Column(name = "pregunta")
    private String question;

    @Column(name = "respuesta")
    private String answer;

    @Column(name = "acudiente")
    private boolean hasGuardian;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private StateEnum state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario"), inverseJoinColumns = @JoinColumn(name = "rol"))
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
