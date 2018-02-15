# soyuz-is-to
Вы знаете как преобразовать в java String в Integer? Я пишу на java уже много лет, но понятия не имею, как это делать.
И я (не один такой)[https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java] (почти 5 000 000 просмотров о чем-то говорит).
Десятый или пятнадцатый раз гугля как преобразовать X в Y (речь, конечно, не только о String -> Integer), я решил что можно это упростить.

Хочу представить вам **новую библиотеку** (СТОП! Придержим помидоры еще пару минут), которая ну оооочень простая и ну очень полезная мне (как автору) ну и, надеюсь, вам.

# soyuz-is-to
Состоит библиотека из двух классов:
 1. is - truthy checks (`x != null && ...`) в стиле JavaScript / Groovy - не относится ко вступлению. Про
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

## How to use
### Maven
```
<dependency>
    <groupId>io.thedocs</groupId>
    <artifactId>soyuz-is-to</artifactId>
    <version>0.58</version>
</dependency>
```

### Gradle
```
repositories {
    mavenCentral()
}

dependencies {
    compile 'io.thedocs:soyuz-is-to:0.58'
}
```