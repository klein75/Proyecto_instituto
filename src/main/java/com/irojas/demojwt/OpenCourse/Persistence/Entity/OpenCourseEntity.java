package com.irojas.demojwt.OpenCourse.Persistence.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.irojas.demojwt.Courses.Persistence.Entity.CourseEntity;
import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.SemestreEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.schedule.persistence.entity.ScheduleEntity;
import com.irojas.demojwt.studentDay.Persistence.Entity.StudyDay;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cursos_abiertos")
public class OpenCourseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Long id;
    
    @Column(name = "inicio", nullable = false)
    private Date startDate;

    @Column(name = "fin", nullable = false)
    private Date endDate;

    @Column(name = "año", nullable = false, length = 4)
    private String year;

    @Enumerated(EnumType.STRING)
    @Column(name = "semestre", nullable = false)
    private SemestreEnum semester;

    @Column(name = "matricula", nullable = false)
    private BigDecimal enrollmentFee;

    @Column(name = "total", nullable = false)
    private BigDecimal totalFee;

    @Column(name = "descripcion", length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private StateEnum state;

    @ManyToOne
    @JoinColumn(name = "curso", nullable = false)
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "jornada", nullable = false)
    private StudyDay studyDay;

    @ManyToOne
    @JoinColumn(name = "encargado", nullable = false)
    private User inCharge;

    @ManyToMany
    @JoinTable(
        name = "curso_horarios",
        joinColumns = @JoinColumn(name = "curso"),
        inverseJoinColumns = @JoinColumn(name = "horario")
    )
    private Set<ScheduleEntity> schedules = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "curso_materias",
        joinColumns = @JoinColumn(name = "curso"),
        inverseJoinColumns = @JoinColumn(name = "materia")
    )
    private Set<SubjectEntity> subjects = new HashSet<>();


}
