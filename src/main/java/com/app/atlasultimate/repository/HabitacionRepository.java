package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import org.hibernate.mapping.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository  extends JpaRepository<Habitacion,Integer> {

}
