package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository  extends JpaRepository<Habitacion,Integer> {

}
