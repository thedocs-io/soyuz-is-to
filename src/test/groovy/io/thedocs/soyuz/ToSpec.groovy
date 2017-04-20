package io.thedocs.soyuz

import io.thedocs.soyuz.to as To
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
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
            return v * 2
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

    def "should transform set to list"() {
        when:
        def a = [1, 3, 3, 2] as Set

        then:
        assert To.list(a) == [1, 3, 2]
    }

    def "should convert zonedDateTime <-> date"() {
        when:
        def localDate = LocalDate.of(2017, 02, 10)
        def localTime = LocalTime.of(12, 55, 32)
        def zonedDateTime = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault())
        def date = new Date(2017 - 1900, 02 - 1, 10, 12, 55, 32)

        then:
        assert To.date(zonedDateTime) == date
        assert To.zonedDateTime(date) == zonedDateTime
    }

    def "should convert to linked map"() {
        setup:
        def a

        when:
        a = To.linkedHashMap(1, "1")

        then:
        assert a == [(1): "1"]

        when:
        a = To.linkedHashMap(1, "1", 2, "2")

        then:
        assert a == [(1): "1", (2): "2"]
        assert a.keySet() == [1, 2] as LinkedHashSet

        when:
        a = To.linkedHashMap(1, "1", 2, "2", 3, "3")

        then:
        assert a == [(1): "1", (2): "2", (3): "3"]
        assert a.keySet() == [1, 2, 3] as LinkedHashSet

        when:
        a = To.linkedHashMap(1, "1", 2, "2", 3, "3", 4, "4")

        then:
        assert a == [(1): "1", (2): "2", (3): "3", (4): "4"]
        assert a.keySet() == [1, 2, 3, 4] as LinkedHashSet

        when:
        a = To.linkedHashMap(1, "1", 2, "2", 3, "3", 4, "4", 5, "5")

        then:
        assert a == [(1): "1", (2): "2", (3): "3", (4): "4", (5): "5"]
        assert a.keySet() == [1, 2, 3, 4, 5] as LinkedHashSet
    }

    def "should convert params to linked map"() {
        when:
        def a = To.linkedHashMap(1, "1", "2", 2, "3", 3, "4", 4, "5", 5, "6", 6)

        then:
        assert a == [(1): "1", "2": 2, "3": 3, "4": 4, "5": 5, "6": 6]
        assert a.keySet() == [1, "2", "3", "4", "5", "6"] as LinkedHashSet
    }

    def "should convert linked map"() {
        when:
        def a = [hello: 1, world: 2]
        def b = To.linkedHashMap(a, { k, v ->
            return v * 2
        } as BiFunction)

        then:
        assert b == [hello: 2, world: 4]
        assert a.keySet() == ["hello", "world"] as LinkedHashSet
    }

    def "should use values from source linked map"() {
        when:
        def source = [hello: "world"]
        def a = To.linkedHashMap(source, "again", "=)")

        then:
        assert a == [hello: "world", again: "=)"]
        assert source == [hello: "world"]
        assert a.keySet() == ["hello", "again"] as LinkedHashSet
    }

    def "shouldn't convert to linked map with odd params number"() {
        when:
        To.linkedHashMap("a")

        then:
        thrown(IllegalArgumentException)
    }
}
