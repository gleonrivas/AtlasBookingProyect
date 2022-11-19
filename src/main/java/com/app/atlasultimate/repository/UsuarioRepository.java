package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findTopByEmail(String username);
    boolean existsByEmail(String email);

    @Query(value = "UPDATE usuario set nombre = :nombre, apellido = :apellido, dni = :dni, email = :email, telefono = :telefono, contrasena = :contrasena where id = :id", nativeQuery = true)
    Usuario updateByID(@Param("nombre") String nombre,
                       @Param("apellido") String apellido,
                       @Param("dni") String dni,
                       @Param("email") String email,
                       @Param("telefono") String telefono,
                       @Param("contrasena") String contrasena,
                       @Param("id") Integer id);

    @Query(value = "SELECT * from usuario where id = :id", nativeQuery = true)
    Usuario usuarioporId(Integer id);

    @Query(value = "SELECT email from usuario ", nativeQuery = true)
    List<String> emailUser();

}
