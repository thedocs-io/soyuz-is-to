# soyuz-is-to
This library serves two important features:
 1. is - truthy checks (`x != null && ...`) - very close to JavaScript
 2. to - a lot of useful functions to convert `X` to `Y` (e.g. `Set` to `List`)

## is
Is is very simple. It's just `!= null` check and additional logic (like not empty). Please read sources - it's only few lines of code.

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