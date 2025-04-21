package ru.job4j.ood.tdd;

import java.util.HashMap;
import java.util.Map;

public class YourTicket implements Ticket {

    private Account account;
    private Cinema cinema;
    private Film film;
    private Session session;
    private boolean isPaid;

    @Override
    public void booking(Session session, Map<Integer, Integer> seat, Account account) {
    }

    @Override
    public Ticket confirmPayment(Ticket ticket) {
        return null;
    }

    @Override
    public Session getSession() {
        return null;
    }

    @Override
    public HashMap<Integer, Integer> getSeats() {
        return null;
    }

    @Override
    public Account getAccount() {
        return null;
    }
}
/* нарушение принципа SRP:
    - booking(), getSession() и getSeats() - лучше вынести в отдельный SessionService
ведь создание билета с его параметрами это одна сущность, а сеанс с его параметрами это другая сущность
    - confirmPayment() - лучше вынести в отдельный PaymentService
ведь создание билета с его параметрами это одно дело, а платёж - совсем другое
 */