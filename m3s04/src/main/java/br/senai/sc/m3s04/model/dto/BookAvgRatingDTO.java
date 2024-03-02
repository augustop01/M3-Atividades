package br.senai.sc.m3s04.model.dto;

import br.senai.sc.m3s04.model.Book;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.Rating;
import br.senai.sc.m3s04.repository.RatingRepository;

import java.util.List;

public record BookAvgRatingDTO(String guid, String title, String createdByGuid, Integer publishedYear, double averageRating) {

    public BookAvgRatingDTO(Book book){
        this(book.getGuid(),
                book.getTitle(),
                book.getRegisteredBy().getGuid(),
                book.getPublishedYear(),
                book.getAverageRating());
    }
}
