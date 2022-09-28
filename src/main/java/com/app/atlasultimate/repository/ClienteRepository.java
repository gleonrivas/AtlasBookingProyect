package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Usuario, Integer > {
}
