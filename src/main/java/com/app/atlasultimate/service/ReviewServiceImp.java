package com.app.atlasultimate.service;

import com.app.atlasultimate.model.Review;
import com.app.atlasultimate.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp {

    @Autowired
    private ReviewRepository reviewRepositorio;


}
