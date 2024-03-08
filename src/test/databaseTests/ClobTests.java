package test.databaseTests;

import main.database.DatabaseSetup;
import main.database.EmployeeQueriesAuto;
import main.utils.GetRandomNumber;
import main.utils.PropertiesHelper;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class ClobTests {


    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseSetup.recreateEmployeeTable();
        clobTest();
    }

    /**
     * Reads and writes a LONG_TEXT file to the database (large text documents or xml up to 4GB of data)
     * @param
     * @throws SQLException
     * @throws FileNotFoundException
     */
    public static void clobTest() throws SQLException, FileNotFoundException {
        String email = "mary.public@foo.com.pdf";
        String docsPath = PropertiesHelper.getProperties().getProperty("file.docs.location");
        EmployeeQueriesAuto.addResumeClobToEmployee(email, docsPath + "sample_resume.txt");
        String outputFileName = email + "_" + GetRandomNumber.getRandomNumber(5) + ".txt";
        EmployeeQueriesAuto.readEmployeeResumeClobAndWriteToFile(email, outputFileName);
    }


}
