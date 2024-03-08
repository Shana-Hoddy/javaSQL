package storedProcedures;

import main.database.DatabaseConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class GreetTheDept {

    private static final String GREET_THE_DEPT = "{call greet_the_department(?)}";

    /* demonstrates using a stored procedure OUT parameter */
    public static void greetTheDepartment(String department) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             CallableStatement callableStatement = connection.prepareCall(GREET_THE_DEPT)){
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(1, department);
            callableStatement.execute();

            //gets the value of the INOUT statement
            System.out.println(callableStatement.getString(1));
        }
    }

}
