package org.courses.jdbc1_3;

import java.sql.SQLException;

import static org.courses.jdbc1_3.DbConnector.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        showPhoneAddress();
        showPhoneBirthDate();
        showManagerPhoneBirthDate();
    }
}
