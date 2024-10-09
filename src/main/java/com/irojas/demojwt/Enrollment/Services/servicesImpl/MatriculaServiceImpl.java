package com.irojas.demojwt.Enrollment.Services.servicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.irojas.demojwt.Enrollment.Services.interfaces.IMatriculaService;
import com.irojas.demojwt.Enrollment.persistence.entity.MatriculaEntity;
import com.irojas.demojwt.Enrollment.persistence.repository.MatriculaRepository;
import com.irojas.demojwt.Enrollment.presentation.DTO.MatriculaRequestDto;
import com.irojas.demojwt.OpenCourse.Persistence.Entity.OpenCourseEntity;
import com.irojas.demojwt.OpenCourse.Persistence.Repository.OpenCourseRepository;
import com.irojas.demojwt.User.Persisten.Repository.UserRepository;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.RoleEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import java.util.List;

@Service
@Transactional
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OpenCourseRepository openCourseRepository;

    @Override
    public List<MatriculaEntity> findAll() {
        return matriculaRepository.findAll();
    }

    @Override
    public List<MatriculaEntity> findByAlumnoId(Long id) {
        return matriculaRepository.findByAlumnoId(id);
    }

    @Override
    public List<MatriculaEntity> findByCursoId(Long id) {
        return matriculaRepository.findByCursoId(id);
    }

    @Override
    public List<MatriculaEntity> findByFolio(String folio) {
        return matriculaRepository.findByFolio(folio);
    }

    @Override
    public List<MatriculaEntity> findByNumero(String numero) {
        return matriculaRepository.findByNumero(numero);
    }

    @Override
    public List<MatriculaEntity> findBySede(String sede) {
        return matriculaRepository.findBySede(sede);
    }

    @Override
    public List<MatriculaEntity> findByState(StateEnum estado) {
        return matriculaRepository.findByEstado(estado);
    }

    @Override
    public MatriculaEntity findById(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));
    }

    @Override
    public MatriculaEntity create(MatriculaEntity matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public MatriculaEntity update(Long id, MatriculaEntity matricula) {
        MatriculaEntity existing = findById(id);
        existing.setFolio(matricula.getFolio());
        existing.setNumero(matricula.getNumero());
        existing.setSede(matricula.getSede());
        existing.setFecha(matricula.getFecha());
        existing.setEstado(matricula.getEstado());
        return matriculaRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        matriculaRepository.deleteById(id);
    }



    
    public MatriculaEntity createFromDto(MatriculaRequestDto matriculaDto) {
        // Buscar el usuario por nombre y apellido
        MatriculaEntity matriculaEntity = userRepository.findByNombreUnoAndApellidoUno(matriculaDto.getNombreUno(), matriculaDto.getApellidoUno())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
User alumno = matriculaEntity.getAlumno();
        // Validar que el usuario tenga el rol de alumno
        boolean isAlumno = alumno.getRoles().stream()
            .anyMatch(role -> role.getRol().equals(RoleEnum.ALUMNO)); // Validar contra el rol de "ALUMNO"
        
        if (!isAlumno) {
            throw new RuntimeException("Solo los alumnos pueden realizar esta acción");
        }
    
        // Buscar el curso por ID
        OpenCourseEntity curso = openCourseRepository.findById(matriculaDto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    
        // Crear la entidad de matrícula
        MatriculaEntity matricula = new MatriculaEntity();
        matricula.setAlumno(alumno); // Asignar el alumno encontrado (de tipo User)
        matricula.setCurso(curso);   // Asignar el curso encontrado
        matricula.setFolio(matriculaDto.getFolio());
        matricula.setNumero(matriculaDto.getNumero());
        matricula.setSede(matriculaDto.getSede());
        matricula.setFecha(matriculaDto.getFecha());
        matricula.setEstado(StateEnum.valueOf(matriculaDto.getEstado()));
    
        // Guardar y retornar la matrícula
        return matriculaRepository.save(matricula);
    }

    //TODO: POSIBILIDADES DE GUARDAR EL USUARIO CON NOMBRE EN LA MATRICULA  EL JSON SE ENVIA DE LA SIGUIENTE MANERA 
    // {
    //     "nombreUno": "Juan",
    //     "apellidoUno": "Perez",
    //     "folio": "123456",
    //     "numero": "7890",
    //     "sede": "Sede Central",
    //     "fecha": "2024-09-06",
    //     "estado": "ACTIVO",
    //     "cursoId": 1
    //   }
    
}
