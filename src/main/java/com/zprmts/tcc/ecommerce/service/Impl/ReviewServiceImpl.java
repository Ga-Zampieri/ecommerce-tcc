//package com.zprmts.tcc.ecommerce.service.Impl;
//
//import com.zprmts.tcc.ecommerce.domain.Perfume;
//import com.zprmts.tcc.ecommerce.domain.Review;
//import com.zprmts.tcc.ecommerce.exception.RegraDeNegocioException;
//import com.zprmts.tcc.ecommerce.repository.PerfumeRepository;
//import com.zprmts.tcc.ecommerce.repository.ReviewRepository;
//import com.zprmts.tcc.ecommerce.service.ReviewService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static com.zprmts.tcc.ecommerce.constants.ErrorMessage.PERFUME_NOT_FOUND;
//
//@Service
//@RequiredArgsConstructor
//public class ReviewServiceImpl implements ReviewService {
//
//    private final PerfumeServiceImpl perfumeService;
//    private final ReviewRepository reviewRepository;
//
//    @Override
//    public List<Review> getReviewsByPerfumeId(Long perfumeId) throws RegraDeNegocioException {
//        Perfume perfume = perfumeService.getById(perfumeId);
//        return perfume.getReviews();
//    }
//
//    @Override
//    @Transactional
//    public Review addReviewToPerfume(Review review, Long perfumeId) throws RegraDeNegocioException {
//        Perfume perfume = perfumeRepository.findById(perfumeId)
//                .orElseThrow(() -> new RegraDeNegocioException(PERFUME_NOT_FOUND));
//        List<Review> reviews = perfume.getReviews();
//        reviews.add(review);
//        double totalReviews = reviews.size();
//        double sumRating = reviews.stream().mapToInt(Review::getRating).sum();
//        perfume.setPerfumeRating(sumRating / totalReviews);
//        return reviewRepository.save(review);
//    }
//}
