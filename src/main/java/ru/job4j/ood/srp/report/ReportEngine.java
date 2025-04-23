package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public abstract class ReportEngine implements Report {
    protected final Store store;
    protected final DateTimeParser<Calendar> dateTimeParser;

    public ReportEngine(Store store,
                        DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    public abstract String generate(Predicate<Employee> filter);
}
