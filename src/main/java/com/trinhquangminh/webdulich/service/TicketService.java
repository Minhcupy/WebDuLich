package com.trinhquangminh.webdulich.service;

import com.trinhquangminh.webdulich.dto.request.TicketRequest;
import com.trinhquangminh.webdulich.dto.response.TicketResponse;
import com.trinhquangminh.webdulich.exception.AppException;
import com.trinhquangminh.webdulich.exception.ErrorCode;
import com.trinhquangminh.webdulich.mapper.TicketMapper;
import com.trinhquangminh.webdulich.model.Ticket;
import com.trinhquangminh.webdulich.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class TicketService {

    TicketRepository ticketRepository;
    TicketMapper ticketMapper;

    public List<TicketResponse> getAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::toResponse)
                .toList();
    }

    public TicketResponse getById(Integer id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));
        return ticketMapper.toResponse(ticket);
    }

    public TicketResponse create(TicketRequest request) {
        Ticket ticket = ticketMapper.toEntity(request);
        return ticketMapper.toResponse(ticketRepository.save(ticket));
    }

    public TicketResponse update(Integer id, TicketRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));

        ticketMapper.updateTicket(ticket, request);
        return ticketMapper.toResponse(ticketRepository.save(ticket));
    }

    public void delete(Integer id) {
        if (!ticketRepository.existsById(id)) {
            throw new AppException(ErrorCode.TICKET_NOT_FOUND);
        }
        ticketRepository.deleteById(id);
    }
}
