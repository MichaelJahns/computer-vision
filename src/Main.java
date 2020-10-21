import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static double maxThreshold;
    public static double minThreshold;

    public static void main(String args[]) {
        System.out.println(Core.VERSION);
        maxThreshold = .80;
        minThreshold = .20;

        String haystackPath = "images/albion_farm.jpg";
        Mat source = Imgcodecs.imread(haystackPath);

        String needlePath = "images/albion_cabbage.jpg";
        Mat template = Imgcodecs.imread(needlePath);

        List<Mat> allMatchAlgorithms = matchTemplate.runner(source, template);
        matchTemplate.writeListOfMats(allMatchAlgorithms, "preprocess");
        List<Mat> thresheldMats = thresholdStab(allMatchAlgorithms);

        List<MatOfPoint> contours = findContoursFromThresholdMat(thresheldMats.get(5));
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
        matchTemplate.writeMat(dst, "Rectangled");

    }

    public static List<Mat> thresholdStab(List<Mat> matList) {
        List<Mat> thresheldMats = new ArrayList<>();
        int i = 0;
        for (Mat mat : matList) {
            Mat output = new Mat();
            Imgproc.threshold(mat, output, 155, 255, Imgproc.THRESH_TOZERO);
            thresheldMats.add(output);
            i++;
        }
        return thresheldMats;
    }

    public static List<MatOfPoint> findContoursFromThresholdMat(Mat mat) {
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        hierarchy.release();
        return contours;
    }

    public static List<Mat> findBestMatch(Mat source, List<Mat> matList) {
        List<Mat> maxMatches = new ArrayList<>();
        for (Mat mat : matList) {
            Core.MinMaxLocResult mmr = Core.minMaxLoc(mat);
            Mat temp = new Mat();
            source.copyTo(temp);
            if (mmr.maxVal > maxThreshold) {
                Point matchMax = mmr.maxLoc;
                Mat max = drawRectangle(temp, matchMax, 1);
                maxMatches.add(max);
            }
            if (mmr.minVal < minThreshold) {
                Point matchMin = mmr.minLoc;
                Mat min = drawRectangle(temp, matchMin, 2);
                maxMatches.add(min);
            }
        }
        return maxMatches;
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
                new Point(location.x + matchTemplate.nWidth, location.y + matchTemplate.nHeight),
                new Scalar(R, G, B),
                5
        );
        return source;
    }
}
