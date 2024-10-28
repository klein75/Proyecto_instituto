package com.instituto.demoj.News.domain.entity;

import java.time.LocalDate;

import com.instituto.demoj.News.Utils.StateEnum;
import com.instituto.demoj.User.domain.entity.User;


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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class NewsEntity {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "subtitle", length = 150, nullable = false)
    private String subtitle;

    @Column(name = "content", length = 500, nullable = false)
    private String content;

    @Column(name = "image", length = 255, nullable = false)
    private String image;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private StateEnum state;

}
