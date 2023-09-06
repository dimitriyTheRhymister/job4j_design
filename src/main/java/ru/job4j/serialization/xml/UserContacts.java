package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userContacts")
public class UserContacts {
    @XmlAttribute
    private String phone;
    @XmlAttribute
    private String email;

    public UserContacts() { }

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
