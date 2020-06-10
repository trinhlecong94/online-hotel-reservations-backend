package com.onlinehotelreservations.controller.hotel;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
import com.onlinehotelreservations.controller.hotel.DTO.HotelPaginationDTO;
import com.onlinehotelreservations.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelMapper hotelMapper;
    private final HotelService hotelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDTO addNewHotel(@RequestBody @Validated HotelDTO hotelDTO) {
        return this.hotelMapper.toHotelDTO(this.hotelService.addNewHotel(
                this.hotelMapper.toHotelEntity(hotelDTO)
        ));
    }

    @GetMapping
    public List<HotelDTO> getAllHotel() {
        return this.hotelMapper.toHotelDTOs(this.hotelService.getAllHotel());
    }

    @GetMapping("/{id}")
    public HotelDTO getHotelFollowID(@PathVariable("id") int id) {
        return this.hotelMapper.toHotelDTO(
                this.hotelService.getHotelFollowID(id)
        );
    }

    @PutMapping("/{id}")
    public HotelDTO editHotelFollowID(@PathVariable("id") int id, @RequestBody @Validated HotelDTO hotelDTO) {
        hotelDTO.setId(id);
        return this.hotelMapper.toHotelDTO(
                this.hotelService.editHotel(
                        this.hotelMapper.toHotelEntity(hotelDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    public HotelDTO deleteHotelFollowID(@PathVariable("id") int id) {
        return this.hotelMapper.toHotelDTO(
                this.hotelService.deleteHotelFollowID(id)
        );
    }

    @GetMapping("/search")
    public HotelPaginationDTO paginationHotels(
            @RequestParam(name = "size") int size,
            @RequestParam(name = "index") int index,
            @RequestParam(name = "valueSearch") String valueSearch,
            @RequestParam(name = "keySort") String valueSort) {

        HotelPaginationDTO res = new HotelPaginationDTO();

        res.setHotelDTOs(this.hotelMapper.toHotelDTOs(
                this.hotelService.paginationHotels(size, index, valueSearch, valueSort)
        ));

        res.setCount(this.hotelService.getAllHotelFollowValueSearch(valueSearch).size());

        return res;
    }
}
