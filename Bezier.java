package bezier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Bezier extends JPanel implements ActionListener {
    int[] x2 = {150, 100, 150};
    int[] y2 = {50, 100, 150};
    int[] x = {150, 300};
    int[] y = {50, 50};
    int[] x3 = {300,320,320};
    int[] y3 = {50,60,500};
    int[] x4 = {320,250,200};
    int[] y4 = {500,600,500};
    int[] x5 = {200,250,600};
    int[] y5 = {500,300,200};
    int[] x6 = {450,450};
    int[] y6 = {50,550};
    int[] x7 = {450,525,550,575,600};
    int[] y7 = {50,50,100,50,200};
    int[] x8 = {600,800,450};
    int[] y8 = {200,500,550};

    int[] x9 = {800,850,900};
    int[] y9 = {100,0,100};
    int[] x10 = {900,950,1000};
    int[] y10 = {100,0,100};
    int[] x11 = {800,750,900,1050,1000};
    int[] y11 = {100,250,500,250,100};


    public Bezier() {
        setVisible(true);
    }

    public static int silnia(int i) {
        if (i == 0)
            return 1;
        if (i == 1)
            return 1;
        return i * silnia(i - 1);
    }

    public static int dwumianNewtona(int n, int k) {

        if (k != 0)
            return silnia(n) / (silnia(k) * silnia(n - k));
        else {
            return 1;
        }
    }




    public void bezier(int[] x, int[] y, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        double sumax = 0;
        double sumay = 0;

        for (double t = 0; t <= 1; t += 0.0001) {
                for (int i = 0; i <= x.length-1; i++) {
                    sumax += dwumianNewtona(x.length-1, i) * Math.pow(1 - t, x.length-1-i) * Math.pow(t, i) * x[i];
                    sumay += dwumianNewtona(x.length-1, i) * Math.pow(1 - t, x.length-1- i) * Math.pow(t, i) * y[i];
                }

                g2d.setColor(Color.red);
                g2d.drawOval((int)sumax,(int)sumay,3,3);
                //g2d.drawLine((int) sumax, (int) sumay, (int) sumax, (int) sumay);
                sumax = 0;
                sumay = 0;
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        bezier(x2,y2,g);
        bezier(x,y,g);
        bezier(x3,y3,g);
        bezier(x4,y4,g);
        bezier(x5,y5,g);
        bezier(x6,y6,g);
        bezier(x7,y7,g);
        bezier(x8,y8,g);

        bezier(x9,y9,g);
        bezier(x10,y10,g);
        bezier(x11,y11,g);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}
