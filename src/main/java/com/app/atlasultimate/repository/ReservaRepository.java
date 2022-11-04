package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.model.Temporada;
import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository  extends JpaRepository< Registro,Integer> {

    @Query(value="SELECT h2.nombre, h2.preferencia_pago, htp.tipo_pension, " +
            "h2.cancelacion_grat, h.tipo_habitacion\n" +
            "from habitacion h join hotel h2 on \n" +
            "h2.id = h.id_hotel join hotel_tipo_pension " +
            "htp on htp.hotel =h2.id where h.id = :id_habitacion", nativeQuery = true)
    Habitacion habitacionEscogida(@Param("id_habitacion") Integer id);


    @Query(value = "select temporada_id from  habitacion h where h.id = :id_hab ", nativeQuery = true)
    Integer IdTemporadaporIdHab(@Param("id_hab") Integer id);

    List<Registro> findAllByUsuario(Usuario usuario);

}
