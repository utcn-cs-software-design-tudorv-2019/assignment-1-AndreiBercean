package dataAccessLayer;

import java.sql.*;

public class ConnectionFactory 
{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    //private static final String PASS = "";
    private static Connection connection = null;

    @SuppressWarnings("unused")
	private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory()
    {
        try {
            Class.forName(DRIVER).newInstance();
            connection = createConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Connection createConnection()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DBURL + "?user=" + USER + "&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection()
    {
        return connection;
    }

    public static void close(Connection connection)
    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement statement)
    {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet resultSet)
    {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}