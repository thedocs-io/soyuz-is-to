package io.thedocs.soyuz;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created on 13.02.18.
 */
public class toFuncTest {

    @Test
    public void shouldReturnNullForNullObject() {
        Car car = null;
        String expected = null;

        assertEquals(expected, to.nullOr(car, Car::getTitle));
    }

    @Test
    public void shouldReturnTitleForNonNullObject() {
        Car car = new Car(1, "Lada");
        String expected = "Lada";

        assertEquals(expected, to.nullOr(car, Car::getTitle));
    }

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    private static class Car {
        private int id;
        private String title;
        private Integer power;

        public Car(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }

}
