package me.absolute.springcourse.benches;

import me.absolute.springcourse.App;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class HibernateBench {
    public static void runBench(List<String> queriesList) {
        long duration;
        long startTime;
        long endTime;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println("JDBI Test:");
        for(int req = 0; req < 4; req++) {
            long result = 0;
            for(int it = 0; it < App.TEST_COUNT; it++) {
                startTime = System.nanoTime();
                List<Object> query = session.createNativeQuery(queriesList.get(req)).list();
                endTime = System.nanoTime();
                duration = (endTime - startTime) / 1000000;
                result += duration;
            }
            System.out.println("Test #" + (req + 1) + " - " + result/App.TEST_COUNT + "ms");
        }
        sessionFactory.close();
    }
}
