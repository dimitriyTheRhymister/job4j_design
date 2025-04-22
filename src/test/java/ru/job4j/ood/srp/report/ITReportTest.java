package ru.job4j.ood.srp.report;

import org.junit.Test;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ITReportTest {
    Calendar hired = Calendar.getInstance();
    Calendar fired = Calendar.getInstance();
    MemoryStore store = new MemoryStore();
    ReportDateTimeParser dateTimeParser = new ReportDateTimeParser();

    private void prepareStore() {
        hired.set(2023, Calendar.JANUARY, 1, 1, 11);
        fired.set(2023, Calendar.JANUARY, 1, 1, 22);

        store.add(new Employee("John Doe", hired, fired, 1000));
        store.add(new Employee("Jack Bob", hired, fired, 3000));
        store.add(new Employee("William Johnson", hired, fired, 2000));
    }

    @Test
    public void whenGenerateToFileThenCorrectOutput() {
        prepareStore();
        ITReport report = new ITReport(store, dateTimeParser);
        report.generateToFile(employee -> true, "data/4ITReportTest/test_report.csv");

        File file = new File("data/4ITReportTest/test_report.csv");
        assertTrue(file.exists());

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            String expected = """
                    Name;Hired;Fired;Salary
                    John Doe;01:01:2023 01:11;01:01:2023 01:22;1000.0
                    Jack Bob;01:01:2023 01:11;01:01:2023 01:22;3000.0
                    William Johnson;01:01:2023 01:11;01:01:2023 01:22;2000.0""";

            assertTrue(content.toString().startsWith("Name;Hired;Fired;Salary\n"));
            String[] lines = content.toString().split("\n");
            assertEquals(4, lines.length);
            assertThat(content.toString().trim()).isEqualTo(expected);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void whenGenerateToFileWithFilterThenCorrectOutput() {
        prepareStore();
        ITReport report = new ITReport(store, dateTimeParser);
        report.generateToFile(employee -> employee.getSalary() > 1500, "data/4ITReportTest/test_report_filtered.csv");

        File file = new File("data/4ITReportTest/test_report_filtered.csv");
        assertTrue(file.exists());

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            String[] lines = content.toString().split("\n");
            assertEquals(3, lines.length);
            assertTrue(lines[1].contains("Jack Bob"));
            assertTrue(lines[2].contains("William Johnson"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}