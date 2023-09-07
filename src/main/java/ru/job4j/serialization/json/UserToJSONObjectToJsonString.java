package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserToJSONObjectToJsonString {
    public static void main(String[] args) {
        String jsonStringUC = "{\"phone\":\"+7-111-11-11\", \"email\":\"user@mail.com\"}";
        JSONObject jsonObjectUC = new JSONObject(jsonStringUC);

        List<String> hobbiesList = new ArrayList<>();
        hobbiesList.add("skiing");
        hobbiesList.add("games");
        hobbiesList.add("travel");
        JSONArray jsonHobbies = new JSONArray(hobbiesList);

        final User user = new User(true, 33, "Robin", new UserContacts("+7-222-22-22", "noUser@mail.com"), new String[] {"skis", "dancing", "playing cards"});
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("active", user.getActive());
        userJsonObject.put("id", user.getId());
        userJsonObject.put("nickname", user.getNickname());
        userJsonObject.put("userContacts", jsonObjectUC);
        userJsonObject.put("hobbies", jsonHobbies);

        System.out.println(userJsonObject);
        System.out.println(new JSONObject(user));
    }
}
