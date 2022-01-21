package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "././/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithoutComment2() {
        String path = "././/add.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("animal"), is("animalPassword"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }
}