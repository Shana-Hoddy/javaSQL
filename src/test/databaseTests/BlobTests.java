package test.databaseTests;

import main.database.DatabaseSetup;
import main.database.EmployeeQueriesAuto;
import main.utils.GetRandomNumber;
import main.utils.PropertiesHelper;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class BlobTests {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseSetup.recreateEmployeeTable();
        blobTest();
    }

    /**
     * Writes a Blob resume to the database (binary file), then reads the file and outputs it to the temp directory.
     * @throws SQLException
     * @throws FileNotFoundException
     */
    public static void blobTest() throws SQLException, FileNotFoundException {
        String email = "mary.public@foo.com.pdf";
        String docsPath = PropertiesHelper.getProperties().getProperty("file.docs.location");
        EmployeeQueriesAuto.addResumeToEmployee(email, docsPath + "sample_resume.pdf");
        String outputFileName = email + "_" + GetRandomNumber.getRandomNumber(5) + ".pdf";
        EmployeeQueriesAuto.readEmployeeResumeAndWriteToFile(email, outputFileName);
    }



}
