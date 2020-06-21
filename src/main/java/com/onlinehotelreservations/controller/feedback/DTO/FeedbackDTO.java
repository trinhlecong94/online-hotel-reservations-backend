package com.onlinehotelreservations.controller.feedback.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDTO {
    private int id;

    @Column(nullable = false, length = 45, unique = true)
    private String description;

    @NotNull
    private int rating;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class User {
        @NotNull
        private int id;

        private String email;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Brand {
        @NotNull
        private int id;

        private String name;
    }
}
