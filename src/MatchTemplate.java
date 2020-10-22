import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class MatchTemplate {
    public static int nWidth;
    public static int nHeight;
    public static String trial;
    private static final String fileDestination = "./out/tester/";

    public static Mat normalizeMat(Mat mat) {
        Mat result = new Mat();
        Core.normalize(mat, result, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);
        return result;
    }

    public static void writeMat(Mat matchTemplate, String message) {
        Imgcodecs.imwrite(fileDestination + trial + message + ".jpg", matchTemplate);
    }

    public static void writeListOfMats(List<Mat> matList, String message) {
        int i = 0;
        for (Mat mat : matList) {
            writeMat(mat, i + message);
            i++;
        }
    }

    // RUNNERS
    public static List<Mat> runner(Mat source, Mat template) {
        trial = "algorithm_";

        nHeight = template.rows();
        nWidth = template.cols();

        Mat result = new Mat();

        List<Mat> allMatchAlgorithms = matchByAllAlgorithms(source, template, result);
        return allMatchAlgorithms;
    }

    public static List<Mat> matchByAllAlgorithms(Mat source, Mat template, Mat result) {
        List<Mat> allMatchAlgorithms = new ArrayList<>();

        Mat TM_SQDIFF = matchByTM_SQDIFF(source, template, result);
        allMatchAlgorithms.add(TM_SQDIFF);

        Mat TM_SQDIFF_NORMED = matchByTM_SQDIFF_NORMED(source, template, result);
        allMatchAlgorithms.add(TM_SQDIFF_NORMED);

        Mat TM_CCORR = matchByTM_CCORR(source, template, result);
        allMatchAlgorithms.add(TM_CCORR);

        Mat TM_CCORR_NORMED = matchByTM_CCCORR_NORMED(source, template, result);
        allMatchAlgorithms.add(TM_CCORR_NORMED);

        Mat TM_CCOEFF = matchByTM_CCOEFF(source, template, result);
        allMatchAlgorithms.add(TM_CCOEFF);

        Mat TM_CCOEFF_NORMED = matchByTM_CCOEFF_NORMED(source, template, result);
        allMatchAlgorithms.add(TM_CCOEFF_NORMED);

        return allMatchAlgorithms;
    }

    //Match Template Algorithms
    public static Mat matchByTM_SQDIFF(Mat source, Mat template, Mat matchTemplate) {
        int machMethod = Imgproc.TM_SQDIFF;
        Imgproc.matchTemplate(source, template, matchTemplate, machMethod);
        matchTemplate = normalizeMat(matchTemplate);
        return matchTemplate;
    }

    public static Mat matchByTM_SQDIFF_NORMED(Mat source, Mat template, Mat matchTemplate) {
        int machMethod = Imgproc.TM_SQDIFF_NORMED;
        Imgproc.matchTemplate(source, template, matchTemplate, machMethod);
        matchTemplate = normalizeMat(matchTemplate);
        return matchTemplate;
    }

    public static Mat matchByTM_CCORR(Mat source, Mat template, Mat matchTemplate) {
        int machMethod = Imgproc.TM_CCORR;
        Imgproc.matchTemplate(source, template, matchTemplate, machMethod);
        matchTemplate = normalizeMat(matchTemplate);
        return matchTemplate;
    }

    public static Mat matchByTM_CCCORR_NORMED(Mat source, Mat template, Mat matchTemplate) {
        int machMethod = Imgproc.TM_CCORR_NORMED;
        Imgproc.matchTemplate(source, template, matchTemplate, machMethod);
        matchTemplate = normalizeMat(matchTemplate);
        return matchTemplate;
    }

    public static Mat matchByTM_CCOEFF(Mat source, Mat template, Mat matchTemplate) {
        int machMethod = Imgproc.TM_CCOEFF;
        Imgproc.matchTemplate(source, template, matchTemplate, machMethod);
        matchTemplate = normalizeMat(matchTemplate);
        return matchTemplate;
    }

    public static Mat matchByTM_CCOEFF_NORMED(Mat source, Mat template, Mat matchTemplate) {
        int machMethod = Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(source, template, matchTemplate, machMethod);
        matchTemplate = normalizeMat(matchTemplate);
        return matchTemplate;
    }
}
