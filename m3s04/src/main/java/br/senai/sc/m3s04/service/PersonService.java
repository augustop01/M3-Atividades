package br.senai.sc.m3s04.service;
import br.senai.sc.m3s04.model.Person;
import br.senai.sc.m3s04.model.dto.PersonDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreatePersonDTO;
import br.senai.sc.m3s04.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return this.personRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário foi encontrado pelo e-mail: " + username));
    }

    public Person findByEmail(String email) throws UsernameNotFoundException {
        LOGGER.info("Procurando usuário pelo email: {}...", email);
        return this.personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário foi encontrado pela busca por e-mail: " + email));
    }

    @Transactional
    public PersonDTO create(CreatePersonDTO createPersonDTO){
        LOGGER.info("Criando usuário...");
        String passwordEncoded = this.passwordEncoder.encode(createPersonDTO.password());
        Person person = this.personRepository.save(new Person(createPersonDTO, passwordEncoded));
        return new PersonDTO(person);
    }

}
