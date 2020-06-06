package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.promo.exception.PromoIsExistsException;
import com.onlinehotelreservations.controller.promo.exception.PromoNotFoundException;
import com.onlinehotelreservations.entity.PromoEntity;
import com.onlinehotelreservations.entity.RoomTypeEntity;
import com.onlinehotelreservations.repository.PromoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class PromoService {
    private final PromoRepository promoRepository;
    private final RoomTypeService roomTypeService;

    public PromoEntity addNewPromo(PromoEntity newPromo) {
        if (this.promoRepository.existsById(newPromo.getId())) {
            throw new PromoIsExistsException(newPromo.getId());
        }
        return this.promoRepository.save(newPromo);
    }

    public PromoEntity getPromoFollowID(int id) {
        return this.promoRepository.findById(id).orElseThrow(() ->
                new PromoNotFoundException(id));
    }


    public PromoEntity editPromo(PromoEntity editPromo) {
        if (!this.promoRepository.existsById(editPromo.getId())) {
            throw new PromoNotFoundException(editPromo.getId());
        }
        RoomTypeEntity roomType = roomTypeService.getRoomTypeFollowId(editPromo.getRoomType().getId());
        editPromo.setRoomType(roomType);
        return this.promoRepository.save(editPromo);
    }

    public void deletePromo(int id) {
        if (!this.promoRepository.existsById(id)) {
            throw new PromoNotFoundException(id);
        }
        this.promoRepository.deleteById(id);
    }

    public List<PromoEntity> getAllPromo() {
        return this.promoRepository.findAll();
    }

    public List<PromoEntity> getAllPromoStillActive() {
        return this.promoRepository.getAllPromoStillActive();
    }
}
