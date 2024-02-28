package br.senai.sc.m3s04.repository;

import br.senai.sc.m3s04.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
