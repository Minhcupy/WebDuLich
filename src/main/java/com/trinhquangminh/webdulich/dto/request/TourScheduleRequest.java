package com.trinhquangminh.webdulich.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class TourScheduleRequest {
    private Integer tourId;
    private Date dayCome;
    private Date dayGo;
    private String activity;
    private String meal;
    private String accommodation;
}
