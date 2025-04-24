package ru.job4j.ood.lsp;

public class PreconditionExample {

    public static class PaymentGateway {
        public void processPayment(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
        }
    }

    public static class PremiumPaymentGateway extends PaymentGateway {
        @Override
        public void processPayment(double amount) {
            if (amount < 100) {
                throw new IllegalArgumentException("Amount must be positive and at least 100");
            }
        }
    }
}
/*В этом примере, класс PremiumPaymentGateway наследует от класса PaymentGateway
и усиливает предусловие метода processPayment() , требуя, чтобы сумма была не только
положительной, но и не менее 100. Это нарушение принципа LSP, поскольку объекты класса
PremiumPaymentGateway не могут быть использованы везде, где ожидается объект класса
PaymentGateway, без нарушения корректности программы.*/