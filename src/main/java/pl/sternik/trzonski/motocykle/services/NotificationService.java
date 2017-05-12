package pl.sternik.trzonski.motocykle.services;

import java.util.List;

import pl.sternik.trzonski.motocykle.services.NotificationServiceImpl.NotificationMessage;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    List<NotificationMessage> getNotificationMessages();
}