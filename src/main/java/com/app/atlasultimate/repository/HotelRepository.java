package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Cliente;
import com.app.atlasultimate.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Integer > {


}
