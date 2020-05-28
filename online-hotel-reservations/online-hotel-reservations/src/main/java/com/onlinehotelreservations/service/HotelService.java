package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.hotel.exception.HotelNotFoundException;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelEntity getHotelFollowID(int id) {
        return this.hotelRepository.findById(id).orElseThrow(
                () -> new HotelNotFoundException(id)
        );
    }

    public HotelEntity addNewHotel(HotelEntity hotelEntity) {
        return this.hotelRepository.save(hotelEntity);
    }
}
