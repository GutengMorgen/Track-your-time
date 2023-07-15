import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class MainFrame implements ActionListener {
    JFrame frame = new JFrame();
    JButton saveBtn;

    MainFrame() {
        saveBtn = new JButton("SAVE");
        saveBtn.setBounds(20, 200, 550, 100);
        saveBtn.addActionListener(this);

        frame.add(saveBtn);

        // frame.setBounds(50, 50, 600, 350);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 350);
        frame.setResizable(true); // false
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // System.out.println("close");
        // frame.setDefaultCloseOperation(clos)
        frame.dispose();
    }

    public static void main(String[] args) throws InterruptedException {
        // new MainFrame();
        long value = 2;
        boolean x = true;
        long displayMinutes = 0;
        long startTime = System.currentTimeMillis();
        System.out.println("start time: ");
        while (x) {
            TimeUnit.SECONDS.sleep(1);
            long timepassed = System.currentTimeMillis() - startTime;
            long secondspassed = timepassed / 1000;
            if (secondspassed == 60) {
                secondspassed = 0;
                startTime = System.currentTimeMillis();
            }
            if ((secondspassed % 60) == 0)
                displayMinutes++;
            else if (displayMinutes == value) {
                x = false;
                System.out.println("timer stop");
            }

            System.out.println(displayMinutes + ":" + secondspassed);
        }
    }
}