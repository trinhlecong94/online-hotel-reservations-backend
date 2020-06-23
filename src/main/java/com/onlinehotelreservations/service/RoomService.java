package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.room.exception.RoomIsExistsException;
import com.onlinehotelreservations.controller.room.exception.RoomIsNotExistsException;
import com.onlinehotelreservations.entity.RoomEntity;
import com.onlinehotelreservations.entity.RoomReservationEntity;
import com.onlinehotelreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomEntity getRoomFollowID(int id) {
        return this.roomRepository.findById(id).orElseThrow(() ->
                new RoomIsNotExistsException(id));
    }

    public RoomEntity editRoom(RoomEntity editRoom) {
        if (!this.roomRepository.existsById(editRoom.getId())) {
            throw new RoomIsNotExistsException(editRoom.getId());
        }
        return this.roomRepository.save(editRoom);
    }

    public RoomEntity addNewRoom(RoomEntity newRoom) {
        if (this.roomRepository.existsById(newRoom.getId())) {
            throw new RoomIsExistsException(newRoom.getId());
        }
        return this.roomRepository.save(newRoom);
    }

    public void deleteRoom(int id) {
        if (!this.roomRepository.existsById(id)) {
            throw new RoomIsNotExistsException(id);
        }
        this.roomRepository.deleteById(id);
    }

    public List<RoomEntity> getAllRoomByBrand(int brandID) {
        return this.roomRepository.getAllRoomByBrand(brandID);
    }

    public List<RoomEntity> searchRooms(String valueSearch) {
        return this.roomRepository.searchRooms(valueSearch);
    }

    public List<RoomEntity> getAllRoomAvailableByBandIdAndRoomTypeId(Date startDate, Date endDate, int BrandId, int RoomTypeId) {
        startDate.setHours(11);startDate.setMinutes(00);startDate.setSeconds(00);
        endDate.setHours(15);endDate.setMinutes(00);endDate.setSeconds(00);

        List<RoomEntity> roomEntity = this.roomRepository.getAllRoomByBrandIdandRoomTypeId(BrandId, RoomTypeId);
        List<RoomEntity> roomEntityList = new ArrayList<>();

        for (RoomEntity room : roomEntity) {
            boolean temp = true;
            for (RoomReservationEntity roomReservationEntity : room.getRoomReservation()) {
                if (isDateBetweenTwoDates(roomReservationEntity.getStartDate(), roomReservationEntity.getEndDate(), startDate,endDate)){
                    temp = false;
                    break;
                }
            }
            if (temp) {
                roomEntityList.add(room);
            }
        }
        return roomEntityList;
    }

    boolean isDateBetweenTwoDates(Date startDate, Date endDate, Date checkStartDate,Date checkEndDate) {
        return startDate.before(checkEndDate) && endDate.after(checkStartDate);
    }


}
