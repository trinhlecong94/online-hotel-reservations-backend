package com.onlinehotelreservations.controller.room.DTO;


import com.onlinehotelreservations.entity.RoomTypeEntity;
import lombok.Data;

@Data
public class RoomStatusDTO {
    private int totalRoom;

    private int totalRoomAvailable;

    private RoomTypeEntity roomType;
}
