package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.function.Predicate;

public class ITReport implements Report {

    private final MemoryStore store;
    private final ReportDateTimeParser dateTimeParser;

    public ITReport(MemoryStore store, ReportDateTimeParser dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text
                    .append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public void generateToFile(Predicate<Employee> filter, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(generate(filter));
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    public static void main(String[] args) {
        MemoryStore store = new MemoryStore();
        ReportDateTimeParser parser = new ReportDateTimeParser();
        Calendar hired = Calendar.getInstance();
        Calendar fired = Calendar.getInstance();
        Employee employeeJohn = new Employee("John Doe", hired, fired, 100000);
        store.add(employeeJohn);
        Employee employeeJohn2 = new Employee("John Doe2", hired, fired, 200000);
        store.add(employeeJohn2);
        Employee employeeJohn3 = new Employee("John Doe3", hired, fired, 300000);
        store.add(employeeJohn3);

        String data = "data/ITReport.csv";
        Path path = Paths.get(data);
        ITReport itReport = new ITReport(store, parser);
        itReport.generateToFile(employee -> true, String.valueOf(path));
    }
}