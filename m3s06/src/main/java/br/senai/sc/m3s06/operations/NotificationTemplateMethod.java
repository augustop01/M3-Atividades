package br.senai.sc.m3s06.operations;

import br.senai.sc.m3s06.exceptions.InvalidNotificationTypeException;
import br.senai.sc.m3s06.model.Person;
import br.senai.sc.m3s06.operations.create.NotificationFactory;

public abstract class NotificationTemplateMethod {

    protected void notify(Person person, String content) throws InvalidNotificationTypeException {
        NotificationFactory.createNotification(person.getNotificationsEnum()).send(person, content);
    }
}
