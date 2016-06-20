package io.belov.soyuz.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by fbelov on 20.06.16.
 */
public class toTest {

    @Test
    public void testListToArray() {
        List<String> source = to.list("hello", "world");
        String[] expected = new String[]{"hello", "world"};

        assertArrayEquals(expected, to.arr(source, String.class));
    }

}