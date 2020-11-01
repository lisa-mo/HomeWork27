package models;

import java.util.Objects;

public class Employee {
    Integer id;
    String employee_name;
    Integer employee_salary;
    Integer employee_age;
    String profile_image;

    public Employee(String employee_name, Integer employee_salary, Integer employee_age, String profile_image) {
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return  Objects.equals(employee_name, employee.employee_name) &&
                Objects.equals(employee_salary, employee.employee_salary) &&
                Objects.equals(employee_age, employee.employee_age) &&
                Objects.equals(profile_image, employee.profile_image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_name, employee_salary, employee_age, profile_image);
    }
}
