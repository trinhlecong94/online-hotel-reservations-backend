package com.onlinehotelreservations.controller.roomreservation;

import com.onlinehotelreservations.controller.roomreservation.DTO.RoomReservationDTO;
import com.onlinehotelreservations.controller.roomreservation.DTO.RoomReservationRequestDTO;
import com.onlinehotelreservations.controller.user.UserMapper;
import com.onlinehotelreservations.service.RoomReservationService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/room-reservations")
@RequiredArgsConstructor
public class RoomReservationController {

    private final RoomReservationService roomReservationService;

    private final RoomReservationMapper roomReservationMapper;

    private final UserMapper userMapper;

    @GetMapping("/{id}")
    ApiData<RoomReservationDTO> getRoomReservationFollowID(@PathVariable("id") int id) {
        return new ApiData<>(this.roomReservationMapper.toRoomReservationDTO(
                this.roomReservationService.getRoomReservationFollowId(id)));
    }


    @PostMapping
    ApiData<List<RoomReservationDTO>> addNewRoomsReservation(
            @RequestParam(value = "numberOfRooms") int numberOfRooms,
            @RequestParam(value = "listPromoCode", required = false) List<String> listPromoCode,
            @RequestBody RoomReservationRequestDTO roomsReservationDTO) {
        return new ApiData<>(this.roomReservationMapper.toRoomReservationDTOs(
                this.roomReservationService.addNewRoomReservations(
                        numberOfRooms,
                        roomsReservationDTO,
                        this.userMapper.toUserEntity(roomsReservationDTO.getUsersBooking()),
                        listPromoCode)));
    }

    @PutMapping
    ApiData<RoomReservationDTO> editRoomReservation(@RequestBody RoomReservationDTO roomReservationDTO) {
        return new ApiData<>(this.roomReservationMapper.toRoomReservationDTO(
                this.roomReservationService.editRoomReservation(
                        this.roomReservationMapper.toRoomReservationEntity(roomReservationDTO))));
    }

    @DeleteMapping("/{id}")
    void deleteRoomReservationFollowID(@PathVariable("id") int id) {
        this.roomReservationService.deleteRoomReservationFollowID(id);
    }

}
