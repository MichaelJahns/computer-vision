import com.sun.jna.platform.win32.WinDef;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static Vision vision;
    public static double maxThreshold;
    public static double minThreshold;

    public static void main(String args[]) {
        String needlePath = "images/drops.jpg";
        int mAlgorithm = Imgproc.TM_CCOEFF_NORMED;
        vision = new Vision(needlePath, mAlgorithm);
        try {
            Robot robot = new Robot();
            run(robot);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void run(Robot robot) {
        WinDef.HWND windowHandle = Win32.findWindowHandle();
        Win32.moveToFront(windowHandle);

        long loopTime = System.currentTimeMillis();
        while (true) {
            BufferedImage bi = VideoCapture.captureBIFromScreen(robot);
            Mat mat = VideoCapture.img2Mat(bi);
            Mat processed = vision.findClickPoints(mat, vision.needleMat);

            HighGui.imshow("processed", processed);
            HighGui.waitKey(1);
            long msDelTime = System.currentTimeMillis() - loopTime;
            double sDelTime = ((msDelTime * .001));
            double FPS = 1 / sDelTime;
//            System.out.println(FPS);
            loopTime = System.currentTimeMillis();
            if (HighGui.pressedKey == 4) {
                HighGui.destroyAllWindows();
            }
        }
    }


}
