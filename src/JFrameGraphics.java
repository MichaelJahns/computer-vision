import javax.swing.*;
import java.awt.*;

public class JFrameGraphics extends JPanel {
    public void paint(Graphics g){
        Image image = Toolkit.getDefaultToolkit().getImage("./images/haystack.jpg");
        g.drawImage(image, 10, 10, this);
    }

    public static void main(String[] args){
        JFrame frame= new JFrame("JavaTutorial.net");
        frame.getContentPane().add(new JFrameGraphics());
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
