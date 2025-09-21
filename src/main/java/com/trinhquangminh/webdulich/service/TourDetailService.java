package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.TourDetailRequest;
import com.trinhquangminh.webdulich.dto.response.TourDetailResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.TourDetailMapper;
import com.trinhquangminh.webdulich.model.TourDetail;
import com.trinhquangminh.webdulich.repository.LocationRepository;
import com.trinhquangminh.webdulich.repository.TourDetailRepository;
import com.trinhquangminh.webdulich.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TourDetailService {

    TourDetailRepository tourDetailRepository;
    TourDetailMapper tourDetailMapper;
    TourRepository tourRepository;
    LocationRepository locationRepository;

    public List<TourDetailResponse> getAll() {
        return tourDetailRepository.findAll()
                .stream()
                .map(tourDetailMapper::toTourDetailResponse)
                .toList();
    }

    public TourDetailResponse getById(Integer id) {
        TourDetail detail = tourDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_DETAIL_NOT_FOUND));
        return tourDetailMapper.toTourDetailResponse(detail);
    }

    public TourDetailResponse create(TourDetailRequest request) {
        if (!tourRepository.existsById(request.getTourId())) {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }

        if (!locationRepository.existsById(request.getLocationId())) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }

        TourDetail detail = tourDetailMapper.toTourDetail(request);
        return tourDetailMapper.toTourDetailResponse(tourDetailRepository.save(detail));
    }

    public TourDetailResponse update(Integer id, TourDetailRequest request) {
        TourDetail detail = tourDetailRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_DETAIL_NOT_FOUND));

        // Kiểm tra lại nếu có thay đổi tour/location
        if (request.getTourId() != null && !tourRepository.existsById(request.getTourId())) {
            throw new AppException(ErrorCode.TOUR_NOT_FOUND);
        }
        if (request.getLocationId() != null && !locationRepository.existsById(request.getLocationId())) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }

        tourDetailMapper.updateTourDetail(detail, request);

        if (request.getTourId() != null) {
            detail.setTourId(new com.trinhquangminh.webdulich.model.Tour(request.getTourId()));
        }
        if (request.getLocationId() != null) {
            detail.setLocationId(new com.trinhquangminh.webdulich.model.Location(request.getLocationId()));
        }

        return tourDetailMapper.toTourDetailResponse(tourDetailRepository.save(detail));
    }

    public void delete(Integer id) {
        if (!tourDetailRepository.existsById(id)) {
            throw new AppException(ErrorCode.TOUR_DETAIL_NOT_FOUND);
        }
        tourDetailRepository.deleteById(id);
    }

    public List<TourDetailResponse> getByTourId(Integer tourId) {
        return tourDetailRepository.findByTourId_Id(tourId)
                .stream()
                .map(tourDetailMapper::toTourDetailResponse)
                .toList();
    }

    public List<TourDetailResponse> getByLocationId(Integer locationId) {
        return tourDetailRepository.findByLocationId_Id(locationId)
                .stream()
                .map(tourDetailMapper::toTourDetailResponse)
                .toList();
    }
}
