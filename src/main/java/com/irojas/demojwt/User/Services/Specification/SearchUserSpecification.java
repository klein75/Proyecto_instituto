package com.irojas.demojwt.User.Services.Specification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.irojas.demojwt.User.Persisten.entity.User;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserSpecification implements Specification<User>{

      private String nombreUno;
    private String nombreDos;
    private String apellidoUno;
    private String apellidoDos;
    private String docType;
    private String documento;
    private Date fechaExp;
    private String lugarExp;
    private Date fechaNaci;
    private String lugarNaci;
    private String edad;
    private String tipoSangre;
    private String sexo;
    private String correo;
    private String telefono;
    private String apodo;
    private String pregunta;
    private String respuesta;
    private boolean acudiente;
    private String username;
    private String password;
    private String estado;
    private String role;


    @Override
    @Nullable
    public Predicate toPredicate(Root<User> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
       query.orderBy(CriteriaBuilder.asc(root.get("id")));
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(nombreUno)) {
          Predicate nameLikPredicate = CriteriaBuilder.like(root.get("nombreUno"), "%" .concat(nombreUno) .concat("%"));

          predicates.add(nameLikPredicate);
        }
        
    return null;
    }

}

