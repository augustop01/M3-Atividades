package br.senai.sc.m3s04.repository;

import br.senai.sc.m3s04.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, String> {
}
