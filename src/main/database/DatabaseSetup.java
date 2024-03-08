package main.database;

import java.sql.Connection;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;

public class DatabaseSetup {

    public static void recreateEmployeeTable() {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             Statement statement = connection.createStatement()) {
             String sqlScriptPath = "src/main/resources/setupEmployeeTable.sql";
             executeSqlScript(sqlScriptPath, statement);
        } catch (Exception e) {
            System.out.println("Unable to setup employee table");
            throw new RuntimeException(e);
        }
    }

    private static void executeSqlScript(String scriptPath, Statement statement) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
            StringBuilder script = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                script.append(line).append("\n");
            }

            String[] sqlCommands = script.toString().split(";");

            for (String sqlCommand : sqlCommands) {
                if (!sqlCommand.trim().isEmpty()) {
                    statement.addBatch(sqlCommand);
                }
            }

            statement.executeBatch();
        }
    }

}



