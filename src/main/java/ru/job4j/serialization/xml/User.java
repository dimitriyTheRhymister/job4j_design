package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private boolean active;
    @XmlAttribute
    private int id;
    @XmlAttribute
    private  String nickname;
    private UserContacts userContacts;
    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    private String[] hobbies;

    public User() { }

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
