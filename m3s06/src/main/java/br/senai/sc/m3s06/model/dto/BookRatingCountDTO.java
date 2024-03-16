package br.senai.sc.m3s06.model.dto;

import java.util.Map;

public record BookRatingCountDTO (
        String guid,
        String title,
        String registeredBy,
        Integer publishedYear,
        double averageRating,
        Map<Integer, Integer> ratingCounts) {
}
