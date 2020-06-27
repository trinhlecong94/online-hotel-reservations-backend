package com.onlinehotelreservations.controller.promo;

import com.onlinehotelreservations.controller.promo.DTO.PromoDTO;
import com.onlinehotelreservations.service.PromoService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/promos")
public class PromoController {

    private final PromoMapper promoMapper;

    private final PromoService promoService;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ApiData<PromoDTO> addNewPromo(@RequestBody @Validated PromoDTO promoDTO) {
        return new ApiData<>(this.promoMapper.toPromoDTO(this.promoService.addNewPromo(
                this.promoMapper.toPromoEntity(promoDTO))));
    }

    @GetMapping("/{id}")
    public ApiData<PromoDTO> getPromoFollowID(@PathVariable("id") int id) {
        return new ApiData<>(this.promoMapper.toPromoDTO(this.promoService.getPromoFollowID(id)));
    }

    @GetMapping
    public ApiData<List<PromoDTO>> getAllPromo() {
        return new ApiData<>(this.promoMapper.toPromoDT0s(this.promoService.getAllPromo()));
    }

    @GetMapping("/active")
    public ApiData<List<PromoDTO>> getAllPromoStillActive() {
        return new ApiData<>(this.promoMapper.toPromoDT0s(this.promoService.getAllPromoStillActive()));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public PromoDTO editPromo(@RequestBody @Validated PromoDTO promoDTO) {
        return this.promoMapper.toPromoDTO((this.promoService.editPromo(
                this.promoMapper.toPromoEntity(promoDTO))));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deletePromo(@PathVariable("id") int id) {
        this.promoService.deletePromo(id);
    }
}
