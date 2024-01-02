package org.courses.jdbc1_3;

import java.sql.*;
import java.util.Date;
import java.util.Objects;

public class DbConnector {
    private static Connection connection;
    public static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB?serverTimezone=CET";
    public static final String USER_NAME = "jdbc_user";
    public static final String PASSWORD = "1234567890";

    public static Connection getConnection() throws SQLException {
        if (Objects.isNull(connection)) {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
        return connection;
    }

    public static void showPhoneAddress() throws SQLException {

        String query = "SELECT e.employee_id, e.phone_number, p.country,\" \", p.city, \" \", p.street, \" \"," +
                        " p.house_number\n" +
                        "FROM Employee e\n" +
                        "INNER JOIN Personal_info p\n" +
                        "ON e.personal_id = p.personal_id;";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int employee_id = resultSet.getInt("employee_id");
                int phone_number = resultSet.getInt("phone_number");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                int house_number = resultSet.getInt("house_number");

                System.out.println(employee_id + "\t\t" + phone_number + "\t\t" + country + "\t\t" + city +
                                "\t\t" + street + "\t\t" + house_number);
            }
        }

        System.out.println("=========================================================================================");
    }

    public static void showPhoneBirthDate() throws SQLException {

        String query = "SELECT e.employee_id, e.phone_number, p.birth_date\n" +
                        "FROM Employee e\n" +
                        "INNER JOIN  Personal_info p\n" +
                        "ON e.personal_id = p.personal_id\n" +
                        "WHERE family_status = 1;";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int employee_id = resultSet.getInt("employee_id");
                int phone_number = resultSet.getInt("phone_number");
                Date birth_date = resultSet.getDate("birth_date");

                System.out.println(employee_id + "\t\t" + phone_number + "\t\t" + birth_date);
            }
        }
        System.out.println("=========================================================================================");
    }
    public static void showManagerPhoneBirthDate() throws SQLException {

        String query = "SELECT e.employee, pos.position,e.phone_number, p.birth_date\n" +
                        "FROM Position pos\n" +
                        "INNER JOIN Employee e\n" +
                        "ON e.position_id = pos.position_id\n" +
                        "INNER JOIN  Personal_info p\n" +
                        "ON e.personal_id = p.personal_id\n" +
                        "WHERE position = 'manager';\n";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int employee_id = resultSet.getInt("employee_id");
                int phone_number = resultSet.getInt("phone_number");
                Date birth_date = resultSet.getDate("birth_date");

                System.out.println(employee_id + "\t\t" + phone_number + "\t\t" + birth_date);
            }
        }
        System.out.println("=========================================================================================");
    }



    public static void main(String[] args) throws SQLException {
        showPhoneAddress();
        showPhoneBirthDate();
        showManagerPhoneBirthDate();
    }
}