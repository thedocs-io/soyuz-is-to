# soyuz-is-to
This library serves two important features:
 1. is - truthy checks (`x != null && ...`) - very close to JavaScript
 2. to - a lot of useful functions to convert `X` to `Y` (e.g. `Set` to `List`)

## is
Let's begin with simple things. Class `is` provides you an ability to make `!= null` check + additional logic (like not empty). `is.t(object)` will return `true` in following cases:
```
object type                             | condition
string                                  | != ""
number (Integer, Long, BigDecimal, etc) | != 0
collection / map                        | not empty
file                                    | exist
```
It requires only 15-30 seconds to understand it's logic (is it hard for you to read 100 lines of simple code?) but it's very useful in practice.


## to
Do you know how to convert `String` to `Long`? Well... it's easy. What about `Date` to `LocalDate`? Or `array` to `stream`?
Most of the time we convert data from `X` type to `Y`. `soyuz-is-to` simplifies your life:

1. Simple way to convert `X` to `Y`
2. Simple way to init `Map`, `List`, `Set`
3. \+ a little bit functional programming...

## to intro
Basic rule: `to.$requredType($sourceType)`. E.g. we want to convert `java.util.Date now` to `java.time.LocalDate`:
```
LocalDate date = to.localDate(now)
```

## to init

## to convert

## to functional

## How to use
### Maven
```
<dependency>
    <groupId>io.thedocs</groupId>
    <artifactId>soyuz-is-to</artifactId>
    <version>0.72</version>
</dependency>
```

### Gradle
```
repositories {
    mavenCentral()
}

dependencies {
    compile 'io.thedocs:soyuz-is-to:0.72'
}
```