package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class XMLReportEngineTest {

    Store store = new MemoryStore();

    @Test
    public void whenGeneratedXMLReportThenCorrect() {

        Calendar hired = Calendar.getInstance();
        hired.set(2022, Calendar.JANUARY, 1, 11, 22);
        Calendar fired = Calendar.getInstance();
        fired.set(2022, Calendar.JANUARY, 1, 11, 22);
        Employee employee = new Employee("John Doe", hired, fired, 50000.0);
        store.add(employee);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        XMLReportEngine engine = new XMLReportEngine(store, parser);

        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>John Doe</name>
                        <hired>01:01:2022 11:22</hired>
                        <fired>01:01:2022 11:22</fired>
                        <salary>50000.0</salary>
                    </employee>
                </employees>
                """;
        String actual = engine.generate(emp -> true);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenGeneratedXMLReportForMultipleEmployeesThenCorrect() {

        Calendar hired = Calendar.getInstance();
        hired.set(2022, Calendar.JANUARY, 1, 11, 22);
        Calendar fired = Calendar.getInstance();
        fired.set(2022, Calendar.JANUARY, 1, 11, 22);
        Employee employee = new Employee("John Lennon", hired, fired, 50000.0);
        store.add(employee);


        Calendar hired2 = Calendar.getInstance();
        hired2.set(2023, Calendar.JANUARY, 3, 11, 22);
        Calendar fired2 = Calendar.getInstance();
        fired2.set(2023, Calendar.JANUARY, 3, 11, 22);
        Employee employee2 = new Employee("Paul McCartney", hired2, fired2, 100000.0);
        store.add(employee2);

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        XMLReportEngine engine = new XMLReportEngine(store, parser);

        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>John Lennon</name>
                        <hired>01:01:2022 11:22</hired>
                        <fired>01:01:2022 11:22</fired>
                        <salary>50000.0</salary>
                    </employee>
                    <employee>
                        <name>Paul McCartney</name>
                        <hired>03:01:2023 11:22</hired>
                        <fired>03:01:2023 11:22</fired>
                        <salary>100000.0</salary>
                    </employee>
                </employees>
                """;
        String actual = engine.generate(emp -> true);

        assertThat(actual, is(expected));
    }

}