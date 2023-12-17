package me.absolute.springcourse;

import me.absolute.springcourse.benches.HibernateBench;
import me.absolute.springcourse.benches.JDBCbench;
import me.absolute.springcourse.benches.JDBIbench;
import me.absolute.springcourse.Library;
import me.absolute.springcourse.manager.BenchConfig;
import me.absolute.springcourse.manager.JsonManager;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

/**ц
 * Hello world!
 *
 */
public class App 
{
    private static BenchConfig config = JsonManager.getBenchConfig();
    public static int TEST_COUNT = config.getTestCount();

    private static List<Library> libraries = config.getLibraries();




    public static void main( String[] args ) throws SQLException, IOException {
        System.out.println("Test counts: " + TEST_COUNT);


        List<String> queriesList = List.of("SELECT passenger_count, avg(total_amount) FROM postgres.public.taxi GROUP BY 1;",
                "SELECT passenger_count, avg(total_amount) FROM postgres.public.taxi GROUP BY 1;",
                "SELECT passenger_count, extract(year from tpep_pickup_datetime), count(*) FROM postgres.public.taxi GROUP BY 1, 2;",
                "SELECT passenger_count, extract(year from tpep_pickup_datetime), round(trip_distance), count(*) FROM postgres.public.taxi GROUP BY 1, 2, 3 ORDER BY 2, 4 desc;"
        );
        if(libraries.contains(Library.JDBC)) {
            JDBCbench.runBench(queriesList);
        }
        if(libraries.contains(Library.JDBI)) {
            JDBIbench.runBench(queriesList);
        }
        if(libraries.contains(Library.Hibernate)) {
            HibernateBench.runBench(queriesList);
        }
    }
}

