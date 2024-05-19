//package com.zprmts.tcc.ecommerce.controller;
//
//import com.zprmts.tcc.ecommerce.dto.review.ReviewResponse;
//import com.zprmts.tcc.ecommerce.mapper.ReviewMapper;
//import com.zprmts.tcc.ecommerce.service.Impl.ReviewServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/review")
//public class ReviewController {
//
//    private final ReviewServiceImpl reviewService;
////    private final SimpMessagingTemplate messagingTemplate;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<ReviewResponse>> getReviewsByPerfumeId(@PathVariable Long perfumeId) throws Exception {
//        return new ResponseEntity<>(reviewService.getReviewsByPerfumeId(perfumeId), HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<ReviewResponse> addReviewToPerfume(@Valid @RequestBody ReviewRequest reviewRequest,
//                                                             BindingResult bindingResult) {
//        ReviewResponse review = reviewMapper.addReviewToPerfume(reviewRequest, reviewRequest.getPerfumeId(), bindingResult);
//        messagingTemplate.convertAndSend("/topic/reviews/" + reviewRequest.getPerfumeId(), review);
//        return ResponseEntity.ok(review);
//    }
//}
