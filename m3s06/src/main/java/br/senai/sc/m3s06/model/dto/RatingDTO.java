package br.senai.sc.m3s06.model.dto;

import br.senai.sc.m3s06.model.Rating;

public record RatingDTO(String guid, Integer rating, String ratedByGuid, String ratedBookGuid) {

    public RatingDTO(Rating rating){
        this(rating.getGuid(),
                rating.getRating(),
                rating.getRatedBy().getGuid(),
                rating.getRatedBook().getGuid());
    }


}
