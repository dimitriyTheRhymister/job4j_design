package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class YourCinemaTest {

    /*#1 - 2 possible tests 4 Account-----------------------------------*/
    @Test
    public void whenRegisterThenReturnAccount() {
        Account account = new YourAccount();
        account.register("testLogin", "testPassword");
        String log = "testLogin";

        assertNotNull(account);
        assertEquals(log, account.getLogin());
    }

    @Test
    public void whenLoginWithCorrectCredentialsThenReturnAccount() {
    }

    /*#2 - 2 possible tests 4 Cinema------------------------------------*/
    @Test
    public void whenFindSessionsByPredicateThenReturnFilteredSessions() {
        Cinema cinema = new YourCinema();
        Session session1 = new YourSession();
        Session session2 = new YourSession();
        cinema.add(session1);
        cinema.add(session2);
        Predicate<Session> filter = s -> s.equals(session1);
        List<Session> list = cinema.findSessionsByPredicate(filter);

        assertEquals(1, list.size());
        assertEquals(session1, list.get(0));
    }

    @Test
    public void whenAddSessionThenSessionIsSaved() {
    }

    /*#3 - 3 possible tests 4 Film-----------------------------------*/
    @Test
    public void whenGetAllThenReturnListOfFilms() {
        List<Film> list = new ArrayList<>();
        Film film1 = new YourFilm();
        Film film2 = new YourFilm();
        list.add(film1);
        list.add(film2);

        assertEquals(2, list.size());
        assertTrue(list.contains(film1) && list.contains(film2));

    }

    @Test
    public void whenGetByIdThenReturnCorrectFilm() {
    }

    @Test
    public void whenGetByGenreThenReturnFilmsOfThatGenre() {
    }

    /*#4 - 2 possible tests 4 Ticket--------------------------------*/
    @Test
    public void whenBookingThenReturnTicketWithCorrectData() {
        Ticket ticket = new YourTicket();
        Session session = new YourSession();
        HashMap<Integer, Integer> seats = new HashMap<>(1, 1);
        Account account = new YourAccount();
        ticket.booking(session, seats, account);

        assertNotNull(ticket);
        assertEquals(session, ticket.getSession());
        assertEquals(seats, ticket.getSeats());
        assertEquals(account, ticket.getAccount());
    }

    @Test
    public void whenConfirmPaymentThenUpdateTicketStatus() {
    }

    /*#5 - 3 possible tests 4 Session--------------------------------*/
    @Test
    public void whenFindSessionsByFilmThenReturnCorrectSessions() {
        Session session = new YourSession();
        Film film = new YourFilm();
        LocalDateTime date = LocalDateTime.now();
        List<Session> sessions = session.findSessionsByFilm(film, date);

        assertFalse(sessions.isEmpty());
    }

    @Test
    public void whenAvailableSeatsThenReturnAvailableSeats() {
    }

    @Test
    public void whenBookSeatThenSeatIsBooked() {
    }
}