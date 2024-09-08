package com.irojas.demojwt.User.Services.Specification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;
import com.irojas.demojwt.User.Persisten.entity.User;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDocenteSpecification implements Specification<User> {

    private String nombreUno;
    private String nombreDos;
    private String apellidoUno;
    private String apellidoDos;
    private String documento;
    private String edad;
    private String sexo;
    private String correo;
    private String telefono;
    private String apodo;
    private Boolean acudiente;
    private String estado;
    private String role;


    @Override
    @Nullable
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
         List<Predicate> predicates = new ArrayList<>();

         if (StringUtils.isNotBlank(nombreUno)) {
            Expression<String> nameTolowerCase = criteriaBuilder.lower(root.get("nombreUno"));
            Predicate nameLikePredicate = criteriaBuilder.like(nameTolowerCase, "%" + nombreUno.toLowerCase() + "%");
            predicates.add(nameLikePredicate);
        }

        if (StringUtils.isNotBlank(nombreDos)) {
            Expression<String> nameTolowerCase = criteriaBuilder.lower(root.get("nombreDos"));
            Predicate nameLikePredicate = criteriaBuilder.like(nameTolowerCase, "%" + nombreDos.toLowerCase() + "%");
            predicates.add(nameLikePredicate);
        }

        if (StringUtils.isNotBlank(apellidoUno)) {
            Expression<String> apellidoTolowerCase = criteriaBuilder.lower(root.get("apellidoUno"));
            Predicate apellidoLikePredicate = criteriaBuilder.like(apellidoTolowerCase, "%" + apellidoUno.toLowerCase() + "%");
            predicates.add(apellidoLikePredicate);
        }

        if (StringUtils.isNotBlank(apellidoDos)) {
            Expression<String> apellidoTolowerCase = criteriaBuilder.lower(root.get("apellidoDos"));
            Predicate apellidoLikePredicate = criteriaBuilder.like(apellidoTolowerCase, "%" + apellidoDos.toLowerCase() + "%");
            predicates.add(apellidoLikePredicate);
        }

        if (StringUtils.isNotBlank(documento)) {
            predicates.add(criteriaBuilder.equal(root.get("documento"), documento));
        }

        if (StringUtils.isNotBlank(edad)) {
            predicates.add(criteriaBuilder.equal(root.get("edad"), edad));
        }

        if (StringUtils.isNotBlank(sexo)) {
            Expression<String> sexoTolowerCase = criteriaBuilder.lower(root.get("sexo"));
            Predicate sexoLikePredicate = criteriaBuilder.like(sexoTolowerCase, "%" + sexo.toLowerCase() + "%");
            predicates.add(sexoLikePredicate);
        }

        if (StringUtils.isNotBlank(correo)) {
            Expression<String> correoTolowerCase = criteriaBuilder.lower(root.get("correo"));
            Predicate correoLikePredicate = criteriaBuilder.like(correoTolowerCase, "%" + correo.toLowerCase() + "%");
            predicates.add(correoLikePredicate);
        }

        if (StringUtils.isNotBlank(telefono)) {
            predicates.add(criteriaBuilder.equal(root.get("telefono"), telefono));
        }

        if (StringUtils.isNotBlank(apodo)) {
            Expression<String> apodoTolowerCase = criteriaBuilder.lower(root.get("apodo"));
            Predicate apodoLikePredicate = criteriaBuilder.like(apodoTolowerCase, "%" + apodo.toLowerCase() + "%");
            predicates.add(apodoLikePredicate);
        }

        if (acudiente != null) { // Solo agrega el predicado si acudiente no es nulo
            if (acudiente) {
                predicates.add(criteriaBuilder.isTrue(root.get("acudiente")));
            } else {
                predicates.add(criteriaBuilder.isFalse(root.get("acudiente")));
            }
        }

        if (StringUtils.isNotBlank(estado)) {
            Expression<String> estadoTolowerCase = criteriaBuilder.lower(root.get("estado"));
            Predicate estadoLikePredicate = criteriaBuilder.like(estadoTolowerCase, "%" + estado.toLowerCase() + "%");
            predicates.add(estadoLikePredicate);
        }
        if (StringUtils.isNotBlank(role) && "ALUMNO".equalsIgnoreCase(role)) {
            Join<User, RoleEntity> rolesJoin = root.join("roles");
            Predicate rolePredicate = criteriaBuilder.equal(criteriaBuilder.lower(rolesJoin.get("rol")), "alumno");
            predicates.add(rolePredicate);
        } else {
            // Si el rol no es "ALUMNO", retornamos una condición que no coincida con ningún usuario
            predicates.add(criteriaBuilder.disjunction()); // Esto hace que la consulta devuelva cero resultados
        }
        
          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
    }
 


