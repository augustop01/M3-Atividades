package br.senai.sc.m3s04.model.dto;

import br.senai.sc.m3s04.model.Person;

public record PersonDTO(String guid, String name, String email) {

   public PersonDTO(Person person) {
        this(person.getGuid(),
                person.getName(),
                person.getEmail());
    }
}
