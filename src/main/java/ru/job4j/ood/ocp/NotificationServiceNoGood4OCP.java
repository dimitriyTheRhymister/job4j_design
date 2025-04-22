package ru.job4j.ood.ocp;

public class NotificationServiceNoGood4OCP {

          public void sendNotification(String message, String type) {
            if (type.equals("EMAIL")) {
                System.out.println("Sending email: " + message);
            } else if (type.equals("SMS")) {
                System.out.println("Sending SMS: " + message);
            }
            /*
            Добавление нового типа уведомления потребует изменения этого метода,
            а принцип OCP: "Программные сущности (классы, модули, функции...)
            должны быть открыты для расширения, но закрыты для модификации."
            */
        }
}
