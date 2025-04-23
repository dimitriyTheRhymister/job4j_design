package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeWhereTimeIsString;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JSONReportEngine extends ReportEngine {

    public JSONReportEngine(Store store,
                            DateTimeParser<Calendar> dateTimeParser) {
        super(store, dateTimeParser);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<EmployeeWhereTimeIsString> employees = store.findBy(filter).stream()
                .map(employee -> new EmployeeWhereTimeIsString(
                        employee.getName(),
                        dateTimeParser.parse(employee.getHired()),
                        dateTimeParser.parse(employee.getFired()),
                        employee.getSalary()
                ))
                .collect(Collectors.toList());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(employees);
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

        JSONReportEngine jsonReportEngine = new JSONReportEngine(store, parser);
        System.out.println(jsonReportEngine.generate(employee -> true));
    }
}