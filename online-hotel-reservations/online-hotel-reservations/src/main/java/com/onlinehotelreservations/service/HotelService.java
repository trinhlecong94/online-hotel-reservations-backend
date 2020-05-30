package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.hotel.exception.HotelNotFoundException;
import com.onlinehotelreservations.controller.hotel.exception.NameHotelIsExistException;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
        if(this.hotelRepository.findByName(hotelEntity.getName()).isPresent()) {
            throw new NameHotelIsExistException(hotelEntity.getName());
        }
        return this.hotelRepository.save(hotelEntity);
    }

    public List<HotelEntity> getAllHotel() {

        log.info(this.hotelRepository.findAll().get(0).getBrandEntities().size() + "");
        return this.hotelRepository.findAll();
    }
}
