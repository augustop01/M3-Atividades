package br.senai.sc.m3s06.service;
import br.senai.sc.m3s06.exceptions.BookNotFoundException;
import br.senai.sc.m3s06.model.Book;
import br.senai.sc.m3s06.model.Person;
import br.senai.sc.m3s06.model.Rating;
import br.senai.sc.m3s06.model.dto.BookAvgRatingDTO;
import br.senai.sc.m3s06.model.dto.BookRatingCountDTO;
import br.senai.sc.m3s06.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s06.model.dto.operations.create.CreateRatingDTO;
import br.senai.sc.m3s06.repository.BookRepository;
import br.senai.sc.m3s06.repository.RatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private PersonService personService;


    @Mock
    private BookRepository bookRepository;

    @Mock
    private RatingRepository ratingRepository;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Captor
    private ArgumentCaptor<Rating> ratingCaptor;


    @Test
    void createBookWithNoFail() {
       String email = "user@email.com";
       Person person = new Person();
       person.setEmail(email);
       BDDMockito.given(this.personService.findByEmail(email)).willReturn(person);

        CreateBookDTO createBookDTO = new CreateBookDTO("Um livro aqui", 1999);
        this.bookService.create(createBookDTO, person);

        verify(this.bookRepository).save(this.bookCaptor.capture());
        Book createdBook = this.bookCaptor.getValue();

        Assertions.assertEquals(createBookDTO.title(), createdBook.getTitle());
        Assertions.assertEquals(createBookDTO.publishedYear(), createdBook.getPublishedYear());
        Assertions.assertEquals(email, createdBook.getRegisteredBy().getEmail());
        Assertions.assertNotNull(createdBook.getGuid());
    }

    @Test
    void listBooksReturnSuccess() {
        String email = "user@email.com";
        Person person = new Person();
        person.setEmail(email);

        CreateBookDTO bookOne = new CreateBookDTO("Um livro aqui", 1999);
        CreateBookDTO bookTwo = new CreateBookDTO("Outro livro ali", 2009);

        List<Book> books = new ArrayList<>();
        books.add(new Book(bookOne, person));
        books.add(new Book(bookTwo, person));

        BDDMockito.given(bookRepository.findAll()).willReturn(books);
        List<BookAvgRatingDTO> list = bookService.listAllBooks();

        Assertions.assertNotNull(list);
        Assertions.assertEquals(books.size(), list.size());
    }

    @Test
    void findBookByIdReturnsSuccess() throws BookNotFoundException {
        Person person = new Person();
        person.setGuid(UUID.randomUUID().toString());
        person.setEmail("user@email.com");
        person.setName("Augusto");

        Book book = new Book();
        String guid = UUID.randomUUID().toString();
        book.setGuid(guid);
        book.setRegisteredBy(person);
        book.setTitle("Outro livro aqui");
        book.setPublishedYear(2006);

        BDDMockito.given(bookRepository.findByGuid(guid)).willReturn(Optional.of(book));
        BookRatingCountDTO returned = bookService.findBookById(guid);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(guid, returned.guid());
        Assertions.assertEquals("Outro livro aqui", returned.title());
        Assertions.assertEquals(2006, returned.publishedYear());
        Assertions.assertEquals(36, returned.guid().length());
    }

    @Test
    void rateBookNotFail() throws BookNotFoundException {
        CreateRatingDTO ratingDTO = new CreateRatingDTO(1);
        String bookGuid = UUID.randomUUID().toString();
        String userGuid = UUID.randomUUID().toString();
        String bookAuthorGuid = UUID.randomUUID().toString();

        Person userWhoRates = new Person();
        userWhoRates.setGuid(userGuid);
        userWhoRates.setEmail("user@email.com");
        userWhoRates.setName("Nome Do User");

        Person bookAuthor = new Person();
        bookAuthor.setGuid(bookAuthorGuid);
        bookAuthor.setEmail("author@email.com");
        bookAuthor.setName("Nome do Autor");

        Book ratedBook = new Book();
        ratedBook.setGuid(bookGuid);
        ratedBook.setTitle("Um livro bem ruim.");
        ratedBook.setPublishedYear(2012);
        ratedBook.setRegisteredBy(bookAuthor);

        BDDMockito.given(this.bookRepository.findByGuid(bookGuid)).willReturn(Optional.of(ratedBook));
        BDDMockito.given(this.personService.findByEmail(userWhoRates.getEmail())).willReturn(userWhoRates);

        this.bookService.createRating(bookGuid, ratingDTO, userWhoRates);
        BDDMockito.then(ratingRepository).should().save(ratingCaptor.capture());;
        Rating returnedRating = this.ratingCaptor.getValue();

        Assertions.assertEquals(userWhoRates.getEmail(), returnedRating.getRatedBy().getEmail());
        Assertions.assertEquals(ratingDTO.setRating(), returnedRating.getRating());
        Assertions.assertEquals(36, returnedRating.getGuid().length());
        Assertions.assertNotNull(returnedRating.getGuid());
    }
}