package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.roomreservation.exception.RoomReservationIsNotExistsException;
import com.onlinehotelreservations.entity.ReservationEntity;
import com.onlinehotelreservations.entity.RoomReservationEntity;
import com.onlinehotelreservations.repository.RoomReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;

    private final ReservationService reservationService;

    private final RoomService roomService;

    public RoomReservationEntity getRoomReservationFollowId(int id) {
        return this.roomReservationRepository.findById(id).orElseThrow(
                () -> new RoomReservationIsNotExistsException(id));
    }

    public RoomReservationEntity addNewRoomReservation(RoomReservationEntity newReservation) {
        if (roomService.getRoomStatus(newReservation.getRoom().getId())){

        }
        RoomReservationEntity roomReservation = this.roomReservationRepository.save(newReservation);
        if (roomReservation.getEndDate() != null) {
            updateRateAfterCheckout(newReservation);
        }
        return this.roomReservationRepository.save(newReservation);
    }

    public RoomReservationEntity editRoomReservation(RoomReservationEntity editReservation) {
        if (!this.roomReservationRepository.existsById(editReservation.getId())) {
            throw new RoomReservationIsNotExistsException(editReservation.getId());
        }
        RoomReservationEntity roomReservation = this.roomReservationRepository.save(editReservation);
        if (roomReservation.getEndDate() != null) {
            updateRateAfterCheckout(editReservation);
        }
        return this.roomReservationRepository.save(roomReservation);
    }

    public void deleteRoomReservationFollowID(int id) {
        if (!this.roomReservationRepository.existsById(id)) {
            throw new RoomReservationIsNotExistsException(id);
        }
        this.roomReservationRepository.deleteById(id);
    }

    public void updateRateAfterCheckout(RoomReservationEntity roomReservation) {
        if (roomReservation.getEndDate() != null) {
            ReservationEntity reservationFromDatabase = this.reservationService.getReservationFollowId(roomReservation.getReservation().getId());
            double totalBeforeTax = (roomReservation.getEndDate().getTime() - roomReservation.getStartDate().getTime()+1) * roomReservation.getRoom().getRoomType().getPrice();
            reservationFromDatabase.setTotalBeforeTax(totalBeforeTax);
            reservationFromDatabase.setTax(reservationFromDatabase.getTotalBeforeTax() * 10 / 100);
            this.reservationService.editReservation(reservationFromDatabase);
        }
    }
}
