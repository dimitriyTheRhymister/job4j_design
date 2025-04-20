package ru.job4j.ood.tdd;

import java.util.HashMap;
import java.util.Map;

public interface Ticket {

    void booking(Session session, Map<Integer, Integer> seat, Account account);
    Ticket confirmPayment(Ticket ticket);

    Session getSession();

    HashMap<Integer, Integer> getSeats();

    Account getAccount();
}
