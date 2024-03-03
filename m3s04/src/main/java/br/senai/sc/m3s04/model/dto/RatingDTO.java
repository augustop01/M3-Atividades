package br.senai.sc.m3s04.model.dto;

import br.senai.sc.m3s04.model.Rating;

public record RatingDTO(String guid, Integer rating, PersonDTO ratedBy) {

    public RatingDTO(Rating rating){
        this(rating.getGuid(),
                rating.getRating(),
                new PersonDTO(rating.getRatedBy()));
    }
}
