package Model;

import java.sql.*;

/**
 * class Database | connect to database, create tables, insert....
 */

public class Database {

    private String url = "jdbc:sqlite:"+System.getenv("homepath")+"/Minesweeper.db";

    public void connect() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(url);

            System.out.println("Connection succeded! | Created database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void disconnect() throws SQLException {
        DriverManager.getConnection(url).close();
    }

    public void createTable() {

        String tableCreation = "Create table if not exists Minesweeper ( \n"
                + "id integer primary key autoincrement,\n"
                + "username nvarchar(200),\n"
                + "winningtime nvarchar,\n"
                + "fieldsize integer\n"
                + ");";

        System.out.println("Table Minesweeper successfully created!");

        try (Connection connection = DriverManager.getConnection(url);
             Statement stmt = connection.createStatement()) {
            stmt.execute(tableCreation);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String username, String time, Integer fieldsize) {

        String sql = "INSERT INTO Minesweeper(username, winningtime, fieldsize)\n"
                + "VALUES ('"+username+"', '"+time+"', '"+fieldsize+"');";

        System.out.println("Successfully inserted data in database!");

        try (Connection connection = DriverManager.getConnection(url);
             Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String update) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement stmt = connection.createStatement()) {
            stmt.execute(update);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sqlStatement(String sql) throws SQLException {
        DriverManager.getConnection(url).close();
        try (Connection connection = DriverManager.getConnection(url);
             Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public ResultSet select(String sql) {
        ResultSet rs;
        try {
            Connection connection = connection();
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            rs=null;
        }
        return rs;
    }

}