package com.onlinehotelreservations.controller.hotel;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class HotelMapper {

    @Autowired
    private BrandService brandService;

    @Mapping(source = ".", target = "brandEntities", qualifiedByName = "mapToHotelEntities")
    public abstract HotelEntity toHotelEntities(HotelDTO hotelDTO);

    @Mapping(source = ".", target = "brands", qualifiedByName = "mapToBrandsDTO")
    public abstract HotelDTO toHotelDTO(HotelEntity hotelEntity);

    public Set<BrandEntity> mapToHotelEntities(final HotelDTO hotelDTO) {
        Set<BrandEntity> brandEntities = new HashSet<>();
        if(hotelDTO.getBrands() != null && !hotelDTO.getBrands().isEmpty() ) {
            hotelDTO.getBrands().stream().forEach(item -> {
                BrandEntity brandEntity = this.brandService.getBrandFollowID(item.getId());
                brandEntities.add(brandEntity);
            });
        }
        return brandEntities;
    }

    public Set<HotelDTO.Brand> mapToBrandsDTO(final HotelEntity hotelEntity) {
        Set<HotelDTO.Brand> brands = new HashSet<>();

        hotelEntity.getBrandEntities().stream().forEach(item -> {
            HotelDTO.Brand brand = new HotelDTO.Brand();
            brand.setId(item.getId());
            brand.setName(item.getName());
            brands.add(brand);
        });

        return brands;
    }
}
