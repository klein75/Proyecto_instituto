package com.irojas.demojwt.OpenCourse.Services.Especification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.irojas.demojwt.Courses.Persistence.Entity.CourseEntity;
import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;
import com.irojas.demojwt.Subject.Persistence.Entity.SubjectEntity;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.DayEnum;
import com.irojas.demojwt.Utilities.Enum.SemestreEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;
import com.irojas.demojwt.schedule.persistence.entity.ScheduleEntity;
import com.irojas.demojwt.studentDay.Persistence.Entity.StudyDay;

import io.micrometer.common.lang.Nullable;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Expression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenCourseSpecification implements Specification<OpenCourseEntity> {

    private Date startDate;
    private Date endDate;
    private String year;
    private SemestreEnum semester;
    private BigDecimal enrollmentFee;
    private BigDecimal totalFee;
    private String description;
    private StateEnum state;
    private String course; // Buscar por el nombre del curso en lugar del ID
    private String studyDay; // Buscar por el nombre del estudio en lugar del ID
    private String inCharge; // Buscar por el nombre de la persona encargada en lugar del ID
    private String subject; // Buscar por el nombre de la materia en lugar del ID
    private DayEnum scheduleDay; // Buscar por el día del horario (ScheduleEntity)
    @Override
    @Nullable
    public Predicate toPredicate(Root<OpenCourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null) {
            predicates.add(criteriaBuilder.equal(root.get("startDate"), startDate));
        }

        if (endDate != null) {
            predicates.add(criteriaBuilder.equal(root.get("endDate"), endDate));
        }

        if (StringUtils.isNotBlank(year)) {
            predicates.add(criteriaBuilder.equal(root.get("year"), year));
        }

        if (semester != null) {
            predicates.add(criteriaBuilder.equal(root.get("semester"), semester));
        }

        if (enrollmentFee != null) {
            predicates.add(criteriaBuilder.equal(root.get("enrollmentFee"), enrollmentFee));
        }

        if (totalFee != null) {
            predicates.add(criteriaBuilder.equal(root.get("totalFee"), totalFee));
        }

        if (StringUtils.isNotBlank(description)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
        }

        if (state != null) {
            predicates.add(criteriaBuilder.equal(root.get("state"), state));
        }

        // Buscar por el nombre del curso
        if (StringUtils.isNotBlank(course)) {
            Join<OpenCourseEntity, CourseEntity> courseJoin = root.join("course");
            Predicate courseNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(courseJoin.get("name")), "%" + course.toLowerCase() + "%");
            predicates.add(courseNamePredicate);
        }

        // Buscar por el nombre de la jornada
        if (StringUtils.isNotBlank(studyDay)) {
            Join<OpenCourseEntity, StudyDay> studyDayJoin = root.join("studyDay");
            Predicate studyDayNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(studyDayJoin.get("name")), "%" + studyDay.toLowerCase() + "%");
            predicates.add(studyDayNamePredicate);
        }

        // Buscar por el nombre completo del encargado
        if (StringUtils.isNotBlank(inCharge)) {
            Join<OpenCourseEntity, User> inChargeJoin = root.join("inCharge");

            // Concatenar nombre completo del usuario
            Expression<String> fullName = criteriaBuilder.concat(
                criteriaBuilder.concat(
                    criteriaBuilder.concat(
                        criteriaBuilder.lower(inChargeJoin.get("nombreUno")), " "
                    ), criteriaBuilder.lower(inChargeJoin.get("nombreDos"))
                ), " "
            );

            fullName = criteriaBuilder.concat(
                criteriaBuilder.concat(fullName, criteriaBuilder.lower(inChargeJoin.get("apellidoUno"))), " "
            );

            fullName = criteriaBuilder.concat(
                fullName, criteriaBuilder.lower(inChargeJoin.get("apellidoDos"))
            );

            Predicate fullNamePredicate = criteriaBuilder.like(fullName, "%" + inCharge.toLowerCase() + "%");
            predicates.add(fullNamePredicate);
        }

        // Buscar por el nombre de la materia
        if (StringUtils.isNotBlank(subject)) {
            Join<OpenCourseEntity, SubjectEntity> subjectsJoin = root.joinSet("subjects");
            Predicate subjectNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(subjectsJoin.get("subject")), "%" + subject.toLowerCase() + "%");
            predicates.add(subjectNamePredicate);
        }

        // Buscar por el día del horario
        if (scheduleDay != null) {
            Join<OpenCourseEntity, ScheduleEntity> schedulesJoin = root.joinSet("schedules");
            Predicate scheduleDayPredicate = criteriaBuilder.equal(schedulesJoin.get("day"), scheduleDay);
            predicates.add(scheduleDayPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
