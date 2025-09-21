package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.HotelRequest;
import com.trinhquangminh.webdulich.dto.response.HotelResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.HotelMapper;
import com.trinhquangminh.webdulich.model.Hotel;
import com.trinhquangminh.webdulich.model.Location;
import com.trinhquangminh.webdulich.repository.HotelRepository;
import com.trinhquangminh.webdulich.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class HotelService {

    HotelRepository hotelRepository;
    HotelMapper hotelMapper;
    LocationRepository locationRepository;

    public Page<HotelResponse> getAll(Pageable pageable) {
        return hotelRepository.findAll(pageable).map(hotelMapper::toResponse);
    }

    public HotelResponse getById(Integer id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));
        return hotelMapper.toResponse(hotel);
    }

    public HotelResponse create(HotelRequest request) {
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_FOUND));

        Hotel hotel = hotelMapper.toEntity(request);
        hotel.setLocation(location);
        return hotelMapper.toResponse(hotelRepository.save(hotel));
    }

    public HotelResponse update(Integer id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_FOUND));

        hotelMapper.updateHotel(hotel, request);

        if (request.getLocationId() != null) {
            Location location = locationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_FOUND));
            hotel.setLocation(location);
        }

        return hotelMapper.toResponse(hotelRepository.save(hotel));
    }

    public void delete(Integer id) {
        if (!hotelRepository.existsById(id)) {
            throw new AppException(ErrorCode.HOTEL_NOT_FOUND);
        }
        hotelRepository.deleteById(id);
    }

    public Page<HotelResponse> searchByName(String name, Pageable pageable) {
        return hotelRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(hotelMapper::toResponse);
    }
}
