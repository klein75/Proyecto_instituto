package com.irojas.demojwt.news.persisten.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import com.irojas.demojwt.Enrollment.persistence.entity.MatriculaEntity;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.visualEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "noticias")
@Entity
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="titulo" )
    private String titulo;

    @Column(name = "subtitulo")
    private String subtitulo;

    @Column(name= "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "fecha", columnDefinition = "DATETIME") // fecha de el evento que se vaya a tener en la noticia  ejemplo :boletines fecha
    private LocalDateTime fecha;

    @Column(name = "fecha_creacion", columnDefinition = "DATETIME", updatable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private visualEnum privacidad;

    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id", nullable = false)
    private User autor;

    




}
