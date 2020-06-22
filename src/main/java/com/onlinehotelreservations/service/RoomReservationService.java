package com.onlinehotelreservations.service;

import com.onlinehotelreservations.config.SecurityUtils;
import com.onlinehotelreservations.controller.promo.exception.PromoIsNotExistsCodeException;
import com.onlinehotelreservations.controller.roomreservation.DTO.RoomReservationRequestDTO;
import com.onlinehotelreservations.controller.roomreservation.exception.ConflictException;
import com.onlinehotelreservations.controller.roomreservation.exception.RoomReservationIsNotExistsException;
import com.onlinehotelreservations.controller.user.UserMapper;
import com.onlinehotelreservations.entity.*;
import com.onlinehotelreservations.repository.RoomReservationRepository;
import com.onlinehotelreservations.shared.enums.RoomReservationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;

    @Autowired
    private ReservationService reservationService;

    private final RoomService roomService;

    private final UserService userService;

    private final PromoService promoService;

    private final UserMapper userMapper;

    public RoomReservationEntity getRoomReservationFollowId(int id) {
        return this.roomReservationRepository.findById(id).orElseThrow(
                () -> new RoomReservationIsNotExistsException(id));
    }


    public RoomReservationEntity editRoomReservation(RoomReservationEntity editReservation) {
        if (!this.roomReservationRepository.existsById(editReservation.getId())) {
            throw new RoomReservationIsNotExistsException(editReservation.getId());
        }
        RoomReservationEntity roomReservation = this.roomReservationRepository.save(editReservation);
        if (roomReservation.getEndDate() != null) {
            this.reservationService.updateRateAfterCheckout(editReservation.getReservation());
        }
        return this.roomReservationRepository.save(roomReservation);
    }

    public void deleteRoomReservationFollowID(int id) {
        if (!this.roomReservationRepository.existsById(id)) {
            throw new RoomReservationIsNotExistsException(id);
        }
        this.roomReservationRepository.deleteById(id);
    }


    public List<RoomReservationEntity> addNewRoomReservations(int numberOfRooms, RoomReservationRequestDTO roomReservationEntity, List<String> PromoCodes) {

        List<RoomReservationEntity> listNewRoomReservation = new ArrayList<>();

        //check Start date must be before end date
        long getDiff = roomReservationEntity.getEndDate().getTime() - roomReservationEntity.getStartDate().getTime();
        if (TimeUnit.MILLISECONDS.toHours(getDiff) < 20) {
            throw new ConflictException("Conflict: Start date must be before end date");
        }

        //check code is Invalid
        Set<PromoEntity> promoFromDatabase = new HashSet<>();
        if (PromoCodes != null)
            for (String code : PromoCodes) {
                PromoEntity PromoFormDatabase = this.promoService.getPromoByCodeStillActive(code);
                if ((PromoFormDatabase == null)
                        || (PromoFormDatabase.getRoomType().getId() != roomReservationEntity.getRoomTypeId())) {
                    throw new PromoIsNotExistsCodeException(code);
                }
                promoFromDatabase.add(PromoFormDatabase);
            }

        for (int i = 0; i < numberOfRooms; i++) {
            List<RoomEntity> roomAvailableFromDatabase = this.roomService.getAllRoomAvailableByBandIdAndRoomTypeId(
                    roomReservationEntity.getStartDate(),
                    roomReservationEntity.getEndDate(),
                    roomReservationEntity.getBrandId(),
                    roomReservationEntity.getRoomTypeId()
            );

            if ((roomAvailableFromDatabase == null) || (roomAvailableFromDatabase.size() < numberOfRooms)) {
                throw new ConflictException("Conflict: There is not enough room");
            }

            RoomReservationEntity newRoomReservation = new RoomReservationEntity();
            newRoomReservation.setId(0);
            newRoomReservation.setRoom(roomAvailableFromDatabase.get(i));
            newRoomReservation.setStatus(roomReservationEntity.getStatus());
            newRoomReservation.setEmail(roomReservationEntity.getEmail());
            newRoomReservation.setFirstName(roomReservationEntity.getFirstName());
            newRoomReservation.setLastName(roomReservationEntity.getLastName());
            newRoomReservation.setCreateDate(new Date());

            ReservationEntity newReservation = new ReservationEntity();
            System.out.println(SecurityUtils.getCurrentUserEmail());
            UserEntity userEntity = this.userService.getUserByEmail(SecurityUtils.getCurrentUserEmail());

            newReservation.setUser(userEntity);
            if (promoFromDatabase != null) {
                newReservation.setPromos(promoFromDatabase);
            }

            newRoomReservation.setReservation(newReservation);

            Date endDate = roomReservationEntity.getEndDate();
            endDate.setHours(12);
            endDate.setMinutes(00);
            endDate.setSeconds(00);
            newRoomReservation.setEndDate(endDate);

            Date startDate = roomReservationEntity.getStartDate();
            startDate.setHours(14);
            startDate.setMinutes(00);
            startDate.setSeconds(00);
            newRoomReservation.setStartDate(startDate);

            RoomReservationEntity roomReservation = this.roomReservationRepository.save(newRoomReservation);

            if (roomReservation.getEndDate() != null) {
                this.reservationService.updateRateAfterCheckout(newRoomReservation.getReservation());
            }

            listNewRoomReservation.add(roomReservation);
        }
        return listNewRoomReservation;
    }

    public List<RoomReservationEntity> getAllRoomReservationByRoomId(int id) {
        return this.roomReservationRepository.getAllRoomReservationByRoomId(id);
    }

    public RoomReservationEntity reverseStatusRoomReservationFollowId(int id, String status) {
        RoomReservationEntity roomReservationFromDatabase = this.roomReservationRepository.getOne(id);
        if (roomReservationFromDatabase == null) {
            throw new RoomReservationIsNotExistsException(id);
        }
        if (roomReservationFromDatabase.getStatus() == RoomReservationStatus.CANCELLED){
            throw new ConflictException("Conflict: Status CANCELLED do not change");
        }
        else if (RoomReservationStatus.CANCELLED.toString().equalsIgnoreCase(status)) {
            roomReservationFromDatabase.setStatus(RoomReservationStatus.CANCELLED);
        } else if (RoomReservationStatus.PENDING.toString().equalsIgnoreCase(status)) {
            roomReservationFromDatabase.setStatus(RoomReservationStatus.PENDING);
        } else if (RoomReservationStatus.COMPLETED.toString().equalsIgnoreCase(status)) {
            roomReservationFromDatabase.setStatus(RoomReservationStatus.COMPLETED);
        } else {
            throw new ConflictException("Conflict: Status is invalid");
        }

        return this.roomReservationRepository.save(roomReservationFromDatabase);
    }

    public List<RoomReservationEntity> getRoomReservationByBrandId(int id) {
        return this.roomReservationRepository.getRoomReservationByBrandId(id);
    }

    public RoomReservationEntity getRoomReservationByReservation(ReservationEntity reservation) {
        return this.roomReservationRepository.getRoomReservationByReservation(reservation);
    }

    public List<RoomReservationEntity> getRoomReservationByCurrentUserId() {
        String currentUsedId = SecurityUtils.getCurrentUserEmail();
        return this.roomReservationRepository.getRoomReservationByCurrentUserId(currentUsedId);
    }
}
