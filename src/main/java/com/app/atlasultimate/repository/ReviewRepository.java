package com.app.atlasultimate.repository;

import com.app.atlasultimate.controller.DTO.ReviewDTO;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByHotel(Hotel hotel);

}
