
package triangleperimeter;

import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import triangleperimeter.TrianglePerimeter.dot;

public class Paint{

            
    
    public static void paint(ArrayList<dot> dots, ArrayList<dot> hull, ArrayList<dot> result) {
        JFrame frame=new JFrame("Test");
        frame.setBounds(0, 0, 850, 850);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(){
            Graphics2D g2;

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g2=(Graphics2D)g;
                
                drawDots(dots, g2);
                drawTriangle(result, g2);
                drawHull(hull, g2);
                
                
            }
        };
        frame.setContentPane(contentPane);
    }
    
    public static void drawDots(ArrayList<dot> dots, Graphics2D g2){
        g2.setColor(Color.BLACK);
        for(dot d : dots) g2.drawLine(d.x, d.y, d.x, d.y);
    }
    
    public static void drawTriangle(ArrayList<dot> result, Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.drawLine(result.get(0).x, result.get(0).y, result.get(1).x, result.get(1).y);
        g2.drawLine(result.get(1).x, result.get(1).y, result.get(2).x, result.get(2).y);
        g2.drawLine(result.get(2).x, result.get(2).y, result.get(0).x, result.get(0).y);
    }
    
        public static void drawHull(ArrayList<dot> hull, Graphics2D g2){
        g2.setColor(Color.cyan);
        for (int i = 0; i < hull.size() - 1; i++){
            g2.drawLine(hull.get(i).x, hull.get(i).y, hull.get(i+1).x, hull.get(i+1).y);
        }
    }
}