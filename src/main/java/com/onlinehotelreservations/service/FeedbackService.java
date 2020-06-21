package com.onlinehotelreservations.service;

import com.onlinehotelreservations.controller.brand.Exception.BrandNotFoundException;
import com.onlinehotelreservations.controller.feedback.Exception.FeedbackNotFoundException;
import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.entity.FeedbackEntity;
import com.onlinehotelreservations.repository.BrandRepository;
import com.onlinehotelreservations.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final BrandRepository brandRepository;

    public FeedbackEntity getFeedBackFollowID(int id) {
        return this.feedbackRepository.findById(id).orElseThrow(
                () -> new FeedbackNotFoundException(id)
        );
    }

    public List<FeedbackEntity> getAllFeedback() {
        return this.feedbackRepository.findAll();
    }
    // TODO: not yet return
    public void getAllFeedbackFollowIDBrand(int idBrand) {
        BrandEntity brandEntity = this.brandRepository.findById(idBrand).orElseThrow(
                () -> new BrandNotFoundException(idBrand)
        );

        Set<FeedbackEntity> feedbackEntities = this.feedbackRepository.findByBrandEntity(brandEntity);

        feedbackEntities.forEach(item -> {
            System.out.println(item.getDescription());
        });
    }

    public FeedbackEntity addNewFeedback(FeedbackEntity feedbackEntity) {
        return this.feedbackRepository.save(feedbackEntity);
    }
}
