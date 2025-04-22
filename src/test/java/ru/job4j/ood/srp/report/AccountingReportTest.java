package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingReportTest {

    @Test
    public void whenGenerateReportUSDThenCorrectOutputEmployeeFired() {
        MemoryStore store = new MemoryStore();
        Calendar hired = Calendar.getInstance();
        hired.set(2023, Calendar.JANUARY, 1, 1, 11);
        Calendar fired = Calendar.getInstance();
        fired.set(2023, Calendar.JANUARY, 1, 1, 22);
        Employee employee = new Employee("John Doe", hired, fired, 100000);
        store.add(employee);

        ReportDateTimeParser parser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        AccountingReport engine = new AccountingReport(store, parser, converter, Currency.USD);

        String report = engine.generate(e -> true);
        String expected = String.format("Name; Hired; Fired; Salary;%nJohn Doe;01:01:2023 01:11;01:01:2023 01:22;%.2f USD%n", 100000 * 0.0162);
        assertThat(report).isEqualTo(expected);
    }

    @Test
    public void whenGenerateReportEURThenCorrectOutputEmployeeWorks() {
        MemoryStore store = new MemoryStore();
        Calendar hired = Calendar.getInstance();
        hired.set(2023, Calendar.JANUARY, 1, 1, 22);
        Employee employee = new Employee("John Doe", hired, null, 100000);
        store.add(employee);

        ReportDateTimeParser parser = new ReportDateTimeParser();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        AccountingReport engine = new AccountingReport(store, parser, converter, Currency.EUR);

        String report = engine.generate(e -> true);
        String expected = String.format("Name; Hired; Fired; Salary;%nJohn Doe;01:01:2023 01:22;;%.2f EUR%n", 100000 * 0.0166);
        assertThat(report).isEqualTo(expected);
    }
}