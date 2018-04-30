package io.thedocs.soyuz;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class toListTest {

    @Test
    public void shouldReturnNullForNull() {
        assertNull(to.list((Object[]) null));
        assertNull(to.list((Object) null));
        assertNull(to.list((Iterator<Object>) null));
    }

    @Test
    public void shouldCreateEmptyList() {
        List<String> a = new ArrayList<>();
        List<String> b = to.list();

        assertEquals(a, b);
    }

    @Test
    public void shouldCreateListWithSingleValue() {
        List<String> a = Arrays.asList("a");
        List<String> b = to.list("a");

        assertEquals(a, b);
    }

    @Test
    public void shouldCreateListWithMultipleValues() {
        List<String> a = Arrays.asList("a", "b", "c");
        List<String> b = to.list("a", "b", "c");

        assertEquals(a, b);
    }

    @Test
    public void shouldTransformIteratorToList() {
        List<String> list = Arrays.asList("1", "2");
        Iterator<String> iterator = list.iterator();

        assertEquals(list, to.list(iterator));
    }

    @Test
    public void shouldApplyMapperToListAndTransformValues() {
        List<Integer> source = to.list(1, 2, 3);
        List<Integer> expected = to.list(2, 4, 6);

        assertEquals(expected, to.list(source, i -> i * 2));
    }

    @Test
    public void shouldApplyMapperToMapAndTransformValues() {
        Map<String, Integer> source = to.map("a", 1, "b", 2);
        List<Integer> expected = to.list(3, 10);

        assertEquals(expected, to.list(source, (k, v) -> {
            if ("a".equals(k)) {
                return v * 3;
            } else {
                return v * 5;
            }
        }));
    }
}