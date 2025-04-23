package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngineOrigin implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportEngineOrigin(Store store,
                              DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        ReportDateTimeParser parser = new ReportDateTimeParser();
        MemoryStore store = new MemoryStore();
        Calendar hired = Calendar.getInstance();
        Calendar fired = Calendar.getInstance();
        Employee employeeJohn = new Employee("John Lennon", hired, fired, 100000);
        store.add(employeeJohn);
        Employee employeeJohn2 = new Employee("Paul McCartney", hired, fired, 450000);
        store.add(employeeJohn2);
        Employee employeeJohn3 = new Employee("George Harrison", hired, fired, 300000);
        store.add(employeeJohn3);
        Employee employeeJohn4 = new Employee("Ringo Starr", hired, fired, 300000);
        store.add(employeeJohn4);

        ReportEngineOrigin reportEngine = new ReportEngineOrigin(store, parser);
        System.out.println(reportEngine.generate(employee -> true));
    }
}