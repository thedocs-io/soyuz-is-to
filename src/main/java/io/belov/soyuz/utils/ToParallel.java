package io.belov.soyuz.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by fbelov on 22.11.16.
 */
public class ToParallel {

    private static final ExecutorService POOL = Executors.newCachedThreadPool();

    private ExecutorService pool;
    private int threadsCount = -1;

    ToParallel() {
        this(null);
    }

    ToParallel(ExecutorService pool) {
        this.pool = (pool == null) ? POOL : pool;
    }

    public <T> List<T> list(Collection<T> items, Consumer<T> consumer) {
        return list(items, (i) -> {
            consumer.accept(i);

            return i;
        });
    }

    public <T, R> List<R> list(Collection<T> items, Function<T, R> func) {
        Semaphore semaphore = new Semaphore(getThreadsCountFor(items));

        List<Future<R>> futures = to.list(items, item -> {
            return pool.submit(() -> {
                try {
                    semaphore.acquire();

                    return func.apply(item);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
        });

        return to.list(futures, f -> {
            try {
                return f.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }

    private int getThreadsCountFor(Collection items) {
        return (threadsCount <= 0) ? items.size() : threadsCount;
    }
}
