package ru.job4j.ood.ocp;

public class EmployeeSalaryNoGood4OCP {

    public enum EmployeeType {
        HOURLY,
        SALARIED
    }

    private final EmployeeType type;
    private final double hourlyRate;
    private final double hoursWorked;
    private final double salary;

    public EmployeeSalaryNoGood4OCP(
            EmployeeType type,
            double hourlyRate,
            double hoursWorked,
            double salary) {
        this.type = type;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.salary = salary;
    }

    public double calculatePay() {
        if (type == EmployeeType.HOURLY) {
            return hourlyRate * hoursWorked;
        } else if (type == EmployeeType.SALARIED) {
            return salary;
        } else {
            /*
            В этом примере, если нам потребуется добавить
            новый тип сотрудника (например, комиссионный),
            нам придется изменить метод calculatePay()
            в классе Employee, что является нарушением OCP:
            "Программные сущности (классы, модули, функции...)
            должны быть открыты для расширения, но закрыты для модификации."
            */
            throw new UnsupportedOperationException("Unsupported employee type");
        }
    }
}