package com.irojas.demojwt.Subject.Persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materia")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "materia")
    private String subject;

    @Column(name = "descripcion")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private StateEnum state;

     @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "materia_docente",
               joinColumns = @JoinColumn(name = "materia"),
               inverseJoinColumns = @JoinColumn(name = "docente"))
    private Set<User> docente = new HashSet<>();

   
}




