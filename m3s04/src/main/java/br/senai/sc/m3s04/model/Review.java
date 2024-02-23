package br.senai.sc.m3s04.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AVALIAÇÃO")
public class Review {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, columnDefinition = "INTEGER CHECK (rate >= 1 AND rate <= 5)")
    private Integer rating;

    @ManyToOne
    @Column(nullable = false)
    private Person ratedBy;

    @ManyToOne
    @Column(nullable = false)
    private Book ratedBook;

    public Review(){

    }

    public Review(String id, Integer rating, Person ratedBy, Book ratedBook) {
        this.id = id;
        this.rating = rating;
        this.ratedBy = ratedBy;
        this.ratedBook = ratedBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
