package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReport implements Report {

    private final MemoryStore store;
    private final ReportDateTimeParser dateTimeParser;
    private final CurrencyConverter currencyConverter;
    private final Currency targetCurrency;

    public AccountingReport(MemoryStore store, ReportDateTimeParser dateTimeParser, CurrencyConverter currencyConverter, Currency targetCurrency) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double salaryInTargetCurrency = currencyConverter.convert(Currency.RUB, employee.getSalary(), targetCurrency);
            text
                    .append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(employee.getFired() == null ? "" : dateTimeParser.parse(employee.getFired())).append(";")
                    .append(String.format("%.2f %s", salaryInTargetCurrency, targetCurrency))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemoryStore store = new MemoryStore();
        ReportDateTimeParser parser = new ReportDateTimeParser();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Calendar hired = Calendar.getInstance();
        Calendar fired = Calendar.getInstance();
        Employee employeeJohn = new Employee("John Doe", hired, fired, 100000);
        store.add(employeeJohn);

        /* Генерация отчета в USD*/
        AccountingReport engineUSD = new AccountingReport(store, parser, converter, Currency.USD);
        String reportUSD = engineUSD.generate(employee -> true);
        System.out.println(reportUSD);

        /* Генерация отчета в EUR*/
        AccountingReport engineEUR = new AccountingReport(store, parser, converter, Currency.EUR);
        String reportEUR = engineEUR.generate(employee -> true);
        System.out.println(reportEUR);
    }
}