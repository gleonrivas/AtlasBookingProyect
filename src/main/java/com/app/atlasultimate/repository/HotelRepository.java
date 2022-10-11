package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Cliente, Integer > {

}
