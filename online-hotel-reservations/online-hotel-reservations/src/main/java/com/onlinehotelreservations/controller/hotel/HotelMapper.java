package com.onlinehotelreservations.controller.hotel;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class HotelMapper {

    @Autowired
    private BrandService brandService;

    public abstract HotelEntity toHotelEntities(HotelDTO hotelDTO);

    @Mapping(source = ".", target = "brands", qualifiedByName = "mapToBrandDTOs")
    public abstract HotelDTO toHotelDTO(HotelEntity hotelEntity);

    public List<HotelDTO> toHotelDTOs(List<HotelEntity> hotelEntities) {
        return hotelEntities.parallelStream().map(this::toHotelDTO).collect(Collectors.toList());
    }

    public List<HotelDTO.Brand> mapToBrandDTOs(final HotelEntity hotelEntity) {
        List<HotelDTO.Brand> brands = new ArrayList<>();
        if (hotelEntity.getBrandEntities() != null && !hotelEntity.getBrandEntities().isEmpty()) {
            hotelEntity.getBrandEntities().stream().forEach(item -> {
                HotelDTO.Brand brand = new HotelDTO.Brand();
                brand.setId(item.getId());
                brand.setName(item.getName());
                brands.add(brand);
            });
        }
        return brands;
    }
}
