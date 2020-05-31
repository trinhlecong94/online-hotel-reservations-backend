package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.roomtype.exception.RomeTypeIsExistsException;
import com.onlinehotelreservations.controller.roomtype.exception.RomeTypeIsNotExistsException;
import com.onlinehotelreservations.controller.roomtype.exception.RomeTypeNotFoundException;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeEntity getRoomTypeFollowId(int id) {
        return this.roomTypeRepository.findById(id).orElseThrow(() ->
                new RomeTypeNotFoundException(id));
    }

    public List<RoomTypeEntity> getAllRoomType() {
        return this.roomTypeRepository.findAll();
    }

    public RoomTypeEntity editRoomType(RoomTypeEntity editroomType) {

        if (!this.roomTypeRepository.existsById(editroomType.getId())) {
            throw new RomeTypeIsNotExistsException(editroomType.getId());
        }

        this.roomTypeRepository.save(editroomType);
        return this.roomTypeRepository.findById(editroomType.getId()).get();
    }


    public void deleteRoomTypeFollowId(int id) {
        if (!this.roomTypeRepository.existsById(id)) {
            throw new RomeTypeIsNotExistsException(id);
        }
        this.roomTypeRepository.deleteById(id);
    }

    public RoomTypeEntity addNewRoomType(RoomTypeEntity newRoomType) {
        if(this.roomTypeRepository.existsById(newRoomType.getId())){
            throw new RomeTypeIsExistsException(newRoomType.getId());
        }
        return this.roomTypeRepository.save(newRoomType);
    }
}
