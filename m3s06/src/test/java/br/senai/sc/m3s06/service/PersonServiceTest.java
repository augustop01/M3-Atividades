package br.senai.sc.m3s06.service;
import br.senai.sc.m3s06.enums.NotificationsEnum;
import br.senai.sc.m3s06.model.Person;
import br.senai.sc.m3s06.model.dto.operations.create.CreatePersonDTO;
import br.senai.sc.m3s06.repository.PersonRepository;
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
        CreatePersonDTO createPersonDTO =
                new CreatePersonDTO("Teste 01", "teste01@example.com", "47998981717", "UmaSenhaForte", NotificationsEnum.EMAIL);
        String passwordEncoded = this.passwordEncoder.encode(createPersonDTO.password());

        this.personService.create(createPersonDTO);
        verify(this.personRepository).save(this.personCaptor.capture());
        Person createdPerson = this.personCaptor.getValue();

        Assertions.assertEquals(createPersonDTO.name(), createdPerson.getName());
        Assertions.assertEquals(createPersonDTO.email(), createdPerson.getEmail());
        Assertions.assertEquals(createPersonDTO.phone(), createdPerson.getPhone());
        Assertions.assertNotNull(createdPerson.getGuid());
        Assertions.assertEquals(36, createdPerson.getGuid().length());
        Assertions.assertEquals(passwordEncoded, createdPerson.getPassword());
    }
}