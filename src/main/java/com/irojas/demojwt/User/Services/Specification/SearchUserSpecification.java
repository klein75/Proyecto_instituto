package com.irojas.demojwt.User.Services.Specification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserSpecification implements Specification<User> {

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
    private Boolean acudiente;
    private String username;
    private String password;
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

        if (StringUtils.isNotBlank(docType)) {
            Expression<String> docTypeTolowerCase = criteriaBuilder.lower(root.get("docType"));
            Predicate docTypeLikePredicate = criteriaBuilder.like(docTypeTolowerCase, "%" + docType.toLowerCase() + "%");
            predicates.add(docTypeLikePredicate);
        }

        if (StringUtils.isNotBlank(documento)) {
            predicates.add(criteriaBuilder.equal(root.get("documento"), documento));
        }

        if (fechaExp != null) {
            predicates.add(criteriaBuilder.equal(root.get("fechaExp"), fechaExp));
        }

        if (StringUtils.isNotBlank(lugarExp)) {
            Expression<String> lugarTolowerCase = criteriaBuilder.lower(root.get("lugarExp"));
            Predicate lugarLikePredicate = criteriaBuilder.like(lugarTolowerCase, "%" + lugarExp.toLowerCase() + "%");
            predicates.add(lugarLikePredicate);
        }

        if (fechaNaci != null) {
            predicates.add(criteriaBuilder.equal(root.get("fechaNaci"), fechaNaci));
        }

        if (StringUtils.isNotBlank(lugarNaci)) {
            Expression<String> lugarTolowerCase = criteriaBuilder.lower(root.get("lugarNaci"));
            Predicate lugarLikePredicate = criteriaBuilder.like(lugarTolowerCase, "%" + lugarNaci.toLowerCase() + "%");
            predicates.add(lugarLikePredicate);
        }

        if (StringUtils.isNotBlank(edad)) {
            predicates.add(criteriaBuilder.equal(root.get("edad"), edad));
        }

        if (StringUtils.isNotBlank(tipoSangre)) {
            Expression<String> tipoSangreTolowerCase = criteriaBuilder.lower(root.get("tipoSangre"));
            Predicate tipoSangreLikePredicate = criteriaBuilder.like(tipoSangreTolowerCase, "%" + tipoSangre.toLowerCase() + "%");
            predicates.add(tipoSangreLikePredicate);
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

        if (StringUtils.isNotBlank(pregunta)) {
            Expression<String> preguntaTolowerCase = criteriaBuilder.lower(root.get("pregunta"));
            Predicate preguntaLikePredicate = criteriaBuilder.like(preguntaTolowerCase, "%" + pregunta.toLowerCase() + "%");
            predicates.add(preguntaLikePredicate);
        }

        if (StringUtils.isNotBlank(respuesta)) {
            Expression<String> respuestaTolowerCase = criteriaBuilder.lower(root.get("respuesta"));
            Predicate respuestaLikePredicate = criteriaBuilder.like(respuestaTolowerCase, "%" + respuesta.toLowerCase() + "%");
            predicates.add(respuestaLikePredicate);
        }

        if (acudiente != null) { // Solo agrega el predicado si acudiente no es nulo
            if (acudiente) {
                predicates.add(criteriaBuilder.isTrue(root.get("acudiente")));
            } else {
                predicates.add(criteriaBuilder.isFalse(root.get("acudiente")));
            }
        }

        if (StringUtils.isNotBlank(username)) {
            Expression<String> usernameTolowerCase = criteriaBuilder.lower(root.get("username"));
            Predicate usernameLikePredicate = criteriaBuilder.like(usernameTolowerCase, "%" + username.toLowerCase() + "%");
            predicates.add(usernameLikePredicate);
        }

        if (StringUtils.isNotBlank(password)) {
            Expression<String> passwordTolowerCase = criteriaBuilder.lower(root.get("password"));
            Predicate passwordLikePredicate = criteriaBuilder.like(passwordTolowerCase, "%" + password.toLowerCase() + "%");
            predicates.add(passwordLikePredicate);
        }

        if (StringUtils.isNotBlank(estado)) {
            Expression<String> estadoTolowerCase = criteriaBuilder.lower(root.get("estado"));
            Predicate estadoLikePredicate = criteriaBuilder.like(estadoTolowerCase, "%" + estado.toLowerCase() + "%");
            predicates.add(estadoLikePredicate);
        }
          if (StringUtils.isNotBlank(role)) {
          Join<User, RoleEntity> rolesJoin = root.join("roles");
          Predicate rolePredicate = criteriaBuilder.equal(rolesJoin.get("rol"), role.toLowerCase());
          predicates.add(rolePredicate);
          }
        
          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

