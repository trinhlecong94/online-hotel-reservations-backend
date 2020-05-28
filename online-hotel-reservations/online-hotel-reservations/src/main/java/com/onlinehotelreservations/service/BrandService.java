package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.brand.Exception.BrandNotFoundException;
import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.brandRepository.save(brandEntity);
    }
}
