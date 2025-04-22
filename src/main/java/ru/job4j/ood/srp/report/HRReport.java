package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.Comparator;
import java.util.function.Predicate;

public class HRReport implements Report {

    private final MemoryStore store;

    public HRReport(MemoryStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text
                .append("Name; Salary;")
                .append(System.lineSeparator());
        store
                .findBy(filter)
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(employee -> text
                        .append(employee.getName())
                        .append(" ")
                        .append(employee.getSalary())
                        .append(System.lineSeparator()));
        return text.toString();
    }

    public static void main(String[] args) {
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

        HRReport engineUSD = new HRReport(store);
        String reportUSD = engineUSD.generate(employee -> true);
        System.out.println(reportUSD);
    }
}