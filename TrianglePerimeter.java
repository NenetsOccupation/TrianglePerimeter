package triangleperimeter;

import java.util.ArrayList;
import java.util.Scanner;
import static triangleperimeter.Paint.paint;

public class TrianglePerimeter {
    
    public class dot{
        public int x;
        public int y;
        
        public dot(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static double perimeter(dot dot1, dot dot2, dot dot3){
        
        return  Math.sqrt(Math.pow((dot2.x - dot1.x), 2) + Math.pow((dot2.y - dot1.y), 2)) +
               Math.sqrt(Math.pow((dot3.x - dot2.x), 2) + Math.pow((dot3.y - dot2.y), 2)) +
               Math.sqrt(Math.pow((dot3.x - dot1.x), 2) + Math.pow((dot3.y - dot1.y), 2));
        
    }
    
    public ArrayList<dot> getDots(String fileAddress){
        ArrayList<dot> dots = new ArrayList<>();
        CSVHandler handler = new CSVHandler();
        ArrayList<int[]> coords = handler.readFile(fileAddress);
        for (int i = 0; i < coords.size(); i++){
            int[] pair = coords.get(i);
            dots.add(new dot(pair[0], pair[1]));
        }
        return dots;
    }
    
    public ArrayList<dot> bruteForce(ArrayList<dot> dots){
        double maxPerimeter = 0;
        ArrayList<dot> result = new ArrayList<>(3);
        result.add(null);
        result.add(null);
        result.add(null);
        
        for(int i = 0; i < dots.size() - 2; i++)
            for(int j = i + 1; j < dots.size() - 1; j++)
                for(int k = j + 1; k < dots.size(); k++) {
                    double per = perimeter(dots.get(i), dots.get(j), dots.get(k));
                    if (per > maxPerimeter) {
                        maxPerimeter = per;
                        result.set(0, dots.get(i));
                        result.set(1, dots.get(j));
                        result.set(2, dots.get(k));
                    }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in,  "UTF-8");
        CSVHandler handler = new CSVHandler();
        System.out.println("Would you like to create new set or use existing?");
        System.out.println("(\"n\" for new, \"e\" for existing)");
        String answer = "";
        boolean ok = false;
        do{
            answer = in.next();
            if (answer.equals("n") || answer.equals("e")){
                ok = true;
                } else{
                    System.out.println("You have to write \"n\" or \"e\"");

                }
        }while(!ok);
        System.out.println("Enter file address (\"d\" for default):");
        String fileAddress = in.next();
        if (fileAddress.equals("d")) fileAddress = "C:\\Users\\Максим\\Desktop\\dots.csv";
        if (answer.equals("n")){
            System.out.println("Enter number of points:");
            int numOfDots = in.nextInt();
            handler.createNewSet(numOfDots, fileAddress);
        }
        TrianglePerimeter tp = new TrianglePerimeter();
        ArrayList<dot> dots = tp.getDots(fileAddress);
        Jarvis j = new Jarvis(dots, tp);
        ArrayList<dot> hull = j.findHull();
        ArrayList<dot> result = tp.bruteForce(hull);
        System.out.println("\nYour triangle is:");
        for (dot d : result) System.out.println(d.x + " " + d.y);
        paint(dots, hull, result);
    }
}
