import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    public static double threshold;

    public static void main(String args[]) {
        System.out.println(Core.VERSION);

//        matchTemplate.convertTo(matchTemplate, CvType.CV_8U);

//            drawRectangle(haystackImg, matchLocation);
    matchTemplate.runner();

    }

//    public static void drawRectangle(Mat source, Point location) {
//        Imgproc.rectangle(
//                source,
//                location,
//                new Point(location.x + nWidth, location.y + nHeight),
//                new Scalar(255, 255, 255)
//        );
//        iterate diff I think
//        HOW DO I ITERATE DIFF
//        filter confidences less that threshold
//        ideal output would be an array of x,y Points
//        [(x,y)(x1,y1)(x2,y2)]
//    }


}
