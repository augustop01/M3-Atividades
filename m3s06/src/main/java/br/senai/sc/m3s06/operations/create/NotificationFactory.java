package br.senai.sc.m3s06.operations.create;

import br.senai.sc.m3s06.enums.NotificationsEnum;
import br.senai.sc.m3s06.exceptions.InvalidNotificationTypeException;
import br.senai.sc.m3s06.interfaces.Notifications;
import br.senai.sc.m3s06.operations.notification.EmailNotifications;
import br.senai.sc.m3s06.operations.notification.SMSNotifications;

public class NotificationFactory {
    private NotificationFactory() {

    }

    public static Notifications createNotification(NotificationsEnum type) throws InvalidNotificationTypeException {
        return switch (type) {
            case EMAIL -> new EmailNotifications();
            case SMS -> new SMSNotifications();
            default -> throw new InvalidNotificationTypeException("Type is not valid: " + type);
        };
    }
}
