package br.senai.sc.m3s04.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "AVALIACAO")
public class Rating {
    @Id
    @Column(nullable = false, length = 36, unique = true)
    private String guid;

    @Column(nullable = false, columnDefinition = "INTEGER CHECK (rate >= 1 AND rate <= 5)")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "rated_by", referencedColumnName= "guid",nullable = false)
    private Person ratedBy;

    @ManyToOne
    @JoinColumn(name = "rated_book", referencedColumnName= "guid",nullable = false)
    private Book ratedBook;

    public Rating(){

    }

    public Rating(Integer rating, Person ratedBy, Book ratedBook) {
        this.guid = UUID.randomUUID().toString();
        this.rating = rating;
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
