package ru.job4j.ood.srp.report;

import org.junit.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import static org.junit.Assert.assertEquals;

public class HRReportTest {
    String ls = System.lineSeparator();

    @Test
    public void whenGenerateReportThenCorrectOutput() {
        MemoryStore store = new MemoryStore();
        store.add(new Employee("John", 50000));
        store.add(new Employee("Alice", 60000));
        store.add(new Employee("Bob", 40000));

        HRReport report = new HRReport(store);
        String result = report.generate(employee -> true);

        String expected = "Name; Salary;" + ls
                + "Alice 60000.0" + ls
                + "John 50000.0" + ls
                + "Bob 40000.0" + ls;
        assertEquals(expected, result);
    }

    @Test
    public void whenGenerateReportWithFilterThenCorrectOutput() {
        MemoryStore store = new MemoryStore();
        store.add(new Employee("John", 50000));
        store.add(new Employee("Alice", 60000));
        store.add(new Employee("Bob", 40000));

        HRReport report = new HRReport(store);
        String result = report.generate(employee -> employee.getSalary() > 45000);

        String expected = "Name; Salary;" + ls
                + "Alice 60000.0" + ls
                + "John 50000.0" + ls;
        assertEquals(expected, result);
    }
}