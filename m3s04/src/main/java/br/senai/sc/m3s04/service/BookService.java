package br.senai.sc.m3s04.service;

import br.senai.sc.m3s04.model.Book;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.dto.BookDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s04.repository.BookRepository;
import br.senai.sc.m3s04.repository.RatingRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private  static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private BookRepository bookRepository;

    private PersonService personService;

    public BookService(BookRepository bookRepository, PersonService personService){
        this.bookRepository = bookRepository;
        this.personService = personService;
    }

    @Transactional
    public BookDTO create(CreateBookDTO createBookDTO, UserDetails userInSession) throws UsernameNotFoundException {
        LOGGER.info("Autorização garantida. Iniciando a criação de um livro conforme requisição do respectivo usuário: {}", userInSession.getUsername());
        Person person = this.personService.findByEmail(userInSession.getUsername());
        Book book = new Book(createBookDTO, person);
        this.bookRepository.save(book);
        LOGGER.info("O livro '{}' foi salvo, retornando ao banco de dados...", book.getTitle());
        return new BookDTO(book);
    }
}
