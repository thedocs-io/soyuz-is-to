package io.belov.soyuz.utils

import io.belov.soyuz.utils.to as To;

import spock.lang.Specification

import java.util.function.BiFunction

/**
 * Created by fbelov on 30.11.15.
 */
class ToSpec extends Specification {

    def "should return empty list/set"() {
        when:
        def s = To.set()
        def l = To.list()

        then:
        assert s instanceof Set
        assert s.size() == 0
        assert l instanceof List
        assert l.size() == 0
    }

    def "should convert to map"() {
        setup:
        def a

        when:
        a = To.map(1, "1")

        then:
        assert a == [(1): "1"]

        when:
        a = To.map(1, "1", 2, "2")

        then:
        assert a == [(1): "1", (2): "2"]

        when:
        a = To.map(1, "1", 2, "2", 3, "3")

        then:
        assert a == [(1): "1", (2): "2", (3): "3"]

        when:
        a = To.map(1, "1", 2, "2", 3, "3", 4, "4")

        then:
        assert a == [(1): "1", (2): "2", (3): "3", (4): "4"]

        when:
        a = To.map(1, "1", 2, "2", 3, "3", 4, "4", 5, "5")

        then:
        assert a == [(1): "1", (2): "2", (3): "3", (4): "4", (5): "5"]
    }

    def "should convert params to map"() {
        when:
        def a = To.map(1, "1", "2", 2, "3", 3, "4", 4, "5", 5, "6", 6)

        then:
        assert a == [(1): "1", "2": 2, "3": 3, "4": 4, "5": 5, "6": 6]
    }

    def "should convert map"() {
        when:
        def a = [hello: 1, world: 2]
        def b = To.map(a, { k, v ->
            return v*2
        } as BiFunction)

        then:
        assert b == [hello: 2, world: 4]
    }

    def "should use values from source map"() {
        when:
        def source = [hello: "world"]
        def a = To.map(source, "again", "=)")

        then:
        assert a == [hello: "world", again: "=)"]
        assert source == [hello: "world"]
    }

    def "shouldn't convert to map with odd params number"() {
        when:
        To.map("a")

        then:
        thrown(IllegalArgumentException)
    }

    def "should return log message"() {
        when:
        def doesNotMatter

        then:
        assert To.log("hello") == "[hello]"

        then:
        assert To.log("world", To.map("a", "b", "c", "d")) == "[world]{a=b, c=d}"
    }

    def "should construct string"() {
        when:
        def s = To.s("map {1} and int {0}", 5, [hello: "world"])

        then:
        assert s == "map {hello=world} and int 5"

        when:
        s = To.s("more {1} params", "abc", "efg", "qwe")

        then:
        assert s == "more efg params"

        when:
        s = To.s("less {1} params")

        then:
        assert s == "less {1} params"
    }

    def "should construct string from map"() {
        when:
        def s = To.s("map {a} and int {b}", [b: 5, a: [hello: "world"]])

        then:
        assert s == "map {hello=world} and int 5"

        when:
        s = To.s("more {b} params", [a: "abc", b: "efg", c: "qwe"])

        then:
        assert s == "more efg params"

        when:
        s = To.s("less {a} params {b}", [b: "efg", c: "qwe"])

        then:
        assert s == "less {a} params efg"
    }

    def "should join collection"() {
        when:
        def s = To.s([5, "hello", "world"], "::")

        then:
        assert s == "5::hello::world"
    }

    def "should convert array to list"() {
        when:
        def l = To.arr([1, 3, 2], Integer)

        then:
        assert l == [1, 3, 2] as Integer[]
    }
}
