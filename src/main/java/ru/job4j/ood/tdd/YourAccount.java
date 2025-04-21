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
/* нарушение принципа SRP:
    - register() - лучше вынести в отдельный RegisterService, а
    - login() - лучше вынести в отдельный AuthService
ведь могут появится новые виды аутентификащии и регистрации
 */