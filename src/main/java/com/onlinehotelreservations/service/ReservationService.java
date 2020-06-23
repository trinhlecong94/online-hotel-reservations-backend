package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.reservation.exception.ReservationIsExistsException;
import com.onlinehotelreservations.controller.reservation.exception.ReservationIsNotExistsException;
import com.onlinehotelreservations.entity.ReservationEntity;
import com.onlinehotelreservations.entity.RoomReservationEntity;
import com.onlinehotelreservations.repository.ReservationRepository;
import com.onlinehotelreservations.shared.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    private RoomReservationService roomReservationService;

    public ReservationEntity getReservationFollowId(int id) {
        return this.reservationRepository.findById(id).orElseThrow(() ->
                new ReservationIsNotExistsException(id));
    }

    public ReservationEntity reverseStatusReservationFollowId(int id) {

        ReservationEntity reservationFromDatabase = this.reservationRepository.getOne(id);

        if (reservationFromDatabase == null) {
            throw new ReservationIsNotExistsException(id);
        }
        reservationFromDatabase.setStatus(reservationFromDatabase.getStatus() == ReservationStatus.UNPAID ? ReservationStatus.PAID : ReservationStatus.UNPAID);

        return this.reservationRepository.save(reservationFromDatabase);
    }

    public List<ReservationEntity> getReservationUserId(int id) {
        return this.reservationRepository.getReservationUserId(id);
    }

    public ReservationEntity updateRateAfterCheckout(ReservationEntity reservation) {

        if (!this.reservationRepository.existsById(reservation.getId())) {
            throw new ReservationIsExistsException(reservation.getId());
        }

        RoomReservationEntity roomReservation = roomReservationService.getRoomReservationByReservation(reservation);

        long getDiff = roomReservation.getEndDate().getTime() - roomReservation.getStartDate().getTime();
        long dateBooking = TimeUnit.MILLISECONDS.toDays(getDiff) + 1;

        double totalAfterTax = dateBooking * roomReservation.getRoom().getRoomType().getPrice();
        reservation.setTotalAfterTax(totalAfterTax);
        reservation.setTotalBeforeTax(totalAfterTax + totalAfterTax * reservation.getTaxPercent() / 100);

        return this.reservationRepository.save(reservation);
    }
}
