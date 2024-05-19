package com.zprmts.tcc.ecommerce.service;

import com.zprmts.tcc.ecommerce.domain.Review;
import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewsByPerfumeId(Long perfumeId) throws RegraDeNegocioException;

    Review addReviewToPerfume(Review review, Long perfumeId) throws RegraDeNegocioException;
}
