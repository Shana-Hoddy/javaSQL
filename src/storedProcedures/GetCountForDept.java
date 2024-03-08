package storedProcedures;

import main.database.DatabaseConnectionManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class GetCountForDept {

    /* demonstrates using a stored procedure OUT parameter */
    public static int getCountForDepartment(String department) throws SQLException {
        final String GET_COUNT_FOR_DEPT = "{call get_count_for_department(?, ?)}";

        try(Connection connection = DatabaseConnectionManager.getDatabaseConnection();
            CallableStatement callableStatement = connection.prepareCall(GET_COUNT_FOR_DEPT)){
            callableStatement.setString(1, department);
            callableStatement.registerOutParameter(2, Types.INTEGER );
            callableStatement.execute();
            return callableStatement.getInt(2);
        }
    }
}
