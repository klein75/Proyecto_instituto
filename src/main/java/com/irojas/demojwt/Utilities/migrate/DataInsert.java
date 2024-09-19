// package com.irojas.demojwt.Utilities.migrate;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.irojas.demojwt.Courses.Persistence.Entity.CourseEntity;
// import com.irojas.demojwt.Courses.Persistence.Repositoty.CourseRepository;
// import com.irojas.demojwt.Utilities.Enum.StateEnum;

// import jakarta.annotation.PostConstruct;

// @Service
// public class DataInsert {

//     @Autowired
//     private CourseRepository courseRepository;

//     @PostConstruct // Este método se ejecutará automáticamente después de la inyección de dependencias
//     public void insertInitialData() {
//         // Crear instancias de CourseEntity para insertar
//         CourseEntity course1 = new CourseEntity();
//         course1.setCourse("Matemáticas Básicas");
//         course1.setDescription("Curso introductorio de matemáticas");
//         course1.setCycle("Ciclo 1");
//         course1.setType("Obligatorio");
//         course1.setState(StateEnum.ACTIVO);

//         CourseEntity course2 = new CourseEntity();

//         course2.setCourse("Historia Universal");
//         course2.setDescription("Estudio de los principales acontecimientos históricos");
//         course2.setCycle("Ciclo 2");
//         course2.setType("Electivo");
//         course2.setState(StateEnum.ACTIVO);

//         CourseEntity course3 = new CourseEntity();
        
//         course3.setCourse("Sociologia");
//         course3.setDescription("Estudio de los principales movimientos sociales");
//         course3.setCycle("Ciclo 2");
//         course3.setType("Obligatorio");
//         course3.setState(StateEnum.ACTIVO);

//         CourseEntity course4 = new CourseEntity();
        
//         course4.setCourse("idionas");
//         course4.setDescription("introducion a la leguas extranjeras");
//         course4.setCycle("Ciclo 1");
//         course4.setType("electivo");
//         course4.setState(StateEnum.INACTIVO);

//         CourseEntity course5 = new CourseEntity();
        
//         course5.setCourse("biologia");
//         course5.setDescription("Estudio de las bases biologicas");
//         course5.setCycle("Ciclo 3");
//         course5.setType("Obligatorio");
//         course5.setState(StateEnum.ACTIVO);
//         // Guardar en la base de datos usando el repositorio
//         courseRepository.save(course1);
//         courseRepository.save(course2);
//         courseRepository.save(course3);
//         courseRepository.save(course4);
//         courseRepository.save(course5);
//     }
// }
