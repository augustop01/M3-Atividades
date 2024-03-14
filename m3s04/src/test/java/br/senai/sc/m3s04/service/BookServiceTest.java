//package com.avalialivros.m3s04.service;
//
//import br.senai.sc.m3s04.model.Book;
//import br.senai.sc.m3s04.repository.BookRepository;
//import br.senai.sc.m3s04.service.BookService;
//import br.senai.sc.m3s04.service.PersonService;
//import com.avalialivros.m3s04.exceptions.PersonNotFoundException;
//import com.avalialivros.m3s04.model.Person;
//import com.avalialivros.m3s04.model.transport.operations.CreatePersonDTO;
//import com.avalialivros.m3s04.repository.PersonRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//
//public class BookServiceTest {
//
//
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Mock
//    private PersonService personService;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @Captor
//    private ArgumentCaptor<Book> bookCaptor;
//
//
//    @Test
//    void createBookWithNoFail() {
//
//    }
//
//    @Test
//    void loadUserByEmailWhenUserIsFound() {
//        String name = "Teste 01";
//        String email = "teste01@example.com.br";
//        String exceptionMessage = "Usuário não encontrado";
//        Person person = new Person();
//        person.setName(name);
//        person.setEmail(email);
//        person.setPassword(this.passwordEncoder.encode("UmaSenhaForte"));
//
//        BDDMockito.given(this.personRepository.findByEmail(email))
//                .willReturn(Optional.of(person));
//
//        Assertions.assertDoesNotThrow(
//                () -> this.personService.findByEmail(email), exceptionMessage);
//    }
//
//    @Test
//    void loadUserByEmailWhenUserIsNotFound() {
//        String email = "teste01@example.com.br";
//        String exceptionMessage = "Usuário não encontrado";
//
//        BDDMockito.given(this.personRepository.findByEmail(email))
//                .willReturn(Optional.empty());
//
//        Assertions.assertThrows(PersonNotFoundException.class,
//                () -> this.personService.findByEmail(email), exceptionMessage);
//    }
//}
//
