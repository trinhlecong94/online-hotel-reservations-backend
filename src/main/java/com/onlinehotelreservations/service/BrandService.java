package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.brand.Exception.BrandNotFoundException;
import com.onlinehotelreservations.controller.brand.Exception.NameBrandIsExistException;
import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;

    public List<BrandEntity> getAll() {
        List<BrandEntity> brandEntities = this.brandRepository.findAll();
        return brandEntities;
    }

    public BrandEntity getBrandFollowID(int id) {
        return this.brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
    }

    public BrandEntity addNewBrand(BrandEntity brandEntity) {
        if (this.brandRepository.findByName(brandEntity.getName()).isPresent()) {
            throw new NameBrandIsExistException(brandEntity.getName());
        }
        return this.brandRepository.save(brandEntity);
    }

    public BrandEntity editBrand(BrandEntity brandEntity) {
        BrandEntity brandEntityFromDatabase = this.getBrandFollowID(brandEntity.getId());

        // hold default brand
        brandEntity.setHotelEntity(brandEntityFromDatabase.getHotelEntity());
        brandEntity.setFeedbackEntities(brandEntityFromDatabase.getFeedbackEntities());

        return this.brandRepository.save(brandEntity);
    }

    public BrandEntity deleteBrandFollowID(int id) {
        BrandEntity brandEntity = this.getBrandFollowID(id);
        this.brandRepository.delete(brandEntity);
        return brandEntity;
    }

    public List<BrandEntity> searchBrands(String keySearch) {
        List<BrandEntity> brandEntities = this.brandRepository.findBrandsByKeyword(keySearch);
        return brandEntities;
    }
}
