package br.senai.sc.m3s04.service;

import br.senai.sc.m3s04.exceptions.BookNotFoundException;
import br.senai.sc.m3s04.model.Book;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.Rating;
import br.senai.sc.m3s04.model.dto.BookAvgRatingDTO;
import br.senai.sc.m3s04.model.dto.BookDTO;
import br.senai.sc.m3s04.model.dto.BookRatingCountDTO;
import br.senai.sc.m3s04.model.dto.RatingDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreateRatingDTO;
import br.senai.sc.m3s04.repository.BookRepository;
import br.senai.sc.m3s04.repository.RatingRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private  static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private BookRepository bookRepository;

    private PersonService personService;

    private RatingRepository ratingRepository;

    public BookService(BookRepository bookRepository, PersonService personService, RatingRepository ratingRepository){
        this.bookRepository = bookRepository;
        this.personService = personService;
        this.ratingRepository = ratingRepository;
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

    public List<BookAvgRatingDTO> listAllBooks(){
        List<BookAvgRatingDTO> listBooks = bookRepository.findAll().stream().map(BookAvgRatingDTO::new).collect(Collectors.toList());
        return listBooks;
    }

    public BookRatingCountDTO findBookById(String guid) throws BookNotFoundException {
        Book book = bookRepository.findByGuid(guid).orElseThrow(() -> new BookNotFoundException("Nenhum livro encontrado pelo ID: " + guid));

        Map<Integer, Integer> ratingCounts = calculateRatingCounts(book.getRatingList());

        return new BookRatingCountDTO(
                book.getGuid(),
                book.getTitle(),
                book.getRegisteredBy().getGuid(),
                        book.getPublishedYear(),
                        book.getAverageRating(),
                        ratingCounts
        );
    }

    private Map<Integer, Integer> calculateRatingCounts(Set<Rating> ratings){
        Map<Integer, Integer> ratingCounts = new HashMap<>();
        for(Rating rating : ratings){
            ratingCounts.put(rating.getRating(),
                    ratingCounts.getOrDefault(rating.getRating(), 0) + 1);
        }
        return ratingCounts;
    }

    @Transactional
    public RatingDTO createRating(String bookGuid, CreateRatingDTO body, UserDetails userInSession) throws BookNotFoundException {
        Book book = bookRepository.findByGuid(bookGuid)
                .orElseThrow(() -> new BookNotFoundException("Nenhum livro encontrado com o ID: " + bookGuid));

        Person ratedBy = personService.findByEmail(userInSession.getUsername());

        if (ratedBy.getGuid().equals(book.getRegisteredBy().getGuid())){
            throw new IllegalArgumentException(("O livro não pode ser avaliado pelo seu próprio autor."));
        }

        Rating rating = new Rating(body, ratedBy, book);

        this.ratingRepository.save(rating);

        return new RatingDTO(rating);
    }
}
