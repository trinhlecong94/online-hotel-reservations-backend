package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.room.exception.RoomIsExistsException;
import com.onlinehotelreservations.controller.room.exception.RoomIsNotExistsException;
import com.onlinehotelreservations.entity.RoomEntity;
import com.onlinehotelreservations.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeService roomTypeService;

    public List<RoomEntity> getAllRoom() {
        return this.roomRepository.findAll();
    }

    public RoomEntity getRoomFollowID(int id) {
        return this.roomRepository.findById(id).orElseThrow(() ->
                new RoomIsNotExistsException(id));
    }

    public RoomEntity editRoom(RoomEntity editRoom) {
        if (!this.roomRepository.existsById(editRoom.getId())){
            throw new RoomIsNotExistsException(editRoom.getId());
        }
        return this.roomRepository.save(editRoom);
    }

    public RoomEntity addNewRoom(RoomEntity newRoom) {
        if (this.roomRepository.existsById(newRoom.getId())){
            throw new RoomIsExistsException(newRoom.getId());
        }
        return this.roomRepository.save(newRoom);
    }

    public void deleteRoom(int id) {
        if (!this.roomRepository.existsById(id)){
            throw new RoomIsNotExistsException(id);
        }
        this.roomRepository.deleteById(id);
    }

    //true : co the dat cho
    //false: ko the dat cho
    public boolean getRoomStatus(int id) {
        if (!this.roomRepository.existsById(id)){
            throw new RoomIsNotExistsException(id);
        }
        RoomEntity roomFromDatabase = this.roomRepository.getRoomAvailable(id);
        if (roomFromDatabase==null){
            return false;
        }
        else return true;
    }

    public List<RoomEntity> getAllRoomByBrand(int brandID) {
        return this.roomRepository.getAllRoomByBrand(brandID);
    }



    public List<RoomEntity> searchRooms(String valueSearch) {
        return this.roomRepository.searchRooms(valueSearch);
    }
}
