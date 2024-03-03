package br.senai.sc.m3s04.model.dto;

import br.senai.sc.m3s04.model.Book;

public record BookAvgRatingDTO(String guid, String title, String createdByGuid, Integer publishedYear, double averageRating) {

    public BookAvgRatingDTO(Book book){
        this(book.getGuid(),
                book.getTitle(),
                book.getRegisteredBy().getGuid(),
                book.getPublishedYear(),
                book.getAverageRating());
    }
}
