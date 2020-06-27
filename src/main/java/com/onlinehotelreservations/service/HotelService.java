package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.hotel.exception.HotelNotFoundException;
import com.onlinehotelreservations.controller.hotel.exception.NameHotelIsExistException;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.hotelRepository.save(hotelEntity);
    }

    public List<HotelEntity> getAllHotel() {
        return this.hotelRepository.findAll();
    }

    public HotelEntity editHotel(HotelEntity hotelEntity) {
        HotelEntity hotelEntityFromDatabase = this.getHotelFollowID(hotelEntity.getId());
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

    public List<HotelEntity> paginationHotels(int size, int index, String valueSearch, String keySort) {
        Pageable sortedPage = getPageableSort(index, size, keySort);
        return hotelRepository.paginationHotelsFollowValueSearch(valueSearch, sortedPage);
    }

    public List<HotelEntity> getAllHotelFollowValueSearch(String valueSearch) {
        return this.hotelRepository.findAllHotelFollowValueSearch(valueSearch);
    }

    public Pageable getPageableSort(int index, int size, String keySort) {
        if (keySort.equals("") || keySort == null) {
            keySort = "id";
        }
        return PageRequest.of(index, size, Sort.by(keySort));
    }
}
