package me.absolute.springcourse.manager;

import me.absolute.springcourse.Library;

import java.util.List;

public class BenchConfig {
    private int testCount;
    private List<Library> libraries;

    public int getTestCount() {
        return testCount;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public BenchConfig(int testCount, List<Library> libraries) {
        this.testCount = testCount;
        this.libraries = libraries;
    }
}
