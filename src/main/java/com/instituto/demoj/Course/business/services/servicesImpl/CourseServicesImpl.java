package com.instituto.demoj.Course.business.services.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.instituto.demoj.Course.Domain.Dto.CourseDto;
import com.instituto.demoj.Course.Domain.Entity.CoursesEntity;
import com.instituto.demoj.Course.business.mapper.CourseMapper;
import com.instituto.demoj.Course.business.services.ICourseService;
import com.instituto.demoj.Course.persistence.CoursesRepository;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.Utilities.Enum.StateEnum;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class CourseServicesImpl implements ICourseService {

    private final CoursesRepository coursesRepository;
    private final Validator validator;
    private final CourseMapper courseMapper;

    public CourseServicesImpl(CoursesRepository coursesRepository, Validator validator, CourseMapper courseMapper) {
        this.coursesRepository = coursesRepository;
        this.validator = validator;
        this.courseMapper = courseMapper;
    }
    
    private String validateDto(CourseDto courseDto) {
        Set<ConstraintViolation<CourseDto>> violations = validator.validate(courseDto);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(v -> "Campo: " + v.getPropertyPath() + " - Error: " + v.getMessage())
                    .collect(Collectors.joining("; "));
        }
        return null;
    }

    @Override
    public ResponseMessage saveCourse(CourseDto courseDto) {
        String validationErrors = validateDto(courseDto);
        if (validationErrors != null) {
            return new ResponseMessage(false, validationErrors, null);
        }

        try {
            CourseDto courseSaved = courseMapper.toDto(coursesRepository.save(courseMapper.toEntity(courseDto)));
            return new ResponseMessage(true, "Curso creado correctamente con ID: " + courseSaved.getId(), courseSaved);
        } catch (Exception e) {
            return new ResponseMessage(false, "Error al crear el curso: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage updateCourse(Long id, CourseDto courseDto) {
       String validationErrors = validateDto(courseDto);
       if (validationErrors != null) {
           return new ResponseMessage(false, validationErrors, null);
       }
       Optional<CoursesEntity> optionalCourse = coursesRepository.findById(id);
       if (optionalCourse.isEmpty()) {
           return new ResponseMessage(false, "El curso con ID " + id + " no fue encontrado.", null);
       }
       try {
           courseDto.setId(id);  // Aseguramos que el ID sea el mismo en la actualización
           CourseDto courseUpdated = courseMapper.toDto(coursesRepository.save(courseMapper.toEntity(courseDto)));
           return new ResponseMessage(true, "Curso con ID " + id + " actualizado correctamente", courseUpdated);
       } catch (Exception e) {
           return new ResponseMessage(false, "Error al actualizar el curso con ID " + id + ": " + e.getMessage(), null);
       }
    }

    @Override
    public ResponseMessage deleteCourse(Long id) {
        if (!coursesRepository.existsById(id)) {
            return new ResponseMessage(false, "El curso con ID " + id + " no fue encontrado.", null);
        }
        try {
            coursesRepository.deleteById(id);
            return new ResponseMessage(true, "Curso con ID " + id + " eliminado correctamente.", null);
        } catch (Exception e) {
            return new ResponseMessage(false, "Error al eliminar el curso con ID " + id + ": " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage getCourses() {
        List<CoursesEntity> courses = coursesRepository.findAll();
        if (!courses.isEmpty()) {
            List<CourseDto> coursesDto = courses.stream()
                    .map(courseMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseMessage(true, "Cursos obtenidos exitosamente", coursesDto);
        } else {
            return new ResponseMessage(false, "No se encontraron cursos disponibles.", null);
        }
    }

    @Override
    public ResponseMessage getCourseById(Long id) {
        Optional<CoursesEntity> optionalCourse = coursesRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new ResponseMessage(false, "El curso con ID " + id + " no fue encontrado.", null);
        }
        return new ResponseMessage(true, "Curso con ID " + id + " encontrado.", courseMapper.toDto(optionalCourse.get()));
    }

    @Override
    public ResponseMessage getCourseByState(StateEnum state) {
        List<CoursesEntity> courses = coursesRepository.findByState(state);
        if (!courses.isEmpty()) {
            List<CourseDto> coursesDto = courses.stream()
                    .map(courseMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseMessage(true, "Cursos con estado " + state + " encontrados.", coursesDto);
        } else {
            return new ResponseMessage(false, "No se encontraron cursos con estado " + state + ".", null);
        }
    }
}