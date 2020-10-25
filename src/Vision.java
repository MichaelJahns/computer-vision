import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vision {
    protected Mat needleMat;
    private int mAlgorithm;
    private double maxThreshold;
    private double minThreshold;

    public Vision(String needlePath, int mAlgorithm) {
        this.needleMat = Imgcodecs.imread(needlePath);
        this.mAlgorithm = mAlgorithm;
        this.maxThreshold = .80;
        this.minThreshold = .20;
    }

    public Vision() {
        this.needleMat = Imgcodecs.imread("images/needle.jpg");
        this.mAlgorithm = Imgproc.TM_CCOEFF_NORMED;
        this.maxThreshold = .80;
        this.minThreshold = .20;
    }

    public Mat findClickPoints(Mat source, Mat template) {
        Mat mTemplate = MatchTemplate.matchProgramatically(source, template, mAlgorithm);
        Mat thresholdMat = thresholdMat(mTemplate, Imgproc.THRESH_TOZERO);
        List<MatOfPoint> contours = findContoursFromThresholdMat(thresholdMat);


        Iterator<MatOfPoint> each = contours.iterator();
        Mat dst = new Mat();
        source.copyTo(dst);

        while (each.hasNext()) {
            MatOfPoint match = each.next();
            List<Point> points = new ArrayList<>();
            Converters.Mat_to_vector_Point(match, points);

            for (Point p : points) {
                drawRectangle(dst, p, 1);
            }
        }
        return dst;
    }

    public static Mat thresholdMat(Mat source, int tMethod) {
        Imgproc.threshold(source, source, 155, 255, tMethod);
        return source;
    }

    public static List<MatOfPoint> findContoursFromThresholdMat(Mat mat) {
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        hierarchy.release();
        return contours;
    }

    public static Mat drawRectangle(Mat source, Point location, int color) {
        int B = 0;
        int G = 0;
        int R = 0;
        switch (color) {
            case 0:
                B = 255;
            case 1:
                G = 255;
            case 2:
                R = 225;
        }
        Imgproc.rectangle(
                source,
                location,
                new Point(location.x + MatchTemplate.nWidth, location.y + MatchTemplate.nHeight),
                new Scalar(R, G, B),
                5
        );
        return source;
    }
}
