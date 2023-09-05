package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserToJsonAndBack {
    public static void main(String[] args) {
        UserContacts userContacts = new UserContacts("+7-111-11-11", "user@mail.com");
        String[] hobbies = {"skiing", "games", "travel"};
        User user = new User(true, 33, "Robin", userContacts, hobbies);
        final Gson userToJson = new GsonBuilder().create();
        System.out.println(userToJson.toJson(user));

        final String userJson =
                "{"
                + "\"active\":true,"
                + "\"id\":33,"
                + "\"nickname\":\"Robin\","
                + "\"userContacts\":"
                + "{"
                + "\"phone\":\"+7-111-11-11\",\"email\":\"user@mail.com\""
                + "},"
                + "\"hobbies\":[\"skiing\",\"games\",\"travel\"]"
                + "}";
       final User userBack = userToJson.fromJson(userJson, User.class);
        System.out.println(userBack);
    }
}
//{
//        "active":true,
//        "id":33,
//        "nickname":"Robin",
//        "userContacts":{
//            "phone":"+7-111-11-11",
//            "email":"user@mail.com"
//        },
//        "hobbies":["skiing","games","travel"]
//}