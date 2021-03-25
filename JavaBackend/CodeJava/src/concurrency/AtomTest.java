import java.util.concurrent.atomic.AtomicLong;

public class AtomTest {
    AtomicLong count = new AtomicLong(0);

    void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count.getAndIncrement();
        }
    }
}
