package ru.job4j.serialization.json;

public class Relative {
    final String brothers;
    final String sisters;

    public Relative(String brothers, String sisters) {
        this.brothers = brothers;
        this.sisters = sisters;
    }

    public String getBrothers() {
        return brothers;
    }

    public String getSisters() {
        return sisters;
    }

    @Override
    public String toString() {
        return "{" + "brothers='" + brothers + '\''
                + ", sisters='" + sisters + '\''
                + '}';
    }
}
