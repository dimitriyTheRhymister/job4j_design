package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElement(name = "employee")
    private List<EmployeeWhereTimeIsString> employees;

    public List<EmployeeWhereTimeIsString> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeWhereTimeIsString> employees) {
        this.employees = employees;
    }

    public static void main(String[] args) {
        EmployeeWhereTimeIsString employee = new EmployeeWhereTimeIsString("A", "1", "4", 2.0);
        EmployeeWhereTimeIsString employee2 = new EmployeeWhereTimeIsString("B", "2", "5", 3.0);
        EmployeeWhereTimeIsString employee3 = new EmployeeWhereTimeIsString("C", "3", "6", 4.0);
        List<EmployeeWhereTimeIsString> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);
        list.add(employee3);
        Employees employees = new Employees();
        employees.setEmployees(list);
        System.out.println(employees.getEmployees());
    }
}