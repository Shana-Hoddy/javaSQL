package main.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeQueriesManual {
    /**
     * Class requires manual management of Connection closing and commits.
     */
    private final static String DELETE_ALL_BY_DEPT = "DELETE FROM employees WHERE department = ?";
    private final static String UPDATE_ALL_SALARIES_BY_DEPT = "UPDATE employees SET salary=? WHERE department = ?";
    public static void deleteAllEmployeesInDepartment(Connection connection, String department) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_BY_DEPT);
        preparedStatement.setString(1, department);
        preparedStatement.executeUpdate();
    }

    public static void updateAllSalariesByDepartment (Connection connection, String department, Float newSalary) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ALL_SALARIES_BY_DEPT);
        preparedStatement.setFloat(1, newSalary);
        preparedStatement.setString(2, department);
        preparedStatement.executeUpdate();
    }



}
