package br.senai.sc.m3s04.controller;
import br.senai.sc.m3s04.model.dto.PersonDTO;
import br.senai.sc.m3s04.model.dto.operations.create.CreatePersonDTO;
import br.senai.sc.m3s04.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody CreatePersonDTO createPersonDTO){
        PersonDTO response = this.personService.create(createPersonDTO);
        return ResponseEntity.created(URI.create(String.format("/user/%s", response.guid()))).body(response);
    }
}
