package com.onlinehotelreservations.controller.brand;

import com.onlinehotelreservations.controller.brand.DTO.BrandDTO;
import com.onlinehotelreservations.service.BrandService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @GetMapping
    public ApiData<List<BrandDTO>> getAllBrand() {
        return new ApiData<>(this.brandMapper.toBrandDTOs(this.brandService.getAll()));
    }

    @GetMapping("/{id}")
    public ApiData<BrandDTO> getBrandFollowID(@PathVariable("id") int id) {
        return new ApiData<>(this.brandMapper.toBrandDTO(this.brandService.getBrandFollowID(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiData<BrandDTO> addNewBrand(@RequestBody @Validated BrandDTO brandDTO) {
        return new ApiData<>(this.brandMapper.toBrandDTO(
                this.brandService.addNewBrand(this.brandMapper.toBrandEntity(brandDTO))
        ));
    }

    @PutMapping("/{id}")
    public BrandDTO editBrandFollowID(@PathVariable("id") int id, @RequestBody @Validated BrandDTO brandDTO) {
        brandDTO.setId(id);
        return this.brandMapper.toBrandDTO(
                this.brandService.editBrand(
                        this.brandMapper.toBrandEntity(brandDTO)
                )
        );
    }

    @DeleteMapping("/{id}")
    public BrandDTO deleteBrandFollowID(@PathVariable("id") int id) {
        return this.brandMapper.toBrandDTO(
                this.brandService.deleteBrandFollowID(id)
        );
    }

    @GetMapping("/search")
    public List<BrandDTO> searchBrands(@RequestParam(name = "valueSearch") String valueSearch) {
        return this.brandMapper.toBrandDTOs(
                this.brandService.searchBrands(valueSearch)
        );
    }
}
