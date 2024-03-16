package br.senai.sc.m3s06.model.dto;

import br.senai.sc.m3s06.enums.NotificationsEnum;
import br.senai.sc.m3s06.model.Person;

public record PersonDTO(String guid, String name, String email, String phone, NotificationsEnum notificationsEnum) {

   public PersonDTO(Person person) {
        this(person.getGuid(),
                person.getName(),
                person.getEmail(),
                person.getPhone(),
                person.getNotificationsEnum());
    }
}
