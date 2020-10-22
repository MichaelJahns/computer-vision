import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class VideoCapture {
    public static void main(String args[]) {
        String fileName = "pop.jpg";
        String format = "jpg";
        try {
            Robot robot = new Robot();
            capture(robot, fileName, format);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void capture(Robot robot, String newFileName, String newFileFormat) {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        final BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        File newFile = new File(newFileName);
        try {
            boolean after = ImageIO.write(screenFullImage, newFileFormat, newFile);
            System.out.println(after);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
