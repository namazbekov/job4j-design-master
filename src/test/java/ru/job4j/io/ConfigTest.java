package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    private static Config config;
    private static String path;

    @BeforeClass
    public static void beforeClass() {
        config = new Config(path);
    }

    @Rule
    public ExpectedException expect = ExpectedException.none();

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithoutComment() {
        path = "./data/app.properties";
        config = new Config(path);
        config.load();
        assertThat(config.value("something"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithoutComment2() {
        String path = "./data/add.properties";
        config = new Config(path);
        config.load();
        config.value("oajdnvij");
    }

    @Test
    public void whenFileContainsException3() {
        String path = "./data/arr.properties";
        config = new Config(path);
        expect.expect(IllegalArgumentException.class);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenDontFindElement() {
        path = "./data/aff.properties";
        config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoLegalElement() {
        String path = "./data/acc.properties";
        Config config = new Config(path);
        config.load();
    }
}