package com.instituto.demoj.User.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.User.business.facade.UserFacade;
import com.instituto.demoj.User.domain.dto.generalUserDto;
import com.instituto.demoj.Utilities.ResponseMessage.ResponseMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users") // Asegúrate de establecer el mapeo correcto
@Validated // Si estás utilizando validaciones
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade; // Asigna la instancia correcta
    }

    @Operation(summary = "Crear usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la validación del usuario"),
            @ApiResponse(responseCode = "403", description = "username o email ya existen"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody generalUserDto userDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessages.append("Campo: ").append(error.getField())
                        .append(" - Error: ").append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(false, errorMessages.toString(), null));
        }

        ResponseMessage responseMessage = userFacade.createUser(userDto);

        if (responseMessage.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } else if (responseMessage.getMessage().contains("ya existe")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @Operation(summary = "Actualizar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error en la validación del usuario")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable Long id, @Valid @RequestBody generalUserDto generalUserDto) {
        ResponseMessage response = userFacade.updateUser(id, generalUserDto);
    
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else if (response.getMessage().contains("no encontrado")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    

    @Operation(summary = "Eliminar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        ResponseMessage response = userFacade.deleteUser(id);
    
        if (response.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
    
    @Operation(summary = "Obtener usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getUserById(@PathVariable Long id) {
        ResponseMessage response = userFacade.getUserById(id);
    
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    

    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida")
    })
    @GetMapping
    public ResponseEntity<ResponseMessage> getAllUsers() {
        ResponseMessage response = userFacade.getAllUsers();
        return ResponseEntity.ok(response);
    }
    

    @Operation(summary = "Obtener alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alumnos obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron alumnos")
    })
    @GetMapping("/alumnos")
    public ResponseEntity<ResponseMessage> getAlumnos() {
        ResponseMessage response = userFacade.getAlumnos();
    
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
}