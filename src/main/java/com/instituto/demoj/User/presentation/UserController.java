package com.instituto.demoj.User.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.instituto.demoj.User.Utils.ResponseMessage.ResponseMessage;
import com.instituto.demoj.User.business.facade.UserFacade;
import com.instituto.demoj.User.domain.dto.generalUserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users") 
@Validated 
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade; 
    }

   
    
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createUser(@Valid @RequestBody generalUserDto userDto, BindingResult bindingResult) {
         if (bindingResult.hasErrors()) { 
            StringBuilder errorMessages = new StringBuilder(); 
            bindingResult.getFieldErrors().forEach(error -> {
                 errorMessages.append("Campo: ").append(error.getField()) 
                 .append(" - Error: ").append(error.getDefaultMessage()).append("; "); });
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST) 
                  .body(new ResponseMessage(false, errorMessages.toString(), null)); } 
                  return userFacade.createUser(userDto); }



    @Operation(summary = "Actualizar usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Error en la validación del usuario")
    })
    @PutMapping("/update/{id}")
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
    @DeleteMapping("/delete/{id}")
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
    @GetMapping("/get/{id}")
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
    @GetMapping("/all")
    public ResponseEntity<ResponseMessage> getAllUsers() {
        ResponseMessage response = userFacade.getAllUsers();
        return ResponseEntity.ok(response);
    }
    

    @Operation(summary = "Obtener alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alumnos obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron alumnos")
    })
    @GetMapping("/get/alumnos")
    public ResponseEntity<ResponseMessage> getAlumnos() {
        ResponseMessage response = userFacade.getAlumnos();
    
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
}