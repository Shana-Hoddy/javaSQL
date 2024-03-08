package storedProcedures;
import main.database.DatabaseConnectionManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
public class IncreaseSalariesForDept {
   private static final String INCREASE_SALARIES_FOR_DEPT = "{call increase_salaries_for_department(?,?)}";

   /* demonstrates using a stored procedure IN parameter */
    public static void increaseSalariesForDept(String department, Float amountToIncrease) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getDatabaseConnection();
             CallableStatement callableStatement = connection.prepareCall(INCREASE_SALARIES_FOR_DEPT)){
            callableStatement.setString(1, department);
            callableStatement.setFloat(2, amountToIncrease);
            callableStatement.execute();
        }
    }
}
