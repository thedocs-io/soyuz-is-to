package io.thedocs.soyuz;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class toSetTest {

    @Test
    public void shouldCreateEmptySet() {
        Set<String> a = new HashSet<>();
        Set<String> b = to.set();

        assertEquals(a, b);
    }

    @Test
    public void shouldCreateSetWithSingleValue() {
        Set<String> a = Sets.newHashSet("a");
        Set<String> b = to.set("a");

        assertEquals(a, b);
    }

    @Test
    public void shouldCreateSetWithMultipleValues() {
        Set<String> a = Sets.newHashSet("a", "b", "c");
        Set<String> b = to.set("a", "b", "c");

        assertEquals(a, b);
    }

    @Test
    public void shouldTransformIteratorToSet() {
        Set<String> set = Sets.newHashSet("1", "2");
        Iterator<String> iterator = set.iterator();

        assertEquals(set, to.set(iterator));
    }

    @Test
    public void shouldApplyMapperToSetAndTransformValues() {
        Set<Integer> source = to.set(1, 2, 3);
        Set<Integer> expected = to.set(2, 4, 6);

        assertEquals(expected, to.set(source, i -> i * 2));
    }

    @Test
    public void shouldApplyMapperToMapAndTransformValues() {
        Map<String, Integer> source = to.map("a", 1, "b", 2);
        Set<Integer> expected = to.set(3, 10);

        assertEquals(expected, to.set(source, (k, v) -> {
            if ("a".equals(k)) {
                return v * 3;
            } else {
                return v * 5;
            }
        }));
    }
}