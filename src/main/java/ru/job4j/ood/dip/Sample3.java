package ru.job4j.ood.dip;

public class Sample3 {
    public static class PayPalGateway {
        public void processPayment(double amount) {
            /* Реализация оплаты через PayPal */
            System.out.println("Оплата " + amount + " через PayPal");
        }
    }

    public static class PaymentService {
        private final PayPalGateway gateway;

        public PaymentService() {
            this.gateway = new PayPalGateway();
        }

        public void makePayment(double amount) {
            gateway.processPayment(amount);
        }

        public static void main(String[] args) {
            PaymentService paymentService = new PaymentService();
            paymentService.makePayment(100);
        }
        /* В этом случае PaymentService зависит от конкретной реализации
        PayPalGateway. Если мы захотим добавить поддержку других платежных
        шлюзов, нам нужно будет изменить PaymentService, нарушая DIP. */
    }
}
