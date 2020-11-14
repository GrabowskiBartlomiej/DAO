package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String DBlink = "jdbc:mysql://localhost:3306/workshop2?useSSL=false&characterEncoding=utf8";
    private static final String DBuser = "root";
    private static final String DBpass = "coderslab";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DBlink,DBuser,DBpass);
    }
}
