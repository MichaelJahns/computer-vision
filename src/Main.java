import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static void main(String args[]){
        System.out.println(Core.VERSION);

        String beach = "images/haystack.jpg";
        String seal = "images/needle.jpg";

        Mat haystackImg = Imgcodecs.imread(beach);
        Mat needleImg = Imgcodecs.imread(seal);

        Mat diff = new Mat();
        int machMethod = Imgproc.TM_CCOEFF_NORMED;
        Imgproc.matchTemplate(haystackImg, needleImg, diff, machMethod);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(diff);
        Point matchLocation = mmr.maxLoc;
        Imgproc.rectangle(haystackImg, matchLocation, new Point(matchLocation.x + needleImg.cols(), matchLocation.y+needleImg.rows()), new Scalar(255,255,255));
        Imgcodecs.imwrite(beach+"sonuc.jpg", haystackImg);


        HighGui.imshow("Beach", needleImg);
        HighGui.waitKey();
    }



}
