package ru.job4j.ood.dip;

public class Sample2 {
    public static class EmailNotifier {
        public void send(String message) {
            /* Реализация отправки email */
            System.out.println("Отправлено по email: " + message);
        }
    }

    public static class OrderService {
        private final EmailNotifier notifier;

        public OrderService() {
            this.notifier = new EmailNotifier();
        }

        public void processOrder(String order) {
            /* Обработка заказа */
            notifier.send("Заказ " + order + " обработан");
        }

        public static void main(String[] args) {
            OrderService orderService = new OrderService();
            orderService.processOrder("12345");
        }
    }
    /* Здесь OrderService зависит от конкретной реализации EmailNotifier.
    Если мы захотим добавить поддержку других способов уведомления (SMS, Telegram
    и т.п.), нам придется изменить OrderService, что снова нарушает DIP. */
}
