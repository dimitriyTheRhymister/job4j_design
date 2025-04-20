package ru.job4j.ood.tdd;

public class YourAccount implements Account {

    private String login;
    private String password;
    private String role;

    @Override
    public void register(String login, String password) {
    }

    @Override
    public Account login(String login, String password) {
        return null;
    }

    @Override
    public String getLogin() {
        return "";
    }
}
