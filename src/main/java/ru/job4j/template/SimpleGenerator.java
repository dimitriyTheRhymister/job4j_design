package ru.job4j.template;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Generator {

    static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{(.*?)}");

    @Override
    public String produce(String template, Map<String, String> args) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            if (!args.containsKey(key)) {
                throw new IllegalArgumentException(String.format("Key '%s' not found in args", key));
            }
            matcher.appendReplacement(result, args.get(key));
        }
        matcher.appendTail(result);

        for (String key : args.keySet()) {
            if (template.contains("${" + key + "}")) {
                continue;
            }
            throw new IllegalArgumentException(String.format("Extra key '%s' found in args", key));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Generator generator = new SimpleGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Petr Petrov", "subject", "you");
        String result = generator.produce(template, values);
        System.out.println(result);
    }
}