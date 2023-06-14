package ru.job4j.assertj;

import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Tetrahedron")
                .contains("ph")
                .isNotBlank();
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .contains("Tetra")
                .containsIgnoringCase("tetrahedron")
                .isNotEmpty();
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .containsIgnoringCase("CUBE")
                .doesNotContain("abcd")
                .startsWith("C");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(16, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .doesNotContain("abcd")
                .endsWith(" object")
                .startsWithIgnoringCase("UN");
    }

    @Test
    void numberOfVerticesIfSphere() {
        Box box = new Box(0, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(0)
                .isEqualTo(0L)
                .isEven()
                .isNotNegative()
                .isBetween(-8, 8);
    }

    @Test
    void numberOfVerticesIfTetrahedron() {
        Box box = new Box(4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4)
                .isEqualTo(Integer.valueOf(4))
                .isGreaterThan(2)
                .isPositive()
                .isNotZero();
    }

    @Test
    void numberOfVerticesIfCube() {
        Box box = new Box(8, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8)
                .isNotEqualTo(88)
                .isLessThan(9)
                .isPositive()
                .isNotZero();
    }

    @Test
    void numberOfVerticesIfUnknownObject() {
        Box box = new Box(-1, 10);
        int number = box.getNumberOfVertices();
        String name = box.whatsThis();
        assertThat(number).isEqualTo(-1)
                .isNotEqualTo(Integer.valueOf(-11))
                .isNegative()
                .isLessThanOrEqualTo(Integer.valueOf(5))
                .isCloseTo(0, Offset.offset(1));
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isThisFigureExist() {
        Box box = new Box(4, 10);
        Boolean rsl = box.isExist();
        String name = box.whatsThis();
        assertThat(rsl).isEqualTo(true)
                .isNotEqualTo(false)
                .isTrue();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisFigureNoExist() {
        Box box = new Box(-1, 10);
        Boolean rsl = box.isExist();
        assertThat(rsl).isEqualTo(false)
                .isNotEqualTo(true)
                .isFalse();
    }

    @Test
    void isThisFigureNoExist2() {
        Box box = new Box(4, 0);
        Boolean rsl = box.isExist();
        assertThat(rsl).isEqualTo(false)
                .isNotEqualTo(true)
                .isFalse();
    }

    @Test
    void areaIfSphere() {
        Box box = new Box(0, 10);
        double area = Math.floor(box.getArea());
        assertThat(area).isEqualTo(1256.0d)
                .isCloseTo(1258.0d, withPrecision(2.0d))
                .isCloseTo(1258.0d, Percentage.withPercentage(0.2d))
                .isFinite()
                .isNotNull();
    }

    @Test
    void areaIfTetrahedron() {
        Box box = new Box(4, 6);
        double area = Math.floor(box.getArea());
        assertThat(area).isEqualTo(62.35d, withPrecision(0.35d))
                .isGreaterThan(61.35d)
                .isLessThan(63.35d)
                .isPositive()
                .isNotCloseTo(162.35d, Percentage.withPercentage(0.2d));
    }

    @Test
    void areaIfCube() {
        Box box = new Box(8, 8);
        double area = Math.floor(box.getArea());
        assertThat(area).isEqualTo(384.0d)
                .isLessThanOrEqualTo(384.0d)
                .isGreaterThanOrEqualTo(384.0d)
                .isBetween(383.0d, 385.0d)
                .isNotNaN()
                .isNotCloseTo(162.35d, withPrecision(0.35d));
    }
}