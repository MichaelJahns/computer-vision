import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
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
        List<Mat> bestMatches = findBestMatch(source, allMatchAlgorithms);
        matchTemplate.writeListOfMats(bestMatches, "Best match");
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
