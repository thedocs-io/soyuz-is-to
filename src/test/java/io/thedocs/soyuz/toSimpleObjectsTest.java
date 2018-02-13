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

}
