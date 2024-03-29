package com.app.atlasultimate.repository;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "select * from review r where id_hotel = :id_hotel", nativeQuery = true)
    List<Review> findValues(@Param("id_hotel")Integer id_hotel);

    List<Review> findAllByHotel(Hotel hotel);

    @Query(value = "select * from review where id_hotel = :id_hotel ", nativeQuery = true)
    List<Review> findReviewsHotel(@Param("id_hotel")Integer id_hotel);

    @Query(value = "select * from review where id_usuario = :id_usuario ", nativeQuery = true)
    List<Review> findReviewsUsuario(@Param("id_usuario")Integer id_usuario);

}
