package me.absolute.springcourse.benches;

import me.absolute.springcourse.App;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBIbench {
    public static void  runBench(List<String> queriesList) {

        ResultSet results = null;
        long duration;
        long startTime;
        long endTime;
        Jdbi jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

        Handle handle = null;
        try {
            handle = jdbi.open();
            System.out.println("JDBI Test:");
            for(int req = 0; req < 4; req++) {
                long result = 0;
                for(int it = 0; it < App.TEST_COUNT; it++) {
                    startTime = System.nanoTime();
                    handle.createQuery(queriesList.get(req)).mapToMap().list();
                    endTime = System.nanoTime();
                    duration = (endTime - startTime) / 1000000;
                    result += duration;
                }
                System.out.println("Test #" + (req + 1) + " - " + result/App.TEST_COUNT + "ms");
            }
        }
        finally {
            if (handle != null) {
                handle.close();
            }
        }

    }


}
