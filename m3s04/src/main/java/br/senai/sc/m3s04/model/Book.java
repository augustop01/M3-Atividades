package br.senai.sc.m3s04.model;

import br.senai.sc.m3s04.model.dto.operations.create.CreateBookDTO;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Book {
    @Id
    @Column(nullable = false, length = 36, unique = true)
    private String guid;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "registered_by", referencedColumnName = "email", nullable = false)
    private Person registeredBy;

    @Column(nullable = false)
    private Integer publishedYear;

    @OneToMany(mappedBy = "ratedBook")
    private Set<Rating> ratingList = new HashSet<>();

    @Transient
    private Map<Integer, Integer> ratingCount = new HashMap<>();

    public Book(){

    }

    public Book(CreateBookDTO createBookDTO, Person person) {
        this.guid = UUID.randomUUID().toString();
        this.title = createBookDTO.title();
        this.registeredBy = person;
        this.publishedYear = createBookDTO.publishedYear();
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

    public Set<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(Set<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public Double getAverageRating(){
        if (ratingList.isEmpty()){
            return 0.0;
        }
        double sum = ratingList.stream().mapToDouble(Rating::getRating).sum();

        return sum / ratingList.size();
    }

    public Map<Integer, Integer> getRatingCounts() {
        for (Rating rating : ratingList) {
            ratingCount.put(rating.getRating(),
                    ratingCount.getOrDefault(rating.getRating(), 0) + 1);
        }
        return ratingCount;
    }

}
