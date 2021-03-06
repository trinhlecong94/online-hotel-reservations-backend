package com.onlinehotelreservations.controller.roomtype;

import com.onlinehotelreservations.controller.roomtype.DTO.RoomTypeDTO;
import com.onlinehotelreservations.service.RoomTypeService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/room-type")
public class RoomTypeController {

    private final RoomTypeMapper roomTypeMapper;

    private final RoomTypeService roomTypeService;

    @GetMapping("/{id}")
    public ApiData<RoomTypeDTO> getRoomTypeFollowId(@PathVariable("id") int id) {
        return new ApiData<>(this.roomTypeMapper.toRoomTypeDTO(this.roomTypeService.getRoomTypeFollowId(id)));
    }

    @GetMapping
    public ApiData<List<RoomTypeDTO>> getAllRoomType() {
        return new ApiData<>(this.roomTypeMapper.toRoomTypeDTOs(this.roomTypeService.getAllRoomType()));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ApiData<RoomTypeDTO> saveRoomType(@RequestBody @Validated RoomTypeDTO roomTypeDTO) {
        return new ApiData<>(this.roomTypeMapper.toRoomTypeDTO(this.roomTypeService.addNewRoomType(this.roomTypeMapper.toRoomTypeEntity(roomTypeDTO))));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping()
    public ApiData<RoomTypeDTO> editRoomType(@RequestBody @Validated RoomTypeDTO roomTypeDTO) {
        return new ApiData<>(this.roomTypeMapper.toRoomTypeDTO(this.roomTypeService
                .editRoomType(this.roomTypeMapper.toRoomTypeEntity(roomTypeDTO))));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteRoomType(@PathVariable("id") int id) {
        this.roomTypeService.deleteRoomTypeFollowId(id);
    }

    @GetMapping("/search")
    public ApiData<List<RoomTypeDTO>> searchRooms(@RequestParam(name = "valueSearch") String valueSearch) {
        return new ApiData<>(this.roomTypeMapper.toRoomTypeDTOs(this.roomTypeService.searchRoomTypes(valueSearch)));
    }
}
