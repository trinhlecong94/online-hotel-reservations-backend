package com.onlinehotelreservations.controller.promo;

import com.onlinehotelreservations.controller.brand.DTO.BrandDTO;
import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.service.HotelService;
import com.onlinehotelreservations.service.RoomTypeService;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public abstract class PromoMapper {
    @Autowired
    private RoomTypeService roomTypeService;

    @Mapping(source = "hotelEntity.id", target = "hotel.id")
    @Mapping(source = "hotelEntity.name", target = "hotel.name")
    public abstract BrandDTO toBrandDTO(BrandEntity brandEntity);

    @Mapping(source = ".", target = "hotelEntity", qualifiedByName = "mapToHotelEntity")
    public abstract BrandEntity toBrandEntity(BrandDTO brandDTO);

    public List<BrandDTO> toBrandDTOs(List<BrandEntity> brandEntities) {
        return brandEntities.parallelStream().map(this::toBrandDTO).collect(Collectors.toList());
    }

    public HotelEntity mapToHotelEntity(final BrandDTO brandDTO) {
        return this.hotelService.getHotelFollowID(brandDTO.getHotel().getId());
    }
}

