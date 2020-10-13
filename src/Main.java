import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class Main {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

    public static Image toBufferedImage(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if(m.channels() != 1){
            Mat m2 = new Mat();
            Imgproc.cvtColor(m, m2, Imgproc.COLOR_BGR2RGB);
            m=m2;
        }
        byte[] b = new byte[m.channels()*m.cols()*m.rows()];
        m.get(0,0,b);
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        image.getRaster().setDataElements(0,0,m.cols(), m.rows(), b);
        return image;
    }

    public static void main(String args[]){

        String beach = "images/haystack.jpg";
        String seal = "images/needle.jpg";

        Mat haystackImg = Imgcodecs.imread(beach);
        Mat needleImg = Imgcodecs.imread(seal);


        Image bufferedImage = toBufferedImage(haystackImg);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(bufferedImage, 0, 0, null);
        System.out.println("success");
        System.out.println(Core.VERSION);

        JFrame frame= new JFrame("Welecome to JavaTutorial.net");
        frame.getContentPane().add(new JFrameGraphics());
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }



}
