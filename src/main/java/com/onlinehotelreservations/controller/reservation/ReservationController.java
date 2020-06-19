package com.onlinehotelreservations.controller.reservation;

import com.onlinehotelreservations.controller.reservation.DTO.ReservationDTO;
import com.onlinehotelreservations.service.ReservationService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/reservations")
@RestController
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @GetMapping("/{id}")
    ApiData<ReservationDTO> getReservationFollowID(@PathVariable("id") int id) {
        return new ApiData<>(this.reservationMapper.toReservationDTO(this.reservationService.getReservationFollowId(id)));
    }

    @DeleteMapping("/{id}")
    void deleteReservationFollowId(@PathVariable("id") int id) {
        this.reservationService.deleteReservationFollowId(id);
    }

}
