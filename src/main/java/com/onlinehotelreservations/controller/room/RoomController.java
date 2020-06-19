package com.onlinehotelreservations.controller.room;

import com.onlinehotelreservations.controller.room.DTO.RoomRequestDTO;
import com.onlinehotelreservations.controller.room.DTO.RoomResponseDTO;
import com.onlinehotelreservations.controller.room.DTO.RoomStatusDTO;
import com.onlinehotelreservations.service.RoomService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    private final RoomRequestMapper roomRequestMapper;

    private final RoomResponseMapper roomResponseMapper;

    private final RoomStatusMapper roomStatusMapper;

    @GetMapping("/status/")
    public ApiData<List<RoomStatusDTO>> getAllRoomStatus(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("brandId") int brandId) {
        return new ApiData<>(this.roomStatusMapper.RoomStatusDTOs(startDate,endDate,brandId));
    }

    @GetMapping("room-reservation/brand/{brandID}")
    public ApiData<List<RoomResponseDTO>> getAllRoom_RoomReservationByBrand(@PathVariable("brandID") int brandID) {
        return new ApiData<>(this.roomResponseMapper.toRoomResponseDTOs(this.roomService.getAllRoomByBrand(brandID)));
    }

    @GetMapping("/search/{valueSearch}")
    public ApiData<List<RoomResponseDTO>> getAllRoomSearchByRoomNameAndBrandNameAndRoomTypeNameAndAddress(@PathVariable(name = "valueSearch") String valueSearch) {
        return new ApiData<>(this.roomResponseMapper.toRoomResponseDTOs(this.roomService.searchRooms(valueSearch)));
    }

    @GetMapping("/brand/{brandID}")
    public ApiData<List<RoomResponseDTO>> getAllRoomByBrand(@PathVariable("brandID") int brandID) {
        return new ApiData<>(this.roomResponseMapper.toRoomResponseDTOs(this.roomService.getAllRoomByBrand(brandID)));
    }

    @GetMapping("/{id}")
    public ApiData<RoomResponseDTO> getRoomFollowID(@PathVariable("id") int id) {
        return new ApiData<>(this.roomResponseMapper.toRoomResponseDTO(this.roomService.getRoomFollowID(id)));
    }

    @PutMapping
    public ApiData<RoomResponseDTO> editRoom(@RequestBody RoomRequestDTO editRoom) {
        return new ApiData<>(this.roomResponseMapper.toRoomResponseDTO(this.roomService.editRoom(
                this.roomRequestMapper.toRoomEntity(editRoom))));
    }

    @PostMapping
    public ApiData<RoomResponseDTO> addNewRoom(@RequestBody RoomRequestDTO newRoom) {
        return new ApiData<>(this.roomResponseMapper.toRoomResponseDTO(this.roomService.addNewRoom(
                this.roomRequestMapper.toRoomEntity(newRoom))));
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") int id) {
        this.roomService.deleteRoom(id);
    }
}
