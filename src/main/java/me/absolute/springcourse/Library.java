package me.absolute.springcourse;

public enum Library {
    Hibernate("Hibernate"),
    JDBC("JDBC"),
    JDBI("JDBI");

    Library(String type) {
        this.type = type;
    }
    private String type;

    public String getType() {
        return type;
    }
}
