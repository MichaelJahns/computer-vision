import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static int nWidth;
    public static int nHeight;
    public static double threshold;

    public static void main(String args[]) {
        System.out.println(Core.VERSION);

        String haystackPath = "images/albion_farm.jpg";
        String needlePath = "images/albion_cabbage.jpg";

        Mat haystackImg = Imgcodecs.imread(haystackPath);
        Mat grayHaystack = new Mat(haystackImg.rows(), haystackImg.cols(), CvType.CV_8U);
        Imgproc.cvtColor(haystackImg, grayHaystack, Imgproc.COLOR_BGR2GRAY);

        Mat needleImg = Imgcodecs.imread(needlePath);
        Mat grayNeedle = new Mat(needleImg.rows(), needleImg.cols(), CvType.CV_8U);
        Imgproc.cvtColor(needleImg, grayNeedle, Imgproc.COLOR_BGR2GRAY);
        nWidth = needleImg.cols();
        nHeight = needleImg.rows();

        threshold = 0.8;

        Mat matchTemplate = new Mat(grayHaystack.rows(), grayHaystack.cols(), CvType.CV_8U);
        int machMethod = Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(grayHaystack, grayNeedle, matchTemplate, machMethod);
        Imgcodecs.imwrite("out/differenceMaps/matchTemplateAlbion" + machMethod + ".jpg", matchTemplate);

        HighGui.imshow("fart", matchTemplate);
        HighGui.waitKey();


//        System.out.println(matchTemplate.depth());
////data type 5 is cv32fc1 or 32bits floating point one channel
////        How does the above information help me to iterate over it?
//        Mat thresholdImage = new Mat();
//        int thresholdMethod = Imgproc.THRESH_TOZERO;
//        Imgproc.threshold(matchTemplate, thresholdImage ,threshold, 255, thresholdMethod);
//        Imgcodecs.imwrite("out/differenceMaps/Gthreshold"+ thresholdMethod + ".jpg", thresholdImage);


//            Core.MinMaxLocResult mmr = Core.minMaxLoc(diff);
//            Point matchLocation = mmr.maxLoc;
//
//            drawRectangle(haystackImg, matchLocation);


//            HighGui.imshow("Beach", thresholdImage);
//            HighGui.waitKey();

    }

    public static void drawRectangle(Mat source, Point location) {
        Imgproc.rectangle(
                source,
                location,
                new Point(location.x + nWidth, location.y + nHeight),
                new Scalar(255, 255, 255)
        );
//        iterate diff I think
//        HOW DO I ITERATE DIFF
//        filter confidences less that threshold
//        ideal output would be an array of x,y Points
//        [(x,y)(x1,y1)(x2,y2)]
    }


}
