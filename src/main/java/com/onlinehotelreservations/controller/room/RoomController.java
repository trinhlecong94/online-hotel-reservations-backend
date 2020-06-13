package com.onlinehotelreservations.controller.room;

import com.onlinehotelreservations.controller.room.DTO.RoomDTO;
import com.onlinehotelreservations.controller.room.DTO.RoomStatusDTO;
import com.onlinehotelreservations.service.RoomService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @GetMapping("/status/{valueSearch}")
    public ApiData<List<RoomStatusDTO>> getAllRoomStatus(@PathVariable(name = "valueSearch") String valueSearch) {
        return new ApiData<>(this.roomMapper.RoomStatusDTOs(this.roomService.searchRooms(valueSearch)));
    }

    @GetMapping("/search/{valueSearch}")
    public ApiData<List<RoomDTO>> getAllRoomSearchByRoomNameAndBrandNameAndRoomTypeNameAndAddress(@PathVariable(name = "valueSearch") String valueSearch) {
        return new ApiData<>(this.roomMapper.toRoomDT0s(this.roomService.searchRooms(valueSearch)));
    }

    @GetMapping("/brand/{brandID}")
    public ApiData<List<RoomDTO>> getAllRoomByBrand(@PathVariable("brandID") int brandID) {
        return new ApiData<>(this.roomMapper.toRoomDT0s(this.roomService.getAllRoomByBrand(brandID)));
    }

    @GetMapping("/{id}")
    public ApiData<RoomDTO> getRoomFollowID(@PathVariable("id") int id) {
        return new ApiData<>(this.roomMapper.toRoomDTO(this.roomService.getRoomFollowID(id)));
    }

    @PutMapping
    public ApiData<RoomDTO> editRoom(@RequestBody RoomDTO editRoom) {
        return new ApiData<>(this.roomMapper.toRoomDTO(this.roomService.editRoom(
                this.roomMapper.toRoomEntity(editRoom))));
    }

    @PostMapping
    public ApiData<RoomDTO> addNewRoom(@RequestBody RoomDTO newRoom) {
        return new ApiData<>(this.roomMapper.toRoomDTO(this.roomService.addNewRoom(
                this.roomMapper.toRoomEntity(newRoom))));
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") int id) {
        this.roomService.deleteRoom(id);
    }
}
