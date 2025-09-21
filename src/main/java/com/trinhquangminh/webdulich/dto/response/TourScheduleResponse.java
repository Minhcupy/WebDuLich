package com.trinhquangminh.webdulich.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class TourScheduleResponse {
    private Integer id;
    private Integer tourId;
    private Date dayCome;
    private Date dayGo;
    private String activity;
    private String meal;
    private String accommodation;
}
