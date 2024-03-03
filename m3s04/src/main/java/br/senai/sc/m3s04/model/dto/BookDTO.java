package br.senai.sc.m3s04.model.dto;

import br.senai.sc.m3s04.model.Book;

public record BookDTO(String guid,
                      String title,
                      PersonDTO registeredBy,
                      Integer publishedYear) {

    public BookDTO(Book book){
        this(book.getGuid(),
                book.getTitle(),
                new PersonDTO(book.getRegisteredBy()),
                        book.getPublishedYear());
    }
}
