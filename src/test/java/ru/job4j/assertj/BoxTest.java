package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void whenVertices4() {
        Box box = new Box(4, 2);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4);
    }

    @Test
    void whenVertices0() {
        Box box = new Box(0, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(0);
    }

    @Test
    void whenIsExistTrue() {
        Box box = new Box(4, 2);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenGetArea6() {
        Box box = new Box(8, 1);
        double result = box.getArea();
        assertThat(result).isEqualTo(6);
    }

    @Test
    void whenGetArea16() {
        Box box = new Box(4, 1);
        double result = box.getArea();
        assertThat(result).isEqualTo(1.7320508075688772);
    }
}