package ru.job4j.serialization.json;

public class UserContacts {

    private final String phone;
    private final String email;

    public UserContacts(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserContacts{"
                + "phone='" + phone + '\''
                + ", email='" + email + '\''
                + '}';
    }

    public static void main(String[] args) {
        UserContacts userContacts = new UserContacts("+7-111-11-11", "user@mail.com");
        System.out.println(userContacts);
    }
}
