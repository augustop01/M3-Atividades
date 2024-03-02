package br.senai.sc.m3s04.repository;

import br.senai.sc.m3s04.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<Person> findByGuid(String guid);
    Optional<Person> findByEmail(String email);
}
