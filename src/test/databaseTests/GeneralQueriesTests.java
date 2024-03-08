package test.databaseTests;

import main.database.DatabaseSetup;
import main.database.EmployeeQueriesAuto;
import main.model.Employee;
import main.utils.EmployeePrinter;
import storedProcedures.GetCountForDept;
import storedProcedures.GetEmployeesForDept;
import storedProcedures.GreetTheDept;
import storedProcedures.IncreaseSalariesForDept;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class GeneralQueriesTests {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseSetup.recreateEmployeeTable();
        runEmployeeQueries();
        runStoredProcedures();
    }

    public static void runEmployeeQueries() throws SQLException {
        EmployeeQueriesAuto.printAllEmployees();

        Employee employee = new Employee("hennessy", "daria", "dariahennessy@google.com", "HR", 50000.00f);
        EmployeeQueriesAuto.insertNewEmployee(employee);
        System.out.println("Added new employee: " + employee.getEmail());
        EmployeeQueriesAuto.printEmployee(employee.getEmail());

        System.out.println("Updating employee's email address");
        employee.setEmail("daria@gmail.com");
        EmployeeQueriesAuto.updateEmployeeEmail(employee, employee.getEmail());
        EmployeeQueriesAuto.printEmployee(employee.getEmail());

        System.out.println("Deleting employee: " + employee.getEmail());
        EmployeeQueriesAuto.deleteEmployee(employee.getEmail());
        EmployeeQueriesAuto.printEmployee(employee.getEmail());
    }

    public static void runStoredProcedures() throws SQLException {
        IncreaseSalariesForDept.increaseSalariesForDept("Engineering", 100000f);
        EmployeeQueriesAuto.printAllEmployees();

        GreetTheDept.greetTheDepartment("Engineering");

        int count = GetCountForDept.getCountForDepartment("Engineering");
        System.out.println("There are: " + count + " members in Engineering.");

        List<Employee> employeeList = GetEmployeesForDept.getEmployeesForDepartment("Engineering");
        EmployeePrinter.printAllEmployeeDetails(employeeList);
    }

}

