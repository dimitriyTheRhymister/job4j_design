package ru.job4j.ood.lsp;

public class PostconditionsExample {

    public static class BankAccount {
        public double withdraw(double amount) throws Exception {
            if (amount > getBalance()) {
                throw new Exception("Insufficient funds");
            }
            double newBalance = getBalance() - amount;
            setBalance(newBalance);
            return newBalance;
        }

        private void setBalance(double newBalance) {
        }

        private double getBalance() {
            return 0;
        }
    }

    public static class DebitCardAccount extends BankAccount {
        @Override
        public double withdraw(double amount) {
            try {
                return super.withdraw(amount);
            } catch (Exception e) {
                return super.getBalance() - amount;
            }
        }
    }
}
/*В этом примере, класс DebitCardAccount наследует от класса BankAccount и ослабляет
постусловие метода withdraw() , разрешая овердрафт в случае нехватки средств на счете.
Это нарушение принципа LSP, поскольку объекты класса DebitCardAccount не обеспечивают
того же уровня гарантии, что и объекты класса BankAccount, относительно состояния счета
после выполнения метода withdraw().*/
