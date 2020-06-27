package com.onlinehotelreservations.controller.hotel;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class HotelMapper {

    @Autowired
    private BrandService brandService;

    public abstract HotelEntity toHotelEntity(HotelDTO hotelDTO);

    @Mapping(source = ".", target = "brands", qualifiedByName = "mapToBrandDTOs")
    public abstract HotelDTO toHotelDTO(HotelEntity hotelEntity);

    public List<HotelDTO> toHotelDTOs(List<HotelEntity> hotelEntities) {
        return hotelEntities.parallelStream().map(this::toHotelDTO).collect(Collectors.toList());
    }

    public List<HotelDTO.Brand> mapToBrandDTOs(final HotelEntity hotelEntity) {
        List<HotelDTO.Brand> brands = new ArrayList<>();
        if (hotelEntity.getBrandEntities() != null && !hotelEntity.getBrandEntities().isEmpty()) {
            hotelEntity.getBrandEntities().stream().forEach(item -> {
                HotelDTO.Brand brand = HotelDTO.Brand.builder()
                        .address(item.getAddress())
                        .floor(item.getFloor())
                        .id(item.getId())
                        .imgLink(item.getImgLink())
                        .name(item.getName())
                        .desciption(item.getDesciption())
                        .build();

                brands.add(brand);
            });
        }
        return brands;
    }
}
