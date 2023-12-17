package me.absolute.springcourse.benches;

import me.absolute.springcourse.App;

import java.sql.*;
import java.util.List;

public class JDBCbench {
    public static void runBench(List<String> queriesList) throws SQLException {
        ResultSet results = null;
        long duration;
        long startTime;
        long endTime;
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        Statement statement = connection.createStatement();
        System.out.println("JDBC Test:");
        for(int req = 0; req < 4; req++) {
            long result = 0;
            for(int it = 0; it < App.TEST_COUNT; it++) {
                startTime = System.nanoTime();
                statement.executeQuery(queriesList.get(req));
                endTime = System.nanoTime();
                duration = (endTime - startTime) / 1000000;
                result += duration;
            }
            System.out.println("Test #" + (req + 1) + " - " + result/App.TEST_COUNT + "ms");
        }
    }
}
