package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        byte b = 4;
        long l = 101029L;
        char c = 'f';
        short s = 16516;
        float f = 1.2f;
        double d = 342.5;
        boolean bul = true;
        LOG.debug("Test -> byte : {}, age : {}, long : {}"
                        + ", char : {}, short : {}, float : {}, double : {}"
                + ", boolean : {}", b, age, l, c, s, f, d, bul);
    }
}