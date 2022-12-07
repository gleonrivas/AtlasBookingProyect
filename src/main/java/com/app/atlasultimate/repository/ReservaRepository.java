package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.model.Temporada;
import com.app.atlasultimate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
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

    @Query(value="select id_habitacion from registro where activa = 0", nativeQuery = true)
    List<Integer> listaidHabporRegistro();

    Registro findFirstByUsuarioOrderByIdDesc(Usuario usuario);

    @Query(value="select * from registro where id_habitacion = :id_habitacion", nativeQuery = true)
    List<Registro> listaregistroPorIdHab(@Param("id_habitacion") Integer id_habitacion);

    @Query(value="select * from registro where id_usuario = :id_usuario", nativeQuery = true)
    List<Registro> listaregistroPorUsuario(@Param("id_usuario") Integer id_usuario);

    @Query(value="select id from registro order by id desc limit 1", nativeQuery = true)
    Integer ultimoRegistro();

    @Query(value="select * from registro where codigo = :codigo", nativeQuery = true)
    Registro registroporCodigo(@Param("codigo") String codigo);
    void deleteAllByHabitacion(Habitacion habitacion);

    @Query(value = "UPDATE registro SET activa = 1 WHERE id = :id", nativeQuery = true)
    Registro updateById(@Param("id") Integer id);
    @Query(value = "UPDATE registro SET precio_total_dias = :precio WHERE id = :id", nativeQuery = true)
    Registro updateByIdPrecio(@Param("precio")Double precio, @Param("id") Integer id);



}
