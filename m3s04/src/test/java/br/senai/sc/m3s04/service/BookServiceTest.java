package br.senai.sc.m3s04.service;
import br.senai.sc.m3s04.model.Book;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.dto.operations.create.CreateBookDTO;
import br.senai.sc.m3s04.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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
}