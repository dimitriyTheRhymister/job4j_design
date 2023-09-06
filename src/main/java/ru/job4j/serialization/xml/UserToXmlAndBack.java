package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class UserToXmlAndBack {
    public static void main(String[] args) throws Exception {
        UserContacts userContacts = new UserContacts("+7-111-11-11", "user@mail.com");
        String[] hobbies = {"skiing", "games", "travel"};
        User user = new User(true, 33, "Robin", userContacts, hobbies);

        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            User back = (User) unmarshaller.unmarshal(reader);
            System.out.println(back);
        }
    }
}
