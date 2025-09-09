package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.TourScheduleRequest;
import com.trinhquangminh.webdulich.dto.response.TourScheduleResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.TourScheduleMapper;
import com.trinhquangminh.webdulich.model.Tour;
import com.trinhquangminh.webdulich.model.TourSchedule;
import com.trinhquangminh.webdulich.repository.TourRepository;
import com.trinhquangminh.webdulich.repository.TourScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TourScheduleService {

    TourScheduleRepository tourScheduleRepository;
    TourRepository tourRepository;
    TourScheduleMapper tourScheduleMapper;

    public Page<TourScheduleResponse> getAll(Pageable pageable) {
        return tourScheduleRepository.findAll(pageable)
                .map(tourScheduleMapper::toTourScheduleResponse);
    }

    public TourScheduleResponse getById(Integer id) {
        TourSchedule entity = tourScheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_SCHEDULE_NOT_FOUND));
        return tourScheduleMapper.toTourScheduleResponse(entity);
    }

    public TourScheduleResponse create(TourScheduleRequest request) {
        Tour tour = tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));

        TourSchedule entity = tourScheduleMapper.toTourSchedule(request);
        entity.setTourId(tour); // service chịu trách nhiệm set
        return tourScheduleMapper.toTourScheduleResponse(tourScheduleRepository.save(entity));
    }


    public TourScheduleResponse update(Integer id, TourScheduleRequest request) {
        TourSchedule entity = tourScheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_SCHEDULE_NOT_FOUND));

        Tour tour = tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));

        tourScheduleMapper.updateTourSchedule(entity, request, tour);
        entity.setTourId(tour);
        return tourScheduleMapper.toTourScheduleResponse(tourScheduleRepository.save(entity));
    }

    public void delete(Integer id) {
        if (!tourScheduleRepository.existsById(id)) {
            throw new AppException(ErrorCode.TOUR_SCHEDULE_NOT_FOUND);
        }
        tourScheduleRepository.deleteById(id);
    }
}
