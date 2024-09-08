package com.irojas.demojwt.User.Presentation.Dto;
import java.sql.Date;
import java.util.Set;
import com.irojas.demojwt.Utilities.Enum.DoctypeEnum;
import com.irojas.demojwt.Utilities.Enum.GenderEnum;
import com.irojas.demojwt.Utilities.Enum.QuestionEnum;
import com.irojas.demojwt.Utilities.Enum.RoleEnum;
import com.irojas.demojwt.Utilities.Enum.StateEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

     private String nombreUno;
    private String nombreDos;
    private String apellidoUno;
    private String apellidoDos;
    private DoctypeEnum docType;
    private String documento;
    private Date fechaExp;
    private String lugarExp;
    private Date fechaNaci;
    private String lugarNaci;
    private String edad;
    private String tipoSangre;
    private GenderEnum sexo;
    private String correo;
    private String telefono;
    private String apodo;
    private String imagen;
    private QuestionEnum pregunta;
    private String respuesta;
    private boolean acudiente;
    private String username;
    private String password;
    private StateEnum estado;
    private Set<RoleEnum> roleNames;
}
