package com.instituto.demoj.User.domain.dto;

import java.util.Date;
import java.util.Set;

import com.instituto.demoj.User.Utils.DoctypeEnum;
import com.instituto.demoj.Utilities.Enum.RoleEnum;
import com.instituto.demoj.Utilities.Enum.StateEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class generalUserDto {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    private String Name;

    private String lastName;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "El correo electrónico debe tener un formato válido.")
    private String email;

    @NotBlank(message = "El número de teléfono es obligatorio.")
    @Pattern(regexp = "^[+]?[0-9]{7,15}$", message = "El teléfono debe ser un número válido.")
    private String phone;

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 5, max = 20, message = "El nombre de usuario debe tener entre 5 y 20 caracteres.")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial.")
    private String password;

    @NotNull(message = "El estado es obligatorio.")
    private StateEnum state;

    @NotEmpty(message = "El rol debe de ser asignado.")
    private Set<RoleEnum> roles;
}
