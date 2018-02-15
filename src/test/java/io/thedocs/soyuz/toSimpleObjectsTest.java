package io.thedocs.soyuz;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created on 13.02.18.
 */
public class toSimpleObjectsTest {

    @Test
    public void shouldInjectParamsFromMap() {
        Map<String, String> params = to.map("name", "Alexander");
        String expected = "My name is Alexander";

        assertEquals(expected, to.s("My name is {{name}}", params));
    }

    @Test
    public void shouldBoxSimpleObjects() {
        assertEquals(to.Integer(1), Integer.valueOf(1));
        assertEquals(to.Float(1.3f), Float.valueOf(1.3f));
        assertEquals(to.Double(0.5d), Double.valueOf(0.5d));
        assertEquals(to.Long(2333L), Long.valueOf(2333L));
        assertEquals(to.Boolean(true), Boolean.valueOf(true));
        assertEquals(to.Boolean(false), Boolean.valueOf(false));
    }

}
