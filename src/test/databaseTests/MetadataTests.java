package test.databaseTests;

import main.database.DatabaseConnectionManager;
import main.database.DatabaseSetup;

import java.io.FileNotFoundException;
import java.sql.*;

public class MetadataTests {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseSetup.recreateEmployeeTable();
        getDBmetaData();

    }

    public static void getDBmetaData() throws SQLException {
        Connection connection = DatabaseConnectionManager.getDatabaseConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("metadata product name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("metadata driver name: " + databaseMetaData.getDriverName());
        System.out.println("metadata driver version: " + databaseMetaData.getDriverVersion());

        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;
        String[] types = null;

        //get table names
        ResultSet resultSetTables = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);

        while (resultSetTables.next()) {
            System.out.println(resultSetTables.getString("TABLE_NAME"));
        }

        //get column names
        ResultSet resultSet = databaseMetaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("COLUMN_NAME"));
        }

        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery("select id, last_name, first_name, salary from employees");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println("column count is: " + columnCount);
        for (int column = 1; column <= columnCount; column++) {
            System.out.print("column name: " + resultSetMetaData.getCatalogName(column));
            System.out.print(", column type name: " + resultSetMetaData.getColumnTypeName(column));
            System.out.print(", is nullable " + resultSetMetaData.isNullable(column));
            System.out.println(", is auto increment : " + resultSetMetaData.isAutoIncrement(column));
        }
        DatabaseConnectionManager.closeConnection(connection);
    }

}
