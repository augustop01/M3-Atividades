package br.senai.sc.m3s04.service;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.dto.operations.create.CreatePersonDTO;
import br.senai.sc.m3s04.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PersonRepository personRepository;

    @Captor
    private ArgumentCaptor<Person> personCaptor;


    @Test
    void createUserWithNoFail() {
        CreatePersonDTO person =
                new CreatePersonDTO("Teste 01", "teste01@example.com", "UmaSenhaForte");
        String passwordEncoded = this.passwordEncoder.encode(person.password());

        this.personService.create(person);
        verify(this.personRepository).save(this.personCaptor.capture());
        Person createdPerson = this.personCaptor.getValue();

        Assertions.assertEquals(person.name(), createdPerson.getName());
        Assertions.assertEquals(person.email(), createdPerson.getEmail());
        Assertions.assertNotNull(createdPerson.getGuid());
        Assertions.assertEquals(36, createdPerson.getGuid().length());
        Assertions.assertEquals(passwordEncoded, createdPerson.getPassword());
    }
}