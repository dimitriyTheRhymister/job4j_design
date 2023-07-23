package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(" ");
                String s = strings[strings.length - 2];
                if ("404".equals(s)) {
                    stringList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (String line : log) {
                writer.printf("%s%n", line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/log.txt"))) {
            writer.write("""
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] "GET /items/ajax.html HTTP/1.1" 404 1113
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] "GET /job4j.ru/profile HTTP/1.1" 302 -
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] "GET /job4j.ru/profile/ HTTP/1.1" 404 1110
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] "GET /job4j.ru/profileNew/ HTTP/1.1" 404 -
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:48 +0300] "GET / HTTP/1.1" 200 11488
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] "GET /404.png HTTP/1.1" 200 5103
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] "GET /tomcat.css HTTP/1.1" 200 5931
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] "GET /bg-nav.png HTTP/1.1" 200 1404
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] "GET /bg-button.png HTTP/1.1" 200 713
                    0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] "GET /bg-middle.png HTTP/1.1" 200 4048""");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        for (String line : log) {
            System.out.println(line);
        }
        save(log, "data/404.txt");
    }
}