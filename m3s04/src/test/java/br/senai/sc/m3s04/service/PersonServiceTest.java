package br.senai.sc.m3s04.service;

import static org.mockito.Mockito.verify;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.dto.operations.create.CreatePersonDTO;
import br.senai.sc.m3s04.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PersonService personService;

    @Captor
    private ArgumentCaptor<Person> personCaptor;

    @Test
    void createPersonShouldSavePersonToRepository() {
        CreatePersonDTO createPersonDTO =
                new CreatePersonDTO("Augusto Pires", "augusto@email.com", "aStrongPassword");
        String encodedPassword = this.passwordEncoder.encode(createPersonDTO.password());

        personService.create(createPersonDTO);
        verify(this.personRepository).save(this.personCaptor.capture());
        Person createdPerson = personCaptor.getValue();

        Assertions.assertEquals(createPersonDTO.name(), createdPerson.getName());
        Assertions.assertEquals(createPersonDTO.email(), createdPerson.getEmail());
        Assertions.assertNotNull(createdPerson.getGuid());
        Assertions.assertEquals(36, createdPerson.getGuid().length());
        Assertions.assertEquals(encodedPassword, createdPerson.getPassword());
    }
}