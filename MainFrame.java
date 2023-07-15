import javax.swing.*;
import java.awt.*;

public class MainFrame {
    JFrame frame = new JFrame();

    MainFrame() {
        frame.setTitle("Track your time");
        // frame.setIconImage(new Image());
        frame.setBounds(50, 50, 600, 350);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}