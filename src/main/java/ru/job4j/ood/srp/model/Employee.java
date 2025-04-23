package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.Objects;

@XmlRootElement(name = "employee")
@XmlType(propOrder = {"name", "hired", "fired", "salary"})
public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;

    public Employee() { }

    public Employee(
            String name,
            Calendar hired,
            Calendar fired,
            double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    @XmlElement
    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    @XmlElement
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Double.compare(
                employee.salary,
                salary) == 0
                && Objects.equals(name, employee.name)
                && Objects.equals(hired, employee.hired)
                && Objects.equals(fired, employee.fired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                hired,
                fired,
                salary);
    }
}