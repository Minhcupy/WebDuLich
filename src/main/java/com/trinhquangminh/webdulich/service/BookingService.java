package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.BookingRequest;
import com.trinhquangminh.webdulich.dto.response.BookingResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.BookingMapper;
import com.trinhquangminh.webdulich.model.Booking;
import com.trinhquangminh.webdulich.model.Tour;
import com.trinhquangminh.webdulich.model.Users;
import com.trinhquangminh.webdulich.repository.BookingRepository;
import com.trinhquangminh.webdulich.repository.TourRepository;
import com.trinhquangminh.webdulich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UserRepository userRepository;
    TourRepository tourRepository;

    public List<BookingResponse> getAll() {
        return bookingRepository.findAll().stream().map(bookingMapper::toResponse).toList();
    }

    public BookingResponse getById(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));
        return bookingMapper.toResponse(booking);
    }

    public BookingResponse create(BookingRequest request) {
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Tour tour = tourRepository.findById(request.getTourId())
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));

        Booking booking = bookingMapper.toEntity(request);
        booking.setUserId(user);
        booking.setTourId(tour);

        // Tính toán giá tiền
        booking.setTotalPrice(request.getNumberOfPeople() * tour.getPrice());

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    public BookingResponse update(Integer id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_FOUND));

        bookingMapper.updateBooking(booking, request);

        if (request.getUserId() != null) {
            booking.setUserId(userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
        }
        if (request.getTourId() != null) {
            booking.setTourId(tourRepository.findById(request.getTourId())
                    .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND)));
        }

        booking.setTotalPrice(booking.getNumberOfPeople() * booking.getTourId().getPrice());

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    public void delete(Integer id) {
        if (!bookingRepository.existsById(id)) {
            throw new AppException(ErrorCode.BOOKING_NOT_FOUND);
        }
        bookingRepository.deleteById(id);
    }

    public List<BookingResponse> getByUserId(Integer userId) {
        return bookingRepository.findByUserId_Id(userId).stream().map(bookingMapper::toResponse).toList();
    }

    public List<BookingResponse> getByTourId(Integer tourId) {
        return bookingRepository.findByTourId_Id(tourId).stream().map(bookingMapper::toResponse).toList();
    }

    public List<BookingResponse> getByStatus(String status) {
        return bookingRepository.findByStatus(status).stream().map(bookingMapper::toResponse).toList();
    }
}
