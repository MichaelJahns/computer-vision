import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VideoCapture {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String args[]) {
        String fileName = "pop.jpg";
        String format = "jpg";
        try {
            Robot robot = new Robot();
//            capture(robot, fileName, format);
            screenRecordBenchmark(robot);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void screenRecordBenchmark(Robot robot) {
        int i = 0;
        while (true) {
            BufferedImage bi = squish(robot);
            Mat mat = img2Mat(bi);
            HighGui.imshow("Screencap", mat);
            HighGui.waitKey(1);
            System.out.println(i);
            i++;
        }
    }

    public static void capture(Robot robot, String newFileName, String newFileFormat) {
        BufferedImage bi = squish(robot);
        Mat mat = img2Mat(bi);
        try {
            HighGui.imshow("Screen Cap", mat);
            HighGui.waitKey();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static BufferedImage squish(Robot robot) {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        final BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        return screenFullImage;
    }

    protected static Mat img2Mat(BufferedImage in) {
        Mat out;
        byte[] data;
        int r, g, b;

        if (in.getType() == BufferedImage.TYPE_INT_RGB) {
            out = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC3);
            data = new byte[in.getWidth() * in.getHeight() * (int) out.elemSize()];
            int[] dataBuff = in.getRGB(0, 0, in.getWidth(), in.getHeight(), null, 0, in.getWidth());
            for (int i = 0; i < dataBuff.length; i++) {
                data[i * 3] = (byte) ((dataBuff[i] >> 0) & 0xFF);
                data[i * 3 + 1] = (byte) ((dataBuff[i] >> 8) & 0xFF);
                data[i * 3 + 2] = (byte) ((dataBuff[i] >> 16) & 0xFF);
            }
        } else {
            out = new Mat(in.getHeight(), in.getWidth(), CvType.CV_8UC1);
            data = new byte[in.getWidth() * in.getHeight() * (int) out.elemSize()];
            int[] dataBuff = in.getRGB(0, 0, in.getWidth(), in.getHeight(), null, 0, in.getWidth());
            for (int i = 0; i < dataBuff.length; i++) {
                r = (byte) ((dataBuff[i] >> 0) & 0xFF);
                g = (byte) ((dataBuff[i] >> 8) & 0xFF);
                b = (byte) ((dataBuff[i] >> 16) & 0xFF);
                data[i] = (byte) ((0.21 * r) + (0.71 * g) + (0.07 * b));
            }
        }
        out.put(0, 0, data);
        return out;
    }

}