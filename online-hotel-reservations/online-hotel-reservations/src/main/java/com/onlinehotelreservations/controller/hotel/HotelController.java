package com.onlinehotelreservations.controller.hotel;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
import com.onlinehotelreservations.service.HotelService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelMapper hotelMapper;
    private final HotelService hotelService;

    @PostMapping
    public HotelDTO addNewHotel(@RequestBody HotelDTO hotelDTO) {
        return this.hotelMapper.toHotelDTO(this.hotelService.addNewHotel(
                this.hotelMapper.toHotelEntities(hotelDTO)
        ));
    }

}
