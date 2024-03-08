package storedProcedures;

import main.database.DatabaseConnectionManager;
import main.model.Employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetEmployeesForDept {

    public static List<Employee> getEmployeesForDepartment(String department) throws SQLException {
        final String GET_EMPS_FOR_DEPT = "{call get_employees_for_department(?)}";
        List<Employee> employeeList = new ArrayList<>();
        try(Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            CallableStatement callableStatement = connection.prepareCall(GET_EMPS_FOR_DEPT)){
            callableStatement.setString(1, department);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String email = resultSet.getString("email");
                String dept = resultSet.getString("department");
                float salary = resultSet.getFloat("salary");

                Employee employee = new Employee(lastName, firstName, email, dept, salary);
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
}
