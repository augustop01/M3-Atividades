package br.senai.sc.m3s04.repository;

import br.senai.sc.m3s04.model.Book;
import br.senai.sc.m3s04.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

}
