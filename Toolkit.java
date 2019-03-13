
/**
 * Helpful methods for the PaintShapes class.
 *
 * @author Lewis Barnett
 * @version Fall 2018
 */
import java.awt.Color;
import javax.swing.JLabel;

public class Toolkit
{

    /**
     * Basic incremental scan line conversion algorithm from Foley and 
     * van Dam, 2e. Given the endpoints of a line, fill in the appropriate
     * pixels between the endpoints to approximate the true line.
     * 
     * @param x0    x coordinate of the first endpoint
     * @param y0    y coordinate of the first endpoint
     * @param x1    x coordinate of the second endpoint
     * @param y1    y coordinate of the second endpoint
     * @param c     Color to draw with
     * @param canvas    A 2D array of JLabels to use for drawing.
     */
    public static void drawLine(int x0, int y0, int x1, int y1, Color c, 
        JLabel [][] canvas)
    {
        // There are two cases based on the slope of the line, depending on
        // whether the x coordinate or the y coordinate will change more
        // slowly.
        double dy = y1 - y0;
        double dx = x1 - x0;
        double m = dy / dx;
        
        // If the slope is <= 1, x changes more rapidly
        if (Math.abs(m) <= 1){
            // In this case, we must assume that x0 < x1. If not, swap
            // coordinates.
            if (x0 > x1) {
                int temp = x1;
                x1 = x0;
                x0 = temp;
                
                temp = y1;
                y1 = y0;
                y0 = temp;
                
                // Recalculate slope.
                dy = y1 - y0;
                dx = x1 - x0;
                m = dy / dx;
            }
            
            double y = y0;
            
            //System.out.println("m = " + m);
            
            for (int x = x0; x <= x1; x++){
                int yCoord = (int) Math.round(y);
                canvas[yCoord][x].setBackground(c);
                y += m;
            }
        } else {
            if (y0 > y1) {
                int temp = y1;
                y1 = y0;
                y0 = temp;
                
                temp = x1;
                x1 = x0;
                x0 = temp;
                
                // recalculate slope
                dy = y1 - y0;
                dx = x1 - x0;
                m = dy / dx;
            }
            
            double x = x0;
            
            for (int y = y0; y <= y1; y++){
                int xCoord = (int) Math.round(x);
                canvas[y][xCoord].setBackground(c);
                x += 1.0/m;
            }
        }
    }

    /**
     * Basic midpoint circle algorithm from Foley and Van Dam, 2e.
     * 
     * @param centerX   x coordinate of the center of the circle
     * @param centerY   y coordinate of the center of the circle
     * @param radius    radius of the circle.
     * @param c         Color to draw with.
     * @param canvas    A 2D array of JLabels to use for drawing.
     */
    public static void drawCircle(int centerX, int centerY, int radius, Color c,
        JLabel [][] canvas){
        // generate points as if circle is at origin, translate when 
        // drawing to actual position.
        
        // This method only generates the points for one eight of the 
        // circle, the rest are drawn by drawCirclePoints using the 
        // eight-fold symmetry of the circle to infer the other seven
        // points from the generated point.
        
        int x = 0;
        int y = radius;
        int d = 1 - radius;
        drawCirclePoints(x, y, centerX, centerY, c, canvas);
        while (y > x){
            // There are some parts of the arc where the y value does 
            // not change, only the x value changes.
            if (d < 0) {
                d += 2*x + 3;
            } else {
                d += 2*(x - y) + 5;
                y--;
            }
            x++;
            drawCirclePoints(x, y, centerX, centerY, c, canvas);
        }
    }
    
    /**
     * Due to the eightfold symmetry of the circle, it is only necessary to 
     * compute the points for one eighth of the circle; the rest can be 
     * derived from those points. Given a point on a circle, draw the other
     * seven points that can be determined by symmetry.
     * 
     * @param x The x coordinate of a pixel on the circle.
     * @param y The y coordinate of a pixel on the circle.
     * @param centerX   The x coordinate of the center of the circle.
     * @param centerY   The y coordinate of the center of the circle.
     * @param c         Color to draw with.
     * @param canvas    A 2D array of JLabels to use for drawing.
     */
    public static void drawCirclePoints(int x, int y, int centerX, int centerY, 
        Color c, JLabel [][] canvas)
    {
        canvas[y + centerY][x + centerX].setBackground(c);
        canvas[x + centerY][y + centerX].setBackground(c);
        canvas[y + centerY][-x + centerX].setBackground(c);
        canvas[x + centerY][-y + centerX].setBackground(c);
        canvas[-x + centerY][-y + centerX].setBackground(c);
        canvas[-y + centerY][-x + centerX].setBackground(c);
        canvas[-y + centerY][x + centerX].setBackground(c);
        canvas[-x + centerY][y + centerX].setBackground(c);
    }
    
    public static Color stringToColor(String color){
        Color myColorObject = Color.black;
       if      (color.equals("black"))      { myColorObject = Color.black; }
        else if (color.equals("blue"))       { myColorObject = Color.blue; }
        else if (color.equals("cyan"))       { myColorObject = Color.cyan; }
        else if (color.equals("dark gray"))  { myColorObject = Color.darkGray; }
        else if (color.equals("gray"))       { myColorObject = Color.gray; }
        else if (color.equals("green"))      { myColorObject = Color.green; }
        else if (color.equals("light gray")) { myColorObject = Color.lightGray; }
        else if (color.equals("magenta"))    { myColorObject = Color.magenta; }
        else if (color.equals("orange"))     { myColorObject = Color.orange; }
        else if (color.equals("pink"))       { myColorObject = Color.pink; }
        else if (color.equals("red"))        { myColorObject = Color.red; }
        else if (color.equals("white"))      { myColorObject = Color.white; }
        else if (color.equals("yellow"))     { myColorObject = Color.yellow;}
        else{ myColorObject = Color.black;}
        return myColorObject;
    }
    
    public static String colorToString( Color color){
        String myColorString = "";
        if      (color == Color.black)      { myColorString = "black";}
        else if (color == Color.blue)       { myColorString = "blue";}
        else if (color == Color.cyan)       { myColorString = "cyan";}
        else if (color == Color.darkGray)   { myColorString = "darkGray";}
        else if (color == Color.gray)       { myColorString = "gray";}
        else if (color == Color.green)      { myColorString = "green"; }
        else if (color == Color.lightGray)  { myColorString = "lightGray"; }
        else if (color == Color.magenta)    { myColorString = "magenta"; }
        else if (color == Color.orange)     { myColorString = "orange"; }
        else if (color == Color.pink)       { myColorString = "pink"; }
        else if (color == Color.red)        { myColorString = "red";}
        else if (color == Color.white)      { myColorString = "white";}
        else if (color == Color.yellow)     { myColorString = "black";}
        return myColorString;
    }
}


