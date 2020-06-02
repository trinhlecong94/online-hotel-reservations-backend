package com.onlinehotelreservations.controller.promo;

import com.onlinehotelreservations.controller.hotel.DTO.HotelDTO;
import com.onlinehotelreservations.controller.promo.DTO.PromoDTO;
import com.onlinehotelreservations.entity.HotelEntity;
import com.onlinehotelreservations.entity.PromoEntity;
import com.onlinehotelreservations.service.BrandService;
import com.onlinehotelreservations.service.PromoService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class PromoMapper {

    @Autowired
    private PromoService promoService;

    public abstract PromoEntity toPromoEntity(PromoDTO promoDTO);

    public abstract PromoDTO toPromoDTO(PromoEntity promoEntity);

    public  List<PromoDTO> toPromoDT0s(List<PromoEntity> promoEntities){
        return promoEntities.parallelStream().map(this::toPromoDTO).collect(Collectors.toList());
    };


}

