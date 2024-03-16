package br.senai.sc.m3s06.repository;

import br.senai.sc.m3s06.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findByEmail(String email);
}
