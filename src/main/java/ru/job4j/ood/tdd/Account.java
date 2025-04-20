package ru.job4j.ood.tdd;

public interface Account {

    void register(String login, String password);
    Account login(String login, String password);

    String getLogin();
}
