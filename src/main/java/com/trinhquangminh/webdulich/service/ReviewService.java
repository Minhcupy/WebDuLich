package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.ReviewRequest;
import com.trinhquangminh.webdulich.dto.response.ReviewResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.ReviewMapper;
import com.trinhquangminh.webdulich.model.Review;
import com.trinhquangminh.webdulich.repository.ReviewRepository;
import com.trinhquangminh.webdulich.repository.TourRepository;
import com.trinhquangminh.webdulich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ReviewService {

    ReviewRepository reviewRepository;
    ReviewMapper reviewMapper;
    UserRepository userRepository;
    TourRepository tourRepository;

    public List<ReviewResponse> getAll() {
        return reviewRepository.findAll().stream().map(reviewMapper::toResponse).toList();
    }

    public ReviewResponse getById(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
        return reviewMapper.toResponse(review);
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ReviewResponse create(ReviewRequest request) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if (!tourRepository.existsById(request.getTourId())) {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }

        Review review = reviewMapper.toEntity(request);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ReviewResponse update(Integer id, ReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));
        reviewMapper.updateReview(review, request);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public void delete(Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw new AppException(ErrorCode.REVIEW_NOT_FOUND);
        }
        reviewRepository.deleteById(id);
    }

    @PreAuthorize("permitAll()")
    public List<ReviewResponse> getByTourId(Integer tourId) {
        return reviewRepository.findByTourId_Id(tourId).stream().map(reviewMapper::toResponse).toList();
    }

    public List<ReviewResponse> getByUserId(Integer userId) {
        return reviewRepository.findByUserId_Id(userId).stream().map(reviewMapper::toResponse).toList();
    }
}
