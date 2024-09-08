package com.irojas.demojwt.User.Presentation.Controller;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irojas.demojwt.Auth.AuthResponse;
import com.irojas.demojwt.User.Persisten.Repository.UserRepository;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.User.Presentation.Dto.RegisterUserDto;
import com.irojas.demojwt.User.Services.ServicesImpl.UserServiceImpl;
import com.irojas.demojwt.User.Services.Specification.SearchUserSpecification;
import com.irojas.demojwt.User.Services.Specification.UserDocenteSpecification;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("denyAll()")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterUserDto userDto) {
        try {
           
            AuthResponse response = userService.registerUser(userDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/consultar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAll(
            @RequestParam(required = false) String nombreUno,
            @RequestParam(required = false) String nombreDos,
            @RequestParam(required = false) String apellidoUno,
            @RequestParam(required = false) String apellidoDos,
            @RequestParam(required = false) String docType,
            @RequestParam(required = false) String documento,
            @RequestParam(required = false) Date fechaExp,
            @RequestParam(required = false) String lugarExp,
            @RequestParam(required = false) Date fechaNaci,
            @RequestParam(required = false) String lugarNaci,
            @RequestParam(required = false) String edad,
            @RequestParam(required = false) String tipoSangre,
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String correo,
            @RequestParam(required = false) String telefono,
            @RequestParam(required = false) String apodo,
            @RequestParam(required = false) String pregunta,
            @RequestParam(required = false) String respuesta,
            @RequestParam(value = "acudiente", required = false) Boolean acudiente,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String role) {

        try {
            System.out.println("/consultar");

            SearchUserSpecification specification = new SearchUserSpecification(
                    nombreUno, nombreDos, apellidoUno, apellidoDos, docType, documento,
                    fechaExp, lugarExp, fechaNaci, lugarNaci, edad, tipoSangre, sexo,
                    correo, telefono, apodo, pregunta, respuesta, acudiente, username, estado, role, role);

            List<User> users = userRepository.findAll(specification);

            return ResponseEntity.ok(users);
        } catch (RuntimeException ex) {
            ex.printStackTrace(); // Imprime el stack trace para facilitar la depuración
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    // El siguiente endpoint solo filtrara a los usuarios por Alumno para las pantallas de Docente 
    @GetMapping("/consultar/docente")
    @PreAuthorize("hasRole('DOCENTE')")
    public ResponseEntity<List<User>> findAll(
        @RequestParam(required = false) String nombreUno,
        @RequestParam(required = false) String nombreDos,
        @RequestParam(required = false) String apellidoUno,
        @RequestParam(required = false) String apellidoDos,
        @RequestParam(required = false) String documento,
        @RequestParam(required = false) String edad,
        @RequestParam(required = false) String sexo,
        @RequestParam(required = false) String correo,
        @RequestParam(required = false) String telefono,
        @RequestParam(required = false) String apodo,
        @RequestParam(value = "acudiente", required = false) Boolean acudiente,
        @RequestParam(required = false) String estado,
        @RequestParam(required = false) String role) {

    try {
        System.out.println("/consultar");

        // Usa UserDocenteSpecification para filtrar por rol ALUMNO
        UserDocenteSpecification specification = UserDocenteSpecification.builder()
                .nombreUno(nombreUno)
                .nombreDos(nombreDos)
                .apellidoUno(apellidoUno)
                .apellidoDos(apellidoDos)
                .documento(documento)
                .edad(edad)
                .sexo(sexo)
                .correo(correo)
                .telefono(telefono)
                .apodo(apodo)
                .acudiente(acudiente)
                .estado(estado)
                .role("ALUMNO")  // Asegúrate de que solo el rol ALUMNO se considere
                .build();

        List<User> users = userRepository.findAll(specification);

        return ResponseEntity.ok(users);
    } catch (RuntimeException ex) {
        ex.printStackTrace(); // Imprime el stack trace para facilitar la depuración
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
}
 
    @GetMapping("Persona/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PutMapping("Actualizar/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody RegisterUserDto userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("Eliminar/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}


