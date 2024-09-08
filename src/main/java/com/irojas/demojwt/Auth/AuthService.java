package com.irojas.demojwt.Auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Jwt.JwtService;
import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;
import com.irojas.demojwt.Roles.Persisten.Repository.RoleRepository;
import com.irojas.demojwt.User.Persisten.Repository.UserRepository;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.Utilities.Enum.RoleEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {


        // Crear la entidad User
        User user = User.builder()
            .nombreUno(request.getNombreUno())
            .nombreDos(request.getNombreDos())
            .apellidoUno(request.getApellidoUno())
            .apellidoDos(request.getApellidoDos())
            .docType(request.getDocType())
            .documento(request.getDocumento())
            .fechaExp(request.getFechaExp())
            .lugarExp(request.getLugarExp())
            .fechaNaci(request.getFechaNaci())
            .lugarNaci(request.getLugarNaci())
            .tipoSangre(request.getTipoSangre())
            .sexo(request.getSexo())
            .correo(request.getCorreo())
            .telefono(request.getTelefono())
            .apodo(request.getApodo())
            .imagen(request.getImagen())
            .pregunta(request.getPregunta())
            .respuesta(request.getRespuesta())
            .acudiente(request.isAcudiente())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .estado(StateEnum.ACTIVO)
            .build();


        // asigna roles los roles
        Set<RoleEntity> roles = new HashSet<>();
        if (request.getRoleNames() != null) {
            for (RoleEnum roleEnum : request.getRoleNames()) {
                RoleEntity role = roleRepository.findByRol(roleEnum)
                        .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleEnum));
                roles.add(role);
            }
        }
        user.setRoles(roles);


        // Guardar el usuario en la base de datos
        userRepository.save(user);



        // Generar y retornar el token de autenticación
        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }
}
