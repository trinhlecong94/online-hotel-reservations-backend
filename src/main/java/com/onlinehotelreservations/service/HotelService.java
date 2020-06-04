package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
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
    private final BrandService brandService;

    public HotelEntity getHotelFollowID(int id) {
        return this.hotelRepository.findById(id).orElseThrow(
                () -> new HotelNotFoundException(id)
        );
    }

    public HotelEntity addNewHotel(HotelEntity hotelEntity) {
        if (this.hotelRepository.findByName(hotelEntity.getName()).isPresent()) {
            throw new NameHotelIsExistException(hotelEntity.getName());
        }
        return this.hotelRepository.save(hotelEntity);
    }

    public List<HotelEntity> getAllHotel() {
        return this.hotelRepository.findAll();
    }

    public HotelEntity editHotel(HotelEntity hotelEntity) {
        HotelEntity hotelEntityFromDatabase = this.getHotelFollowID(hotelEntity.getId());

        if (this.hotelRepository.findByName(hotelEntity.getName()).isPresent()) {
            throw new NameHotelIsExistException(hotelEntity.getName());
        }

        hotelEntityFromDatabase.setName(hotelEntity.getName());
        return this.hotelRepository.save(hotelEntity);
    }

    public HotelEntity deleteHotelFollowID(int id) {
        HotelEntity hotelEntity = this.getHotelFollowID(id);

        hotelEntity.getBrandEntities().forEach(item -> {
            this.brandService.deleteBrandFollowID(item.getId());
        });

        this.hotelRepository.delete(hotelEntity);

        return hotelEntity;
    }

    public List<HotelEntity> searchHotelFollowKeyword(String keyword) {
        List<HotelEntity> hotelEntities = this.hotelRepository.findHotelsByKeyword(keyword);
        return hotelEntities;
    }
}