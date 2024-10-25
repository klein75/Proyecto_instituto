package com.instituto.demoj.Utilities.DataInsert;
//    package com.irojas.demojwt.Utilities.DataInsert;
//    import org.springframework.boot.CommandLineRunner;
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.transaction.annotation.Transactional;

//    import com.irojas.demojwt.Permission.Persistence.Entity.PermissionEntity;
//    import com.irojas.demojwt.Permission.Persistence.Repositoty.PermissionRepository;
//    import com.irojas.demojwt.Roles.Persisten.Entity.RoleEntity;
//    import com.irojas.demojwt.Roles.Persisten.Repository.RoleRepository;
//    import com.irojas.demojwt.Utilities.Enum.RoleEnum;
//    import com.irojas.demojwt.Utilities.Enum.StateEnum;

//    import java.util.HashSet;
//    import java.util.Set;

//    @Configuration
//    public class DataInitializer {

//        @Bean
//        CommandLineRunner initData(RoleRepository roleRepository, PermissionRepository permissionRepository) {
//            return args -> {
//                insertPermissions(permissionRepository);
//                insertRoles(roleRepository, permissionRepository);
//            };
//        }

//        @Transactional
//        public void insertPermissions(PermissionRepository permissionRepository) {

//            PermissionEntity permiso1 = new PermissionEntity();
//            permiso1.setPermiso("READ_PRIVILEGE");
//            permiso1.setDescripcion("Permiso para leer datos");
//            permiso1.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso2 = new PermissionEntity();
//            permiso2.setPermiso("QUALIFY_PRIVILEGE");
//            permiso2.setDescripcion("Permiso para Calificar alumnos");
//            permiso2.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso3 = new PermissionEntity();
//            permiso3.setPermiso("DELETE_PRIVILEGE");
//            permiso3.setDescripcion("Permiso para borrar datos");
//            permiso3.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso4 = new PermissionEntity();
//            permiso4.setPermiso("UPDATE_PRIVILEGE");
//            permiso4.setDescripcion("Permiso para actualizar datos");
//            permiso4.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso5 = new PermissionEntity();
//            permiso5.setPermiso("CREATE_PRIVILEGE");
//            permiso5.setDescripcion("Permiso para crear datos");
//            permiso5.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso6 = new PermissionEntity();
//            permiso6.setPermiso("DELETE_QUALIFY");
//            permiso6.setDescripcion("Permiso para borrar calificaciones");
//            permiso6.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso7 = new PermissionEntity();
//            permiso7.setPermiso("UPDATE_QUALIFY");
//            permiso7.setDescripcion("Permiso para actualizar calificaciones");
//            permiso7.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso8 = new PermissionEntity();
//            permiso8.setPermiso("CREATE_QUALIFY");
//            permiso8.setDescripcion("Permiso para crear calificaciones");
//            permiso8.setEstado(StateEnum.ACTIVO);

//            PermissionEntity permiso9 = new PermissionEntity();
//            permiso9.setPermiso("READ_QUALIFY");
//            permiso9.setDescripcion("Permiso para leer calificaciones");
//            permiso9.setEstado(StateEnum.ACTIVO);

//            permissionRepository.save(permiso1);
//            permissionRepository.save(permiso2);
//            permissionRepository.save(permiso3);
//            permissionRepository.save(permiso4);
//            permissionRepository.save(permiso5);
//            permissionRepository.save(permiso6);
//            permissionRepository.save(permiso7);
//            permissionRepository.save(permiso8);
//            permissionRepository.save(permiso9);
//        }

//        @Transactional
//        public void insertRoles(RoleRepository roleRepository, PermissionRepository permissionRepository) {

//            PermissionEntity readPermission = permissionRepository.findByPermiso("READ_PRIVILEGE").orElseThrow();
//            PermissionEntity qualifyPermission = permissionRepository.findByPermiso("QUALIFY_PRIVILEGE").orElseThrow();
//            PermissionEntity deletePermission = permissionRepository.findByPermiso("DELETE_PRIVILEGE").orElseThrow();
//            PermissionEntity updatePermission = permissionRepository.findByPermiso("UPDATE_PRIVILEGE").orElseThrow();
//            PermissionEntity createPermission = permissionRepository.findByPermiso("CREATE_PRIVILEGE").orElseThrow();
//            PermissionEntity docentePermission = permissionRepository.findByPermiso("DELETE_QUALIFY").orElseThrow();
//            PermissionEntity docente2Permission= permissionRepository.findByPermiso("UPDATE_QUALIFY").orElseThrow();
//            PermissionEntity docente3Permission= permissionRepository.findByPermiso("CREATE_QUALIFY").orElseThrow();
//            PermissionEntity docente4Permission= permissionRepository.findByPermiso("READ_QUALIFY").orElseThrow();

//            Set<PermissionEntity> adminPermissions = new HashSet<>();
//            adminPermissions.add(readPermission);
//            adminPermissions.add(qualifyPermission);
//            adminPermissions.add(deletePermission);
//            adminPermissions.add(updatePermission);
//            adminPermissions.add(createPermission);

//            Set<PermissionEntity> docentePermissions = new HashSet<>();
//            docentePermissions.add(readPermission);
//            docentePermissions.add(qualifyPermission);
//            docentePermissions.add(docentePermission);
//            docentePermissions.add(docente2Permission);
//            docentePermissions.add(docente3Permission);
//            docentePermissions.add(docente4Permission);

//            Set<PermissionEntity> alumnoPermissions = new HashSet<>();
//            alumnoPermissions.add(readPermission);
//            alumnoPermissions.add(docente4Permission);

//            RoleEntity adminRole = new RoleEntity();
//            adminRole.setRol(RoleEnum.ADMIN);
//            adminRole.setEstado(StateEnum.ACTIVO);
//            adminRole.setPermissions(adminPermissions);

//            RoleEntity docenteRole = new RoleEntity();
//            docenteRole.setRol(RoleEnum.DOCENTE);
//            docenteRole.setEstado(StateEnum.ACTIVO);
//            docenteRole.setPermissions(docentePermissions);

//            RoleEntity alumnoRole = new RoleEntity();
//            alumnoRole.setRol(RoleEnum.ALUMNO);
//            alumnoRole.setEstado(StateEnum.ACTIVO);
//            alumnoRole.setPermissions(alumnoPermissions);



//            roleRepository.save(adminRole);
//            roleRepository.save(docenteRole);
//            roleRepository.save(alumnoRole);
//        }
//    }
