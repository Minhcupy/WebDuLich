package com.trinhquangminh.webdulich.utils;

import com.trinhquangminh.webdulich.config.VNPayConfig;
import com.trinhquangminh.webdulich.model.Payment;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

public class VNPayUtil {

    // Tạo URL thanh toán cho VNPay
    public static String createPaymentUrl(Payment payment, VNPayConfig config, HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", "2.1.0");
        params.put("vnp_Command", "pay");
        params.put("vnp_TmnCode", config.getTmnCode());
        params.put("vnp_Amount", String.valueOf((long) (payment.getAmount() * 100))); // VNPay yêu cầu x100
        params.put("vnp_CurrCode", "VND");
        params.put("vnp_TxnRef", String.valueOf(payment.getId())); // ID payment làm mã giao dịch
        params.put("vnp_OrderInfo", "Thanh toan don hang " + payment.getId());
        params.put("vnp_OrderType", "other");
        params.put("vnp_Locale", "vn");
        params.put("vnp_ReturnUrl", config.getReturnUrl());
        params.put("vnp_IpAddr", getIpAddress(request));

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        String createDate = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());
        params.put("vnp_CreateDate", createDate);

        // Sắp xếp tham số
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII)).append('&');
                query.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII)).append('&');
            }
        }

        // Xóa ký tự & thừa
        hashData.setLength(hashData.length() - 1);
        query.setLength(query.length() - 1);

        String secureHash = hmacSHA512(config.getSecretKey(), hashData.toString());
        query.append("&vnp_SecureHash=").append(secureHash);

        return config.getPayUrl() + "?" + query.toString();
    }

    // Xác minh chữ ký khi VNPay redirect về
    public static boolean validateSignature(Map<String, String> params, String secretKey) {
        String vnp_SecureHash = params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");

        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName).append('=').append(fieldValue).append('&');
            }
        }
        hashData.setLength(hashData.length() - 1);

        String signValue = hmacSHA512(secretKey, hashData.toString());
        return signValue.equals(vnp_SecureHash);
    }

    // Hàm tạo chữ ký SHA512
    private static String hmacSHA512(String key, String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(key.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing: " + e.getMessage());
        }
    }

    // Lấy IP từ request
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
