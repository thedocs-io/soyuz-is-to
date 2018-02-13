package io.thedocs.soyuz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class isTest {

    @Test
    public void testIsBoolean() {
        assertTrue(is.t(true));
        assertFalse(is.t((Boolean) null));
        assertFalse(is.t((false)));
    }

    @Test
    public void testIsTruthyCastable() {
        assertTrue(is.t(new ObjectId(55)));
        assertFalse(is.t(new ObjectId(0)));
        assertFalse(is.t((ObjectId) null));
    }

    @Test
    public void testIsBigDecimal() {
        assertTrue(is.t(new BigDecimal(1)));
        assertTrue(is.t(new BigDecimal(0.1)));
        assertFalse(is.t(new BigDecimal(0.0)));
        assertFalse(is.t(new BigDecimal(0)));
        assertFalse(is.t(new BigDecimal(-0.0)));
        assertFalse(is.t((BigDecimal) null));
    }

    @AllArgsConstructor
    @Getter
    private static class ObjectId implements TruthyCastableI {
        private int id;

        @Override
        public boolean asTruthy() {
            return id != 0;
        }
    }
}