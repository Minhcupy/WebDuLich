package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.TourRequest;
import com.trinhquangminh.webdulich.dto.response.TourResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.TourMapper;
import com.trinhquangminh.webdulich.model.Category;
import com.trinhquangminh.webdulich.model.Location;
import com.trinhquangminh.webdulich.model.Tour;
import com.trinhquangminh.webdulich.repository.CategoryRepository;
import com.trinhquangminh.webdulich.repository.LocationRepository;
import com.trinhquangminh.webdulich.repository.TourRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TourService {

    TourRepository tourRepository;
    TourMapper tourMapper;
    CategoryRepository categoryRepository;
    LocationRepository locationRepository;

    // Lấy tất cả tours
    @PreAuthorize("permitAll()")
    public List<TourResponse> getAllTours() {
        return tourRepository.findAll()
                .stream()
                .map(tourMapper::toTourResponse)
                .toList();
    }

    // Lấy tour theo id
    public TourResponse getTourById(Integer id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));
        return tourMapper.toTourResponse(tour);
    }

    // Tạo tour mới
    @PreAuthorize("hasAuthority('ADMIN')")
    public TourResponse createTour(TourRequest request) {
        if (tourRepository.existsByNameIgnoreCase(request.getName())) {
            throw new AppException(ErrorCode.TOUR_ALREADY_EXISTS);
        }
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_FOUND));

        Tour tour = tourMapper.toTour(request);
        tour.setCategoryId(category);
        tour.setLocationId(location);

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    // Cập nhật tour
    @PreAuthorize("hasAuthority('ADMIN')")
    public TourResponse updateTour(Integer id, TourRequest request) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));

        tourMapper.updateTour(tour, request);

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            tour.setCategoryId(category);
        }

        if (request.getLocationId() != null) {
            Location location = locationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_FOUND));
            tour.setLocationId(location);
        }

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    // Xóa tour
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTour(Integer id) {
        if (!tourRepository.existsById(id)) {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }
        tourRepository.deleteById(id);
    }

    // Tìm kiếm theo tên
    public Page<TourResponse> searchToursByName(String name, Pageable pageable) {
        return tourRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(tourMapper::toTourResponse);
    }

    // Lọc tour nâng cao
    public Page<TourResponse> filterTours(
            String name,
            Double minPrice,
            Double maxPrice,
            String startDate,
            String status,
            Pageable pageable
    ) {
        Specification<Tour> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (startDate != null && !startDate.isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), startDate));
            }
            if (status != null && !status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return tourRepository.findAll(spec, pageable).map(tourMapper::toTourResponse);
    }
}
