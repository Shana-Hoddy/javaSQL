package test.databaseTests;

import main.database.DatabaseConnectionManager;
import main.database.DatabaseSetup;
import main.database.EmployeeQueriesManual;
import main.utils.UserInputHandler;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTransactionTests {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseSetup.recreateEmployeeTable();
        runJdbcTransactions();
    }
    public static void runJdbcTransactions() throws SQLException {
        Connection connection = null;
        connection = DatabaseConnectionManager.getDatabaseConnection();
        EmployeeQueriesManual.deleteAllEmployeesInDepartment(connection, "HR");
        EmployeeQueriesManual.updateAllSalariesByDepartment(connection, "engineering", 500000.00f);

        boolean okToSave = new UserInputHandler().askUserIfOkToSave();
        if(okToSave)
            connection.commit();
        else
            connection.rollback();
        DatabaseConnectionManager.closeConnection(connection);
    }

}
