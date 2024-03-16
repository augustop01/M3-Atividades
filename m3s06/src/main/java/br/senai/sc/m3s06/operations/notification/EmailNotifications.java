package br.senai.sc.m3s06.operations.notification;

import br.senai.sc.m3s06.interfaces.Notifications;
import br.senai.sc.m3s06.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotifications implements Notifications {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailNotifications.class);


    @Override
    public void send(Person person, String content) {
        LOGGER.info("Notification for {}: {}", person.getEmail(), content);
    }
}
