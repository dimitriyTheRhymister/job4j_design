package ru.job4j.ood.srp.report;

import org.junit.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONReportEngineTest {

    @Test
    public void whenGeneratedJSONReportThenCorrect() {
        Store store = new MemoryStore();
        Calendar hired = Calendar.getInstance();
        hired.set(2022, Calendar.JANUARY, 23, 17, 52);
        Calendar fired = Calendar.getInstance();
        fired.set(2022, Calendar.JANUARY, 23, 17, 52);
        Employee employee = new Employee("John Lennon", hired, fired, 100000.0);
        store.add(employee);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        JSONReportEngine engine = new JSONReportEngine(store, parser);

        String expected = """
                [
                  {
                    "name": "John Lennon",
                    "hired": "23:01:2022 17:52",
                    "fired": "23:01:2022 17:52",
                    "salary": 100000.0
                  }
                ]""";
        String actual = engine.generate(emp -> true);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenGeneratedJSONReportForMultipleEmployeesThenCorrect() {
        JSONReportEngine engine = getJsonReportEngine();

        String expected = """
                [
                  {
                    "name": "John Lennon",
                    "hired": "23:01:2022 17:52",
                    "fired": "23:01:2022 17:52",
                    "salary": 100000.0
                  },
                  {
                    "name": "Paul McCartney",
                    "hired": "22:01:2023 17:52",
                    "fired": "22:01:2023 17:52",
                    "salary": 50000.0
                  }
                ]""";
        String actual = engine.generate(emp -> true);

        assertThat(actual, is(expected));
    }

    private static JSONReportEngine getJsonReportEngine() {
        Store store = new MemoryStore();
        Calendar hired = Calendar.getInstance();
        hired.set(2022, Calendar.JANUARY, 23, 17, 52);
        Calendar fired = Calendar.getInstance();
        fired.set(2022, Calendar.JANUARY, 23, 17, 52);
        Employee employee = new Employee("John Lennon", hired, fired, 100000.0);
        store.add(employee);

        Calendar hired2 = Calendar.getInstance();
        hired2.set(2023, Calendar.JANUARY, 22, 17, 52);
        Calendar fired2 = Calendar.getInstance();
        fired2.set(2023, Calendar.JANUARY, 22, 17, 52);
        Employee employee2 = new Employee("Paul McCartney", hired2, fired2, 50000.0);
        store.add(employee2);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        return new JSONReportEngine(store, parser);
    }
}