package com.instituto.demoj.User.business.services.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.instituto.demoj.Auth.AuthResponse;
import com.instituto.demoj.Jwt.JwtService;
import com.instituto.demoj.Roles.Persisten.Repository.RoleRepository;
import com.instituto.demoj.Roles.domain.Entity.RoleEntity;
import com.instituto.demoj.User.Persistence.UserRepository;
import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.User.business.mapper.UserMapper;
import com.instituto.demoj.User.business.services.IUserService;
import com.instituto.demoj.User.domain.dto.generalUserDto;
import com.instituto.demoj.User.domain.dto.teacherUserDto;
import com.instituto.demoj.User.domain.entity.User;
import com.instituto.demoj.Utilities.Enum.RoleEnum;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Validator validator;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtServices;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
            RoleRepository roleRepository, Validator validator,
            PasswordEncoder passwordEncoder, JwtService jwtServices) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.jwtServices = jwtServices;
    }

    @Override
    public ResponseMessage saveUser(generalUserDto generalUserDto) {
        // Validar el DTO
        Set<ConstraintViolation<generalUserDto>> violations = validator.validate(generalUserDto);
        if (!violations.isEmpty()) {
            String errorMessages = violations.stream()
                    .map(v -> "Campo: " + v.getPropertyPath() + " - Error: " + v.getMessage())
                    .collect(Collectors.joining("; "));
            return new ResponseMessage(false, errorMessages, null);
        }

        // Verificar si el username o email ya existen
        if (userRepository.existsByUsername(generalUserDto.getUsername())) {
            return new ResponseMessage(false, "El nombre de usuario ya existe", null);
        }
        if (userRepository.existsByEmail(generalUserDto.getEmail())) {
            return new ResponseMessage(false, "El correo electrónico ya está en uso", null);
        }

        try {
            // Mapeo del DTO a la entidad
            User user = userMapper.toEntity(generalUserDto);

            // Asignación de roles
            Set<RoleEntity> roles = new HashSet<>();
            for (RoleEnum roleName : generalUserDto.getRoles()) {
                RoleEntity role = roleRepository.findByRol(roleName)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado con el nombre: " + roleName));
                roles.add(role);
            }
            user.setRoles(roles);

            // Encriptar la contraseña
            String encodedPassword = passwordEncoder.encode(generalUserDto.getPassword());
            user.setPassword(encodedPassword);

            // Guardar el usuario
            user = userRepository.save(user);
            // Generar el token 
            String token = jwtServices.getToken(user); 
            // Retornar la respuesta con el token 
            AuthResponse authResponse = AuthResponse.builder() .token(token) .build();

            return new ResponseMessage(true, "Usuario guardado correctamente", user);

        } catch (Exception e) {
            return new ResponseMessage(false, "Error al guardar el usuario: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage updateUser(Long id, @Valid generalUserDto generalUserDto) {
        // Validación del DTO
        String validationErrors = validateDto(generalUserDto);
        if (validationErrors != null) {
            return new ResponseMessage(false, validationErrors, null);
        }
    
        // Verificar si el usuario existe
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseMessage(false, "Usuario no encontrado", null);
        }
    
        try {
            User userToUpdate = optionalUser.get();
    
            // Actualizar los campos del usuario con los datos del DTO
            userMapper.updateEntityFromDto(generalUserDto, userToUpdate);
    
            // Asignación de roles basada en los nombres de roles
            Set<RoleEntity> roles = new HashSet<>();
            for (RoleEnum roleEnum : generalUserDto.getRoles()) {
                RoleEntity role = roleRepository.findByRol(roleEnum)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado con el nombre: " + roleEnum));
                roles.add(role);
            }
            userToUpdate.setRoles(roles);
    
            // Guardar cambios
            userRepository.save(userToUpdate);
            return new ResponseMessage(true, "Usuario actualizado correctamente", userToUpdate);
    
        } catch (Exception e) {
            return new ResponseMessage(false, "Error al actualizar el usuario: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return new ResponseMessage(false, "Usuario no encontrado", null);
        }
        try {
            userRepository.deleteById(id);
            return new ResponseMessage(true, "Usuario eliminado correctamente", null);
        } catch (Exception e) {
            return new ResponseMessage(false, "Error al eliminar el usuario: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseMessage getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return new ResponseMessage(true, "Usuario encontrado", optionalUser.get());
        } else {
            return new ResponseMessage(false, "Usuario no encontrado", null);
        }
    }

    @Override
    public ResponseMessage getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return new ResponseMessage(true, "Lista de usuarios obtenida", users);
        } else {
            return new ResponseMessage(false, "No se encontraron usuarios", null);
        }
    }
    @Override
    public ResponseMessage getAlumnos() {
        List<User> alumnos = userRepository.findByRolesRol(RoleEnum.ALUMNO);
        if (!alumnos.isEmpty()) {
            List<teacherUserDto> alumnosDto = alumnos.stream()
                    .map(userMapper::toTeacherDto)
                    .collect(Collectors.toList());
            return new ResponseMessage(true, "Usuarios con rol ALUMNO encontrados", alumnosDto);
        } else {
            return new ResponseMessage(false, "No se encontraron usuarios con rol ALUMNO", null);
        }
    }


    private String validateDto(generalUserDto generalUserDto) {
        Set<ConstraintViolation<generalUserDto>> violations = validator.validate(generalUserDto);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(v -> "Campo: " + v.getPropertyPath() + " - Error: " + v.getMessage())
                    .collect(Collectors.joining("; "));
        }
        return null;
    }
}
