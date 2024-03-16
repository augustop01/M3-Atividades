package br.senai.sc.m3s06.model.dto.operations;

import br.senai.sc.m3s06.model.Person;

public interface Notifications {
    void send(Person person, String content);
}
