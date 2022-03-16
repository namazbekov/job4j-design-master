package ru.job4j.serialization.json;

public class Relative {
    final String brothers;
    final String sisters;

    public Relative(String brothers, String sisters) {
        this.brothers = brothers;
        this.sisters = sisters;
    }

    @Override
    public String toString() {
        return "{" + "brothers='" + brothers + '\''
                + ", sisters='" + sisters + '\''
                + '}';
    }
}
