import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;
class Punkt{
    private int x;
    private int y;
    Punkt(int x, int y){this.x=x;this.y=y;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Frame extends JFrame {
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
public class Bezier extends JPanel implements ActionListener {
   LinkedList<Punkt> punkty = new LinkedList();
    LinkedList<Punkt> punkty2 = new LinkedList();
    int[] x = {110, 100,110};
    int[] y = {130, 100, 70};
    int[] x2 = {110, 250};
    int[] y2= {70, 70};
    int[] x3 = {250,250};
    int[] y3= {70,350};
    int[] x4 = {250,245,200};
    int[] y4= {350,400,400};
    int[] x5 = {200,100,100,180};
    int[] y5= {400,400,350,300};
    int[] x6 = {180, 220,200};
    int[] y6= {300, 300,340};
    int[] x7 = {200, 280,220};
    int[] y7= {340, 280,100};
    int[] x8 = {220, 120,110};
    int[] y8= {100, 70,130};

    int[] x9 = {400, 500,400};
    int[] y9= {200, 135,70};
    int[] x10 = {400,600,420};
    int[] y10= {70,135,240};
    int[] x11 = {420,700,400};
    int[] y11= {240,345,410};
    int[] x12 = {400,600,400};
    int[] y12 = {410,345,280};
    int[] x13 = {400,400,380};
    int[] y13 = {280,600,280};
    int[] x14 = {380,380,400};
    int[] y14 = {280,-40,200};

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




    public void bezier(int[] x, int[] y, Graphics g, LinkedList<Punkt> lista) {

        Graphics2D g2d = (Graphics2D) g;
        double sumax = 0;
        double sumay = 0;

        for (double t = 0; t <= 1; t += 0.0001) {
            for (int i = 0; i <= x.length-1; i++) {
                sumax += dwumianNewtona(x.length-1, i) * Math.pow(1 - t, x.length-1-i) * Math.pow(t, i) * x[i];
                sumay += dwumianNewtona(x.length-1, i) * Math.pow(1 - t, x.length-1- i) * Math.pow(t, i) * y[i];
            }
            lista.add(new Punkt((int)sumax,(int)sumay));

            g2d.setColor(Color.red);
            g2d.drawOval((int)sumax,(int)sumay,3,3);
            sumax = 0;
            sumay = 0;
        }

    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        super.paint(g);
     
        bezier(x,y,g,punkty);
        bezier(x2,y2,g,punkty);
        bezier(x3,y3,g,punkty);
        bezier(x4,y4,g,punkty);
        bezier(x5,y5,g,punkty);
        bezier(x6,y6,g,punkty);
        bezier(x7,y7,g,punkty);
        bezier(x8,y8,g,punkty);
        bezier(x9,y9,g,punkty2);
        bezier(x10,y10,g,punkty2);
        bezier(x11,y11,g,punkty2);
        bezier(x12,y12,g,punkty2);
        bezier(x13,y13,g,punkty2);
        bezier(x14,y14,g,punkty2);
        System.out.println(punkty.size());

        GeneralPath j=new GeneralPath();
        g2d.setColor(Color.gray);
        j.moveTo(110,130);
        for (Punkt x:punkty
             ) {
            j.lineTo(x.getX(),x.getY());

        }
        j.closePath();
        g2d.fill(j);

        GeneralPath b=new GeneralPath();
        g2d.setColor(Color.gray);
        b.moveTo(400,200);
        for (Punkt x:punkty2
        ) {
            b.lineTo(x.getX(),x.getY());

        }
        b.closePath();
        g2d.fill(b);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame();
            }
        });
    }
}
