package com.irojas.demojwt.User.Services.ServicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.Auth.AuthResponse;
import com.irojas.demojwt.Jwt.JwtService;
import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;
import com.irojas.demojwt.Roles.Persisten.Repository.RoleRepository;
import com.irojas.demojwt.User.Persisten.Repository.UserRepository;
import com.irojas.demojwt.User.Persisten.entity.User;
import com.irojas.demojwt.User.Presentation.Dto.RegisterUserDto;
import com.irojas.demojwt.Utilities.Enum.RoleEnum;

@Service
public class UserServiceImpl {

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse registerUser(RegisterUserDto userDto) {
        User user = new User();
        user.setNombreUno(userDto.getNombreUno());
        user.setNombreDos(userDto.getNombreDos());
        user.setApellidoUno(userDto.getApellidoUno());
        user.setApellidoDos(userDto.getApellidoDos());
        user.setDocType(userDto.getDocType());
        user.setDocumento(userDto.getDocumento());
        user.setFechaExp(userDto.getFechaExp());
        user.setLugarExp(userDto.getLugarExp());
        user.setFechaNaci(userDto.getFechaNaci());
        user.setLugarNaci(userDto.getLugarNaci());
        user.setEdad(userDto.getEdad());
        user.setTipoSangre(userDto.getTipoSangre());
        user.setSexo(userDto.getSexo());
        user.setCorreo(userDto.getCorreo());
        user.setTelefono(userDto.getTelefono());
        user.setApodo(userDto.getApodo());
        user.setImagen(userDto.getImagen());
        user.setPregunta(userDto.getPregunta());
        user.setRespuesta(userDto.getRespuesta());
        user.setAcudiente(userDto.isAcudiente());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEstado(userDto.getEstado());

      Set<RoleEntity> roles = new HashSet<>();
        for (RoleEnum roleEnum : userDto.getRoleNames()) {
            RoleEntity role = roleRepository.findByRol(roleEnum)
                    .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleEnum));
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, RegisterUserDto userDto) {
        User user = getUserById(id);
    
        user.setNombreUno(userDto.getNombreUno());
        user.setNombreDos(userDto.getNombreDos());
        user.setApellidoUno(userDto.getApellidoUno());
        user.setApellidoDos(userDto.getApellidoDos());
        user.setDocType(userDto.getDocType());
        user.setDocumento(userDto.getDocumento());
        user.setFechaExp(userDto.getFechaExp());
        user.setLugarExp(userDto.getLugarExp());
        user.setFechaNaci(userDto.getFechaNaci());
        user.setLugarNaci(userDto.getLugarNaci());
        user.setEdad(userDto.getEdad());
        user.setTipoSangre(userDto.getTipoSangre());
        user.setSexo(userDto.getSexo());
        user.setCorreo(userDto.getCorreo());
        user.setTelefono(userDto.getTelefono());
        user.setApodo(userDto.getApodo());
        user.setImagen(userDto.getImagen());
        user.setPregunta(userDto.getPregunta());
        user.setRespuesta(userDto.getRespuesta());
        user.setAcudiente(userDto.isAcudiente());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEstado(userDto.getEstado());
    
        Set<RoleEntity> roles = new HashSet<>();
    if (userDto.getRoleNames() != null) {
        for (RoleEnum roleEnum : userDto.getRoleNames()) {
            RoleEntity role = roleRepository.findByRol(roleEnum)
                    .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleEnum));
            roles.add(role);
        }
    }
    user.setRoles(roles);
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

}


