package br.senai.sc.m3s04.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "LIVRO")
public class Book {
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Person registeredBy;

    @Column(nullable = false)
    private Integer publishedYear;

    /*
    @OneToMany
    private Review reviewsList;
    */

    public Book(){

    }

    public Book(String id, String title, Person registeredBy, Integer publishedYear) {
        this.id = id;
        this.title = title;
        this.registeredBy = registeredBy;
        this.publishedYear = publishedYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(Person registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
