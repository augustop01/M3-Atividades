package br.senai.sc.m3s06.model;

import br.senai.sc.m3s06.model.dto.operations.create.CreateRatingDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Rating {
    @Id
    @Column(nullable = false, length = 36, unique = true)
    private String guid;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "rated_by", referencedColumnName= "guid",nullable = false)
    private Person ratedBy;

    @ManyToOne
    @JoinColumn(name = "rated_book", referencedColumnName= "guid",nullable = false)
    private Book ratedBook;

    public Rating(){

    }

    public Rating(CreateRatingDTO rating, Person ratedBy, Book ratedBook) {
        this.guid = UUID.randomUUID().toString();
        this.rating = rating.setRating();
        this.ratedBy = ratedBy;
        this.ratedBook = ratedBook;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Person getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(Person ratedBy) {
        this.ratedBy = ratedBy;
    }

    public Book getRatedBook() {
        return ratedBook;
    }

    public void setRatedBook(Book ratedBook) {
        this.ratedBook = ratedBook;
    }
}
