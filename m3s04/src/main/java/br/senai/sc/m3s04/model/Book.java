package br.senai.sc.m3s04.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "LIVRO")
public class Book {
    @Id
    @Column(nullable = false, length = 36, unique = true)
    @GeneratedValue()
    private String guid;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "registered_by", referencedColumnName = "guid", nullable = false)
    private Person registeredBy;

    @Column(nullable = false)
    private Integer publishedYear;

    @OneToMany(mappedBy = "ratedBook")
    private Set<Rating> ratingList = new HashSet<>();

    public Book(){

    }

    public Book(String title, Person registeredBy, Integer publishedYear) {
        this.guid = UUID.randomUUID().toString();
        this.title = title;
        this.registeredBy = registeredBy;
        this.publishedYear = publishedYear;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
