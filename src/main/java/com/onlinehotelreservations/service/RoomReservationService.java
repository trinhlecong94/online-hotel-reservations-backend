package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.promo.exception.PromoNotFoundException;
import com.onlinehotelreservations.controller.roomreservation.DTO.RoomsReservationDTO;
import com.onlinehotelreservations.controller.roomreservation.exception.ConflictException;
import com.onlinehotelreservations.controller.roomreservation.exception.RoomReservationIsExistsException;
import com.onlinehotelreservations.controller.roomreservation.exception.RoomReservationIsNotExistsException;
import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.controller.user.UserMapper;
import com.onlinehotelreservations.entity.*;
import com.onlinehotelreservations.repository.RoomReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;

    private final ReservationService reservationService;

    private final RoomService roomService;

    private final UserMapper userMapper;

    private final UserService userService;

    private final PromoService promoService;

    public RoomReservationEntity getRoomReservationFollowId(int id) {
        return this.roomReservationRepository.findById(id).orElseThrow(
                () -> new RoomReservationIsNotExistsException(id));
    }

    public RoomReservationEntity addNewRoomReservation(RoomReservationEntity newRoomReservation, UserEntity userBooking) {

        ReservationEntity reservation = new ReservationEntity();
        reservation.setUser(userBooking);
        this.reservationService.addNewReservation(reservation);

        if (roomService.getRoomStatus(newRoomReservation.getRoom().getId())) {
            throw new RoomReservationIsExistsException(newRoomReservation.getId());
        }
        RoomReservationEntity roomReservation = this.roomReservationRepository.save(newRoomReservation);

        if (roomReservation.getEndDate() != null) {
            updateRateAfterCheckout(newRoomReservation);
        }

        return roomReservation;
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
            double totalBeforeTax = (roomReservation.getEndDate().getTime() - roomReservation.getStartDate().getTime() + 1) * roomReservation.getRoom().getRoomType().getPrice();
            reservationFromDatabase.setTotalBeforeTax(totalBeforeTax);
            reservationFromDatabase.setTax(reservationFromDatabase.getTotalBeforeTax() * 10 / 100);
            this.reservationService.editReservation(reservationFromDatabase);
        }
    }

    public List<RoomReservationEntity> addNewRoomReservations(int numberOfRooms, RoomsReservationDTO roomReservationEntity, UserEntity userBooking, List<String> PromoCodes) {
        List<RoomReservationEntity> roomReservationEntities = new ArrayList<>();

        List<PromoEntity> promoEntities = new ArrayList<>();
        for (String code : PromoCodes) {
            PromoEntity PromoFormDatabase = this.promoService.getPromoByCodeStillActive(code);
            if (PromoFormDatabase == null) {
                throw new PromoNotFoundException(0);
            }
            promoEntities.add(PromoFormDatabase);
        }


        for (int i = 0; i < numberOfRooms; i++) {

            ReservationEntity reservation = new ReservationEntity();
            UserEntity userEntity = this.userService.getUserFollowId(userBooking.getId());
            reservation.setUser(userEntity);
            if (promoEntities != null) {
                reservation.setPromos(promoEntities);
            }
            this.reservationService.addNewReservation(reservation);

            List<RoomEntity> roomEntities = this.roomService.getAllRoomAvailableByBandIdAndRoomTypeId(
                    roomReservationEntity.getStartDate(),
                    roomReservationEntity.getEndDate(),
                    roomReservationEntity.getBrandId(),
                    roomReservationEntity.getRoomTypeId()
            );

            if ((roomEntities == null) || (roomEntities.size() < numberOfRooms)) {
                throw new ConflictException("ConflictException");
            }

            if (roomService.getRoomStatus(roomEntities.get(i).getId())) {
                throw new RoomReservationIsExistsException(roomEntities.get(i).getId());
            }

            RoomReservationEntity newRoomReservation = new RoomReservationEntity();
            newRoomReservation.setId(0);
            newRoomReservation.setEndDate(roomReservationEntity.getEndDate());
            newRoomReservation.setReservation(reservation);
            newRoomReservation.setRoom(roomEntities.get(i));
            newRoomReservation.setStatus(roomReservationEntity.getStatus());
            List<UserEntity> userEntities = new ArrayList<>();
            for (UserDTO user : roomReservationEntity.getUsers()) {
                userEntities.add(this.userMapper.toUserEntity(user));
            }
            newRoomReservation.setUsers(userEntities);
            newRoomReservation.setStartDate(roomReservationEntity.getStartDate());
            RoomReservationEntity roomReservation = this.roomReservationRepository.save(newRoomReservation);
            if (roomReservation.getEndDate() != null) {
                updateRateAfterCheckout(newRoomReservation);
            }
            roomReservationEntities.add(roomReservation);
        }
        return roomReservationEntities;
    }
}
