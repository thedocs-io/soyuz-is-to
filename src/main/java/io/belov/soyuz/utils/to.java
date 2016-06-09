/*
 * to
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package io.belov.soyuz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.text.MessageFormat;
import java.time.*;
import java.util.*;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

public class to {

    private static final Logger log = LoggerFactory.getLogger(to.class);

    public static Integer integer(Object val, Integer defaultValue) {
        return integer(val, defaultValue, true);
    }

    public static Integer integer(Object val, Integer defaultValue, boolean silent) {
        if (val != null) {
            try {
                return doIntConvert(val);
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to int", e);
            }
        }

        return defaultValue;
    }

    public static Integer integer(Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof Integer) {
            return (Integer) val;
        } else {
            return integer(val.toString(), null);
        }
    }

    public static Integer integer(String val) {
        return integer(val, null);
    }

    public static Long l(Object val, Long defaultValue) {
        return l(val, defaultValue, true);
    }

    public static Long l(Object val, Long defaultValue, boolean silent) {
        if (val != null) {
            try {
                return doLongConvert(val);
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to long", e);
            }
        }

        return defaultValue;
    }

    public static Long l(Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof Long) {
            return (Long) val;
        } else {
            return l(val.toString(), null);
        }
    }

    public static Long l(String val) {
        return l(val, null);
    }

    public static Boolean b(String val) {
        return b(val, null);
    }

    public static Boolean b(String val, Boolean defaultValue) {
        return b(val, defaultValue, true);
    }

    public static Boolean b(Object val, Boolean defaultValue, boolean silent) {
        if (val != null) {
            try {
                return doBooleanConvert(val);
            } catch (Exception e) {
                if (!silent) log.warn("Cannot convert " + val + " to boolean", e);
            }
        }

        return defaultValue;
    }

    public static URI uri(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static Object[] arr(Object... objects) {
        return objects;
    }

    public static <V> Object[] arr(Collection<V> objects, Function<V, Object> mapper) {
        int i = 0;
        int size = objects.size();
        Object[] answer = new Object[size];

        for (V value : objects) {
            answer[i] = mapper.apply(value);
            i++;
        }

        return answer;
    }

    public static String[] arrOfStrings(Collection<String> collection) {
        return collection.stream().toArray(String[]::new);
    }

    public static Integer[] arrOfIntegers(Collection<Integer> collection) {
        return collection.stream().toArray(Integer[]::new);
    }

    public static Long[] arrOfLongs(Collection<? extends Number> collection) {
        return collection.stream().toArray(Long[]::new);
    }

    public static int[] arrOfSimpleInts(Collection<Integer> collection) {
        int i = 0;
        int size = collection.size();
        int[] answer = new int[size];

        for (Integer value : collection) {
            answer[i] = value;
            i++;
        }

        return answer;
    }

    public static String s(String s, Object... params) {
        return MessageFormat.format(s, params);
    }

    public static String s(String s, Map<String, ?> params) {
        return s; //todo
    }

    public static String s(Iterable iterable, String separator) {
        Iterator iterator = iterable.iterator();
        StringBuilder sb = new StringBuilder();

        if (iterator.hasNext()) {
            sb.append(iterator.next());
            while (iterator.hasNext()) {
                sb.append(separator).append(iterator.next());
            }
        }

        return sb.toString();
    }

    public static String log(String event) {
        return log(event, null);
    }

    public static String log(String event, Map params) {
        String answer = "[" + event + "]";

        if (params != null) {
            answer += params;
        }

        return answer;
    }

    public static <K, V> Map<K, V> map(K k1, V v1) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);

        return a;
    }

    public static <K, V> Map<K, V> map(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> a = new HashMap<K, V>();

        a.put(k1, v1);
        a.put(k2, v2);
        a.put(k3, v3);
        a.put(k4, v4);
        a.put(k5, v5);

        return a;
    }

    public static Map<String, String> map(String... params) {
        return fillMapWithParams(new HashMap<String, String>(), params);
    }

    public static Map<String, String> map(Map<String, String> source, String... params) {
        return fillMapWithParams(new HashMap<>(source), params);
    }

    public static Map map(Object... params) {
        return fillMapWithParams(new HashMap(), params);
    }

    public static Map map(Map source, Object... params) {
        return fillMapWithParams(new HashMap(source), params);
    }

    private static <K, V> Map<K, V> fillMapWithParams(Map map, Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("Params number should be even");
        }

        for (int i = 0; i < params.length / 2; i++) {
            map.put(params[i * 2], params[i * 2 + 1]);
        }

        return map;
    }

    public static <T> List<T> list(T value) {
        List<T> answer = new ArrayList<T>();
        answer.add(value);
        return answer;
    }

    public static <T> List<T> list(Iterator<T> iterator) {
        return list(iterator, 10);
    }

    /**
     * @see org.apache.commons.collections.IteratorUtils.toList
     */
    public static <T> List<T> list(Iterator<T> iterator, int estimatedSize) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (estimatedSize < 1) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        } else {
            ArrayList<T> list = new ArrayList<T>(estimatedSize);

            while (iterator.hasNext()) {
                list.add(iterator.next());
            }

            return list;
        }
    }

    public static <T> List<T> list(T... value) {
        List<T> answer = new ArrayList<T>();
        Collections.addAll(answer, value);
        return answer;
    }

    public static <T, V> List<T> list(Collection<V> values, Function<V, T> mapper) {
        List<T> answer = new ArrayList<T>(values.size());

        for (V value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }

    public static <K, V, R> List<R> list(Map<K, V> map, BiFunction<K, V, R> mapper) {
        List<R> answer = new ArrayList<>(map.values().size());

        for (Map.Entry<K, V> e : map.entrySet()) {
            answer.add(mapper.apply(e.getKey(), e.getValue()));
        }

        return answer;
    }

    public static <T> Set<T> set(T value) {
        Set<T> answer = new HashSet<T>();
        answer.add(value);
        return answer;
    }

    public static <T> Set<T> set(T... value) {
        Set<T> answer = new HashSet<T>();
        Collections.addAll(answer, value);
        return answer;
    }

    public static <V> Set<V> set(Collection<V> values) {
        if (values instanceof Set) {
            return (Set<V>) values;
        } else {
            Set<V> answer = new HashSet<>();

            answer.addAll(values);

            return answer;
        }
    }

    public static <T, V> Set<T> set(Collection<V> values, Function<V, T> mapper) {
        Set<T> answer = new HashSet<T>();

        for (V value : values) {
            answer.add(mapper.apply(value));
        }

        return answer;
    }

    public static <T> SortedSet<T> sortedSet(T value) {
        SortedSet<T> answer = new TreeSet<T>();
        answer.add(value);
        return answer;
    }

    public static <T> SortedSet<T> sortedSet(T... value) {
        SortedSet<T> answer = new TreeSet<T>();
        Collections.addAll(answer, value);
        return answer;
    }

    public static Date date(java.sql.Date dateSql) {
        return new Date(dateSql.getTime());
    }

    public static Date date(LocalDate localDate) {
        //http://stackoverflow.com/a/22929420/716027
        return date(localDate, ZoneId.systemDefault());
    }

    public static Date date(LocalDate localDate, ZoneId zone) {
        return date(localDate.atStartOfDay(zone));
    }

    public static Date date(LocalDateTime localDateTime) {
        //http://blog.progs.be/542/date-to-java-time
        return date(localDateTime, ZoneId.systemDefault());
    }

    public static Date date(LocalDateTime localDateTime, ZoneId zone) {
        return date(localDateTime.atZone(zone));
    }

    public static Date date(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static java.sql.Date dateSql(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static LocalDateTime localDateTime(long millis) {
        //http://blog.progs.be/542/date-to-java-time
        return localDateTime(millis, ZoneId.systemDefault());
    }

    public static LocalDateTime localDateTime(long millis, ZoneId zone) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
    }

    public static LocalDateTime localDateTime(Date date) {
        //http://blog.progs.be/542/date-to-java-time
        return localDateTime(date.getTime());
    }

    public static LocalDate localDate(long millis) {
        return localDateTime(millis).toLocalDate();
    }

    public static LocalTime localTime(long millis) {
        return localDateTime(millis).toLocalTime();
    }

    public static <T> T nullOr(Object o, Supplier<T> supplier) {
        return (o == null) ? null : supplier.get();
    }

    public static <T> T or(T o, Supplier<T> supplier) {
        return (o != null) ? o : supplier.get();
    }

    public static <T> T or(T o, T otherwise) {
        return (o != null) ? o : otherwise;
    }

    public static <T> Stream<T> stream(T[] array) {
        return Arrays.stream(array);
    }

    //http://stackoverflow.com/a/24511534
    public static <T> Stream<T> stream(final Iterator<T> iterator) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static IntStream stream(int[] array) {
        return Arrays.stream(array);
    }

    public static LongStream stream(long[] array) {
        return Arrays.stream(array);
    }

    public static DoubleStream stream(double[] array) {
        return Arrays.stream(array);
    }

    public static Thread daemon(Runnable runnable) {
        return daemon(null, runnable);
    }

    public static Thread daemon(String threadName, Runnable runnable) {
        Thread t = (is.t(threadName)) ? new Thread(runnable, threadName) : new Thread(runnable);
        t.setDaemon(true);

        return t;
    }

    public static Thread daemonForever(long delayInMillis, Runnable runnable) {
        return daemonForever(null, delayInMillis, runnable);
    }

    public static Thread daemonForever(final String threadName, final long delayInMillis, final Runnable runnable) {
        Runnable forever = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        runnable.run();

                        try {
                            Thread.sleep(delayInMillis);
                        } catch (InterruptedException e) {
                            //
                        }
                    }
                } catch (Throwable e) {
                    log.error("daemon.forever.e: " + threadName, e);
                }
            }
        };

        Thread t = (is.t(threadName)) ? new Thread(forever, threadName) : new Thread(forever);
        t.setDaemon(true);

        return t;
    }

    private static Integer doIntConvert(BigDecimal val) {
        return val.intValue();
    }

    private static Integer doIntConvert(Long val) {
        return val.intValue();
    }

    private static Integer doIntConvert(Integer val) {
        return val;
    }

    private static Integer doIntConvert(Object val) {
        return Integer.parseInt(val.toString());
    }

    private static Long doLongConvert(BigDecimal val) {
        return val.longValue();
    }

    private static Long doLongConvert(Long val) {
        return val;
    }

    private static Long doLongConvert(Integer val) {
        return val.longValue();
    }

    private static Long doLongConvert(Object val) {
        return Long.parseLong(val.toString());
    }

    private static Boolean doBooleanConvert(String val) {
        return Boolean.valueOf(val);
    }

    private static Boolean doBooleanConvert(Boolean val) {
        return val;
    }

    private static Boolean doBooleanConvert(Object val) {
        return Boolean.valueOf(val.toString());
    }

}
