import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScreenCaptureRobot implements Runnable {
    Robot robot;
    Rectangle captureSize;
    ConcurrentLinkedQueue<BufferedImage> sharedStore;

    public ScreenCaptureRobot(Robot robot, Rectangle captureSize, ConcurrentLinkedQueue sharedStore) {
        this.robot = robot;
        this.captureSize = captureSize;
        this.sharedStore = sharedStore;
    }

    @Override
    public void run() {
        sharedStore.add(robot.createScreenCapture(captureSize));
    }
}
