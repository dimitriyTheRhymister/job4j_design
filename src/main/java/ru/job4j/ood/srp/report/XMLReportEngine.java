package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeWhereTimeIsString;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class XMLReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public XMLReportEngine(Store store,
                           DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        try {
            List<EmployeeWhereTimeIsString> employees = store.findBy(filter)
                    .stream()
                    .map(employee -> new EmployeeWhereTimeIsString(
                            employee.getName(),
                            dateTimeParser.parse(employee.getHired()),
                            dateTimeParser.parse(employee.getFired()),
                            employee.getSalary()))
                    .collect(Collectors.toList());

            Employees employeesList = new Employees();
            employeesList.setEmployees(employees);

            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(employeesList, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
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

        XMLReportEngine xmlReportEngine = new XMLReportEngine(store, parser);
        System.out.println(xmlReportEngine.generate(employee -> true));
    }
}
