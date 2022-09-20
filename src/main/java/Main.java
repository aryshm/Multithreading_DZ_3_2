import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    private final static int SIZE = 10;
    private final static int LOWER_LIMIT = 0;
    private final static int UPPER_LIMIT = 5000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder revenue = new LongAdder();
        new Random().ints(SIZE, LOWER_LIMIT, UPPER_LIMIT)
                .forEach(i -> executorService.submit(() -> revenue.add(i)));
        new Random().ints(SIZE, LOWER_LIMIT, UPPER_LIMIT)
                .forEach(i -> executorService.submit(() -> revenue.add(i)));
        new Random().ints(SIZE, LOWER_LIMIT, UPPER_LIMIT)
                .forEach(i -> executorService.submit(() -> revenue.add(i)));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Результат: " + revenue.sum());
    }
}
