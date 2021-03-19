package bezier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
        private final int width = 1200;
        private final int height = 700;

        Frame() {
            initUI();
        }

        private void initUI() {
            add(new Bezier());
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setSize(width, height);
            setTitle("Bezier");
            setVisible(true);

        }

}
