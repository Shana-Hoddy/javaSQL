package main.database;

import main.model.Employee;
import main.utils.FileHelper;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Class automatically creates and closes database connections and auto commits all SQL commands.
 */

public class EmployeeQueriesAuto {

    private static final String INSERT_SQL = "INSERT INTO employees (last_name, first_name, email, department, salary) VALUES (?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE employees SET email = ? WHERE first_name = ? AND last_name = ?";
    private static final String DELETE_SQL = "DELETE FROM employees WHERE email = ?";
    private static final String GET_ALL_EMPLOYEES = "SELECT * FROM employees ORDER BY last_name";
    private static final String GET_EMPLOYEE = "SELECT * FROM employees WHERE email = ?";
    private static final String UPDATE_RESUME = "UPDATE employees SET resume = ? WHERE email = ?";
    private static final String SELECT_RESUME = "SELECT resume FROM employees WHERE email = ?";
    private static final String UPDATE_RESUME_CLOB = "UPDATE employees SET resume_clob = ? WHERE email = ?";
    private static final String SELECT_RESUME_CLOB = "SELECT resume_clob FROM employees WHERE email = ?";
    public static void generalQuery(String sql) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        }
    }

    public static String insertNewEmployee(Employee employee) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getDepartment());
            preparedStatement.setFloat(5, employee.getSalary());
            preparedStatement.execute();

            return employee.getEmail();
        }
    }

    public static int updateEmployeeEmail(Employee employee, String newEmail) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            employee.setEmail(newEmail);
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            return preparedStatement.executeUpdate();
        }
    }

    public static void deleteEmployee(String email) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.execute();
            EmployeeQueriesAuto.printEmployee(email);
        }
    }

    public static void printAllEmployees() throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("email") + " ");
                System.out.print(resultSet.getString("department") + " ");
                System.out.println(resultSet.getString("salary") + " ");
            }
        }
    }

    public static void printEmployee(String email) throws SQLException {
        try(Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("no employee found for email: " + email);
                return;
            }
            do {
                String theLastName = resultSet.getString("last_name");
                String theFirstName = resultSet.getString("first_name");
                String theEmail = resultSet.getString("email");
            } while (resultSet.next());
        }
    }

    /** blob usage **/
    public static void addResumeToEmployee(String email, String pathToResume) throws SQLException, FileNotFoundException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESUME)) {
            preparedStatement.setString(1, pathToResume);
            preparedStatement.setString(2, email);
            File thefile = new File(pathToResume);
            FileInputStream input = new FileInputStream(thefile);

            preparedStatement.setBinaryStream(1, input);
            preparedStatement.execute();
        }
    }

    /**
     * Writes the employee resume blob to the output directory
     * @param email
     */
    public static void readEmployeeResumeAndWriteToFile(String email, String outputFileName) throws SQLException {
        ResultSet resultSet;

        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESUME)) {
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
            FileHelper.writeDBresultSetBlobToFile(resultSet, "resume", outputFileName);
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** clob usage **/
    public static void addResumeClobToEmployee(String email, String pathToResume) throws SQLException, FileNotFoundException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESUME_CLOB)) {
            preparedStatement.setString(1, pathToResume);
            preparedStatement.setString(2, email);
            File thefile = new File(pathToResume);
            FileReader input = new FileReader(thefile);

            preparedStatement.setCharacterStream(1, input);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Writes the employee resume clob to the output directory
     * @param email
     */
    public static void readEmployeeResumeClobAndWriteToFile(String email, String outputFileName) throws SQLException {
        ResultSet resultSet;

        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESUME_CLOB)) {
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            FileHelper.writeDBresultSetClobToFile(resultSet, "resume_clob", outputFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
