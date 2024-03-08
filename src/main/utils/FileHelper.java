package main.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class FileHelper {

    /**
     *
     * Reads in a JDBC result containing a blob set to a file.
     * @param resultSet
     * @param columnName - name of the database column (blob) we are reading from
     * @param outputFileName - name of output file
     * @throws IOException
     * @throws SQLException
     */
    public static void writeDBresultSetBlobToFile(ResultSet resultSet, String columnName, String outputFileName)
            throws IOException, SQLException {

        String fileOutputLocation = PropertiesHelper.getProperties().getProperty("file.output.location");
        File file = new File(fileOutputLocation + outputFileName);
        createDirectoryIfNotExists(fileOutputLocation);
        FileOutputStream outputStream = new FileOutputStream(file);

        if (resultSet.next()){
            InputStream input = resultSet.getBinaryStream(columnName);
            byte[] buffer = new byte[1024];
            while(input.read(buffer) > 0) {
                outputStream.write(buffer);
            }
        }
    }

    /**
     * write LONGTEXT (text file or xml to local directory)
     * @param resultSet
     * @param columnName
     * @param outputFileName
     * @throws IOException
     * @throws SQLException
     */
    public static void writeDBresultSetClobToFile(ResultSet resultSet, String columnName, String outputFileName)
            throws IOException, SQLException {

        String fileOutputLocation = PropertiesHelper.getProperties().getProperty("file.output.location");
        File file = new File(fileOutputLocation + outputFileName);
        createDirectoryIfNotExists(fileOutputLocation);
        FileWriter output = new FileWriter(file);

        if (resultSet.next()){
            Reader input = resultSet.getCharacterStream(columnName);
            int theChar;

            while((theChar = input.read()) > 0){
                output.write(theChar);
            }
        }
    }

    private static void createDirectoryIfNotExists(String directoryPath) throws IOException {
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
            System.out.println("Directory created: " + directoryPath);
        }
    }
}
