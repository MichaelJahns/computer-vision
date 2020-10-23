import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScreenCaptureManager {
    private int THREADS = 6;
    private int FPS_PER_THREAD = 6;
    private ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(THREADS);
    private Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    private ConcurrentLinkedQueue<BufferedImage> store = new ConcurrentLinkedQueue<>();
    final static long toNanos = 1000_000_000L;


    public ScreenCaptureManager(int THREADS, int FPS) {
        this.THREADS = THREADS;
        FPS_PER_THREAD = (int) ((double) FPS / (double) THREADS);
    }

    public ScreenCaptureManager() {

    }

    private static long fractionToNanos(long a, long b) {
        //  the fraction (a/b) * toNanos
        return a * toNanos / b;
    }

    public void start() throws Exception {
        for (int i = 0; i < 1; i++) {
            scheduledThreadPool.scheduleAtFixedRate(
                    new ScreenCaptureRobot(new Robot(), captureSize, store),
                    fractionToNanos(i, THREADS),
                    fractionToNanos(i, FPS_PER_THREAD),
                    TimeUnit.NANOSECONDS
            );

        }
    }

    public void stop() {
        scheduledThreadPool.shutdown();
    }

    public ConcurrentLinkedQueue<BufferedImage> getStore() {
        return store;
    }

}
