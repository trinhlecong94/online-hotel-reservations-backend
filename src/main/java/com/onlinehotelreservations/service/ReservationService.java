package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.reservation.exception.ReservationIsExistsException;
import com.onlinehotelreservations.controller.reservation.exception.ReservationIsNotExistsException;
import com.onlinehotelreservations.entity.ReservationEntity;
import com.onlinehotelreservations.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationEntity getReservationFollowId(int id) {
        return this.reservationRepository.findById(id).orElseThrow(() ->
                new ReservationIsNotExistsException(id));
    }

    public ReservationEntity editReservation(ReservationEntity updateReservation) {
        if (!this.reservationRepository.existsById(updateReservation.getId())) {
            throw new ReservationIsNotExistsException(updateReservation.getId());
        }
        return this.reservationRepository.save(updateReservation);
    }

    public ReservationEntity addNewReservation(ReservationEntity newReservation) {
        if (this.reservationRepository.existsById(newReservation.getId())) {
            throw new ReservationIsExistsException(newReservation.getId());
        }
        return this.reservationRepository.save(newReservation);
    }

    public void deleteReservationFollowId(int id) {
        if (!this.reservationRepository.existsById(id)) {
            throw new ReservationIsNotExistsException(id);
        }
        this.reservationRepository.existsById(id);
    }
}
