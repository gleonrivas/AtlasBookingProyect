package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Usuario, Integer > {
}
