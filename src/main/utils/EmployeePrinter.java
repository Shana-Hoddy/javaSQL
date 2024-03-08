package main.utils;

import main.model.Employee;

import java.util.List;

public class EmployeePrinter {
    public static void printEmployeeDetails(Employee employee) {
        System.out.print("Employee Details:");
        System.out.print(" First Name: " + employee.getFirstName());
        System.out.print(" Last Name: " + employee.getLastName());
        System.out.print(" Email: " + employee.getEmail());
        System.out.print(" Department: " + employee.getDepartment());
        System.out.print(" Salary: " + employee.getSalary());
        // Add other fields as needed
        System.out.println();
    }

    public static void printAllEmployeeDetails(List<Employee> employeeList){
        for(Employee employee: employeeList){
            printEmployeeDetails(employee);
        }
    }
}
