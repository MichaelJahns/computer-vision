import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class matchTemplate {
    public static int nWidth;
    public static int nHeight;
    public static String trial;


    public static void runner(){
        trial = "_desert";
        String haystackPath = "images/desert.jpg";
        Mat source = Imgcodecs.imread(haystackPath);
        source.convertTo(source, CvType.CV_8UC1);
        printSource(source);

        String needlePath = "images/drops.jpg";
        Mat template = Imgcodecs.imread(needlePath);
        template.convertTo(template, CvType.CV_8UC1);
        printTemplate(template);

        Mat result = new Mat();
        source.copyTo(result);
        printPreResult(result);

        printAllAlgorithms(source, template, result);
    }

    public static void printSource(Mat source){
        System.out.println("SOURCE:");
        System.out.println("Depth " + source.depth());
        System.out.println("Channels " + source.channels());
        System.out.println("Type " + source.type());
        Imgcodecs.imwrite("out/algorithms/" + trial + "Source.jpg", source);
    }

    public static void printTemplate(Mat template){
        System.out.println("TEMPLATE:");
        System.out.println("Depth " + template.depth());
        System.out.println("Channels " + template.channels());
        System.out.println("Type " + template.type());
        Imgcodecs.imwrite("out/algorithms/" + trial + "Template.jpg", template);
    }

    public static void printPreResult(Mat result){
        System.out.println("RESULT:");
        System.out.println("Depth " + result.depth());
        System.out.println("Channels " + result.channels());
        System.out.println("Type " + result.type());
        Imgcodecs.imwrite("out/algorithms/" + trial + "preResult.jpg", result );
    }
    public static void printAllAlgorithms(Mat source, Mat template, Mat result){
        printTM_SQDIFF(source, template,result);
        printTM_SQDIFF_NORMED(source, template, result);

        printTM_CCORR(source, template, result);
        printTM_CCORR_NORMED(source, template, result);

        printTM_CCOEFF(source, template, result);
        printTM_CCOEFF_NORMED(source, template, result);
    }
    public static void printPostResult(Mat preResult, String matchAlgorithm){
        Mat result = new Mat();
        Core.normalize(preResult, result,0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);
        System.out.println("POST-RESULT: " + matchAlgorithm);
        System.out.println("Depth " + result.depth());
        System.out.println("Channels " + result.channels());
        System.out.println("Type " + result.type());
        Imgcodecs.imwrite("out/algorithms/" + matchAlgorithm + trial + "_result.jpg", result );
    }

    public static void printTM_SQDIFF(Mat source, Mat template, Mat result){
        int machMethod = Imgproc.TM_SQDIFF;
        String matchAlgorithm = "TM_SQDIFF";
        Imgproc.matchTemplate(source, template, result , machMethod);
        printPostResult(result, matchAlgorithm);
    }
    public static void printTM_SQDIFF_NORMED(Mat source, Mat template, Mat result){
        int machMethod = Imgproc.TM_SQDIFF_NORMED;
        String matchAlgorithm = "TM_SQDIFF_NORMED";
        Imgproc.matchTemplate(source, template, result , machMethod);
        printPostResult(result, matchAlgorithm);
    }
    public static void printTM_CCORR(Mat source, Mat template, Mat result){
        int machMethod = Imgproc.TM_CCORR;
        String matchAlgorithm = "TM_CCORR";
        Imgproc.matchTemplate(source, template, result , machMethod);
        printPostResult(result, matchAlgorithm);
    }

    public static void printTM_CCORR_NORMED(Mat source, Mat template, Mat result){
        int machMethod = Imgproc.TM_CCORR_NORMED;
        String matchAlgorithm = "TM_CCORR_NORMED";
        Imgproc.matchTemplate(source, template, result , machMethod);
        printPostResult(result,matchAlgorithm);
    }

    public static void printTM_CCOEFF(Mat source, Mat template, Mat result) {
        int machMethod = Imgproc.TM_CCOEFF;
        String matchAlgorithm = "TM_CCOEFF";
        Imgproc.matchTemplate(source, template, result , machMethod);
        printPostResult(result, matchAlgorithm);
    }

    public static void printTM_CCOEFF_NORMED(Mat source, Mat template, Mat result){
        int machMethod = Imgproc.TM_CCOEFF_NORMED;
        String matchAlgorithm = "TM_CCOEFF_NORMED";
        Imgproc.matchTemplate(source, template, result , machMethod);
        printPostResult(result, matchAlgorithm);
    }
}
