package com.onlinehotelreservations.controller.reservation;

import com.onlinehotelreservations.repository.ReservationRepository;
import com.onlinehotelreservations.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/reservations")
@RestController
public class ReservationController {
    private final ReservationService reservationService;


}
