package br.senai.sc.m3s04.service;
import br.senai.sc.m3s04.exceptions.BookNotFoundException;
import br.senai.sc.m3s04.model.Book;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.Rating;
import br.senai.sc.m3s04.model.dto.BookAvgRatingDTO;
import br.senai.sc.m3s04.model.dto.BookRatingCountDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s04.repository.BookRepository;
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

    @Captor
    private ArgumentCaptor<Book> bookCaptor;


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
        person.setEmail("user@email.com");
        person.setName("Augusto");
        person.setGuid(UUID.randomUUID().toString());
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
    }
}