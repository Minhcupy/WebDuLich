package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.LocationRequest;
import com.trinhquangminh.webdulich.dto.response.LocationResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.LocationMapper;
import com.trinhquangminh.webdulich.model.Location;
import com.trinhquangminh.webdulich.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LocationService {

    LocationRepository locationRepository;
    LocationMapper locationMapper;

    public Page<LocationResponse> getAll(Pageable pageable) {
        return locationRepository.findAll(pageable)
                .map(locationMapper::toLocationResponse);
    }

    public LocationResponse getById(Integer id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_FOUND));
        return locationMapper.toLocationResponse(location);
    }

    public LocationResponse create(LocationRequest request) {
        Location location = locationMapper.toLocation(request);
        return locationMapper.toLocationResponse(locationRepository.save(location));
    }

    public LocationResponse update(Integer id, LocationRequest request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_FOUND));
        locationMapper.updateLocation(location, request);
        return locationMapper.toLocationResponse(locationRepository.save(location));
    }

    public void delete(Integer id) {
        if (!locationRepository.existsById(id)) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }
        locationRepository.deleteById(id);
    }

    public Page<LocationResponse> searchByName(String name, Pageable pageable) {
        return locationRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(locationMapper::toLocationResponse);
    }
}
