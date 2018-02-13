package io.thedocs.soyuz;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class toArrayTest {

    @Test
    public void shouldTransformListToArray() {
        List<String> source = to.list("hello", "world");
        String[] expected = new String[]{"hello", "world"};

        assertArrayEquals(expected, to.arr(source, String.class));
    }

}