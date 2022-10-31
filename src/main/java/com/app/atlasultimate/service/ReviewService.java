package com.app.atlasultimate.service;

import com.app.atlasultimate.controller.DTO.ReviewDTO;
import com.app.atlasultimate.model.Review;
import com.app.atlasultimate.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService  {

    @Autowired
    private ReviewRepository repository;

    public void guardarReview(ReviewDTO reviewDTO){
        Review review = new Review();
        try {
            review = new Review(reviewDTO.getEstrellas(),
                    reviewDTO.getComentario(),
                    reviewDTO.getId_usuario(),
                    reviewDTO.getId_hotel());

        } catch (Exception e) {
            e.printStackTrace();
        }
        repository.save(review);
    }

}
