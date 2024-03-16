package br.senai.sc.m3s06.repository;

import br.senai.sc.m3s06.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByGuid(String guid);
}
