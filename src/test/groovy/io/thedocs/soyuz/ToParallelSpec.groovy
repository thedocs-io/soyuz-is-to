package io.thedocs.soyuz

import com.google.common.base.Stopwatch
import spock.lang.Specification
import io.thedocs.soyuz.to as To;


import java.util.concurrent.TimeUnit

/**
 * Created by fbelov on 22.11.16.
 */
class ToParallelSpec extends Specification {

    def "should convert to list in parallel"() {
        when:
        Stopwatch sw = Stopwatch.createStarted();
        List<Integer> items = To.list(1, 2, 3);
        List<Integer> answers = To.parallel().list(items, { i ->
            TimeUnit.SECONDS.sleep(1)

            return i * 2;
        });

        then:
        assert answers == [2, 4, 6]
        assert sw.elapsed(TimeUnit.MILLISECONDS) < 1300
    }

}
