package triangleperimeter;

import java.util.ArrayList;
import triangleperimeter.TrianglePerimeter.dot;

public class Jarvis {
    public ArrayList<dot> dots;
    public ArrayList<dot> pointsOnHull = new ArrayList<>();
    TrianglePerimeter tp;
    
    public Jarvis(ArrayList<dot> dots, TrianglePerimeter Triangle){
        this.tp = Triangle;
        this.dots = dots;
        pointsOnHull = new ArrayList<>();
    }
    
    public void rightMost() {
        dot max = tp.new dot(400, 400);
        for (dot d : dots) {
            if (d.x <= max.x) {
                if ((d.x < max.x) || ((d.x == max.x) && (d.y > max.y))){
                    max = d;
                }
            }
        }
        pointsOnHull.add(max);
    }
    
    public static double firstAngle(dot dot1, dot dot2){
        if (dot1.equals(dot2)) return 361;
        double angle = Math.atan2(dot2.x - dot1.x, dot2.y - dot1.y);
        angle = angle/Math.PI*180;
        if (angle < 0) angle +=360;
        return angle;
    }
    
    public static double nextAngle(dot first, dot center, dot second) {
        
        class vector {
            int x;
            int y;
            double len;
            
            public vector(dot dot1, dot dot2){
                x = dot1.x - dot2.x;
                y = dot1.y - dot2.y;
                len = Math.sqrt(x*x + y*y);
            }
        }
        
        vector vect1 = new vector(first, center);
        vector vect2 = new vector(second, center);
        
        double cos = (vect1.x*vect2.x + vect1.y*vect2.y)/(vect1.len*vect2.len);
        return Math.acos(cos)/Math.PI*180;
    }
    
    
    public void nextDot(dot currentDot){
        double minAngle = 362;
        int min = 0;
        for (int i = 0; i < dots.size(); i++){
                dot d = dots.get(i);
                if (firstAngle(currentDot, d) < minAngle) {
                    minAngle = firstAngle(currentDot, d);
                    min = i;
                }
        }
        currentDot = dots.get(min);
        dots.remove(min);
        pointsOnHull.add(currentDot);
    }
    
    public ArrayList<dot> findHull(){
        rightMost();
        nextDot(pointsOnHull.get(0));
        double maxAngle;
        
        
        
        do{
            maxAngle = 0;
            dot dot1 = pointsOnHull.get(pointsOnHull.size() - 2);
            dot dot2 = pointsOnHull.get(pointsOnHull.size() - 1);
            dot dot3 = dot1;
            int next = 0;
            for (int i = 0; i < dots.size() - 1; i++){
               dot3 = dots.get(i);
               if (nextAngle(dot1, dot2, dot3) > maxAngle){
                   maxAngle = nextAngle(dot1, dot2, dot3);
                   next = i;
                }
                
            }
            pointsOnHull.add(dots.get(next));
            dots.remove(next);
        }while (!((pointsOnHull.get(pointsOnHull.size() - 1).equals(pointsOnHull.get(0)))));
        return pointsOnHull;
    }
}