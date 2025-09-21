package com.trinhquangminh.webdulich.controller;

import com.trinhquangminh.webdulich.dto.request.TourScheduleRequest;
import com.trinhquangminh.webdulich.dto.response.TourScheduleResponse;
import com.trinhquangminh.webdulich.service.TourScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tour-schedules")
@RequiredArgsConstructor
public class TourScheduleController {

    private final TourScheduleService tourScheduleService;

    @GetMapping
    public Page<TourScheduleResponse> getAll(Pageable pageable) {
        return tourScheduleService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public TourScheduleResponse getById(@PathVariable Integer id) {
        return tourScheduleService.getById(id);
    }

    @PostMapping
    public TourScheduleResponse create(@RequestBody TourScheduleRequest request) {
        return tourScheduleService.create(request);
    }

    @PutMapping("/{id}")
    public TourScheduleResponse update(@PathVariable Integer id,
                                       @RequestBody TourScheduleRequest request) {
        return tourScheduleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        tourScheduleService.delete(id);
    }
}
