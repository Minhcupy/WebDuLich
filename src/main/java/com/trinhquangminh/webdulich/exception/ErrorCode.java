package com.trinhquangminh.webdulich.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1009, "Invalid email format", HttpStatus.BAD_REQUEST),
    EMAIL_REQUIRED(1010, "Email is required", HttpStatus.BAD_REQUEST),
    HOTEL_NOT_FOUND(1011, "Hotel not found", HttpStatus.NOT_FOUND),
    HOTEL_ALREADY_EXISTS(1012, "Hotel already exists", HttpStatus.BAD_REQUEST),
    BOOKING_NOT_FOUND(1013, "Booking not found", HttpStatus.NOT_FOUND),
    TICKET_NOT_FOUND(1014, "Ticket not found", HttpStatus.NOT_FOUND),
    REVIEW_NOT_FOUND(1015, "Review not found", HttpStatus.NOT_FOUND),
    PROMOTION_NOT_FOUND(1016, "Promotion not found", HttpStatus.NOT_FOUND),
    PAYMENT_NOT_FOUND(1017, "Payment not found", HttpStatus.NOT_FOUND),


    // ----- Category -----
    CATEGORY_NOT_FOUND(2001, "Category not found", HttpStatus.NOT_FOUND),
    CATEGORY_ALREADY_EXISTS(2002, "Category already exists", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_INVALID(2003, "Category name is invalid", HttpStatus.BAD_REQUEST),

    // ----- Tour -----
    TOUR_NOT_FOUND(3001, "Tour not found", HttpStatus.NOT_FOUND),
    TOUR_ALREADY_EXISTS(3002, "Tour already exists", HttpStatus.BAD_REQUEST),
    TOUR_NAME_INVALID(3003, "Tour name is invalid", HttpStatus.BAD_REQUEST),
    TOUR_PRICE_INVALID(3004, "Tour price must be greater than 0", HttpStatus.BAD_REQUEST),

    // ----- Tour Detail -----
    TOUR_DETAIL_NOT_FOUND(4001, "Tour detail not found", HttpStatus.NOT_FOUND),
    TOUR_DETAIL_INVALID_DATE(4002, "Invalid date range for tour detail", HttpStatus.BAD_REQUEST),
    TOUR_DETAIL_ALREADY_EXISTS(4003, "Tour detail already exists", HttpStatus.BAD_REQUEST),

    // ----- Location -----
    LOCATION_NOT_FOUND(5001, "Location not found", HttpStatus.NOT_FOUND),
    LOCATION_ALREADY_EXISTS(5002, "Location already exists", HttpStatus.BAD_REQUEST),
    LOCATION_NAME_INVALID(5003, "Location name is invalid", HttpStatus.BAD_REQUEST),

    TOUR_SCHEDULE_NOT_FOUND(6001, "Tour schedule not found", HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
