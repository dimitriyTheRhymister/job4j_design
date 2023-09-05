package ru.job4j.serialization.json;

import java.util.Arrays;

public class User {
    private final boolean active;
    private final int id;
    private  final String nickname;
    private final UserContacts userContacts;
    private final String[] hobbies;

    public User(boolean active, int id, String nickname, UserContacts userContacts, String[] hobbies) {
        this.active = active;
        this.id = id;
        this.nickname = nickname;
        this.userContacts = userContacts;
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{"
                + "active=" + active
                + ", id=" + id
                + ", nickname=" + nickname
                + ", userContacts=" + userContacts
                + ", hobbies=" + Arrays.toString(hobbies)
                + '}';
    }

    public static void main(String[] args) {
        UserContacts userContacts = new UserContacts("+7-111-11-11", "user@mail.com");
        String[] hobbies = {"skiing", "games", "travel"};
        User user = new User(true, 33, "Robin", userContacts, hobbies);
        System.out.println(user);
    }
}
