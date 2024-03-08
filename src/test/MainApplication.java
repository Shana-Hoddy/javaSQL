package test;

import main.database.DatabaseSetup;

import java.io.FileNotFoundException;
import java.sql.*;

public class MainApplication {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseSetup.recreateEmployeeTable();


    }

}

