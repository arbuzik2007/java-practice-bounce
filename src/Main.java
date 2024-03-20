import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    static JFrame getFrame(int width, int height){
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2-250,
                dimension.height/2 - 150,
                width,
                height);
        return jFrame;
    }
    public static void main(String[] args) {
        var frame = getFrame(700, 700);
        var panel = new BounceMotionPanel();
        panel.setVisible(true);
        frame.setContentPane(panel);

        Timer timer = new Timer(30, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.update(10, frame);
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}