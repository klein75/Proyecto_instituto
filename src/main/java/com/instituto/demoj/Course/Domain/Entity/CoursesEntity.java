package com.instituto.demoj.Course.Domain.Entity;

import com.instituto.demoj.Course.utils.TitleEnum;
import com.instituto.demoj.Course.utils.TypeEnum;
import com.instituto.demoj.Utilities.Enum.StateEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "courses")
public class CoursesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course")
    private String course;

    @Column(name = "details")
    private String details;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private String duration;

    @Column(name = "title")
    private TitleEnum title;

    @Column(name = "type")
    private TypeEnum type;

    @Column(name = "state")
    private StateEnum state;

}
