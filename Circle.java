import java.awt.*;
import javax.swing.*;

/**
 * public class Circle
 *
 * @author Alex Kiiru
 * @version 12/16/18
 */
public class Circle extends AbstractShape
{
    protected int radius;
    public static final int DEFAULT_RADIUS = 20;
    /**
     * Constructor for objects of class Circle
     */

    public Circle(){
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR, DEFAULT_RADIUS);

    }

    /**
     * Constructor for objects of class Circle
     * @param x  for x coordinate of the center of the rectangle
     * @param y  for y coordinate of the center of the rectangle
     * @param ShapeColor    
     * @param r    
     */
    public Circle(int x, int y, Color shapeColor, int r)
    {
        super(x,y,shapeColor);
        radius = r;
    }

    /**
     * Returns a string containing information about the shape such as the type,
     * the x and y coordinates, the color, and the radius
     * @return String of shape information
     */
    public String toString(){
        String shape = "Circle";
        String x = Integer.toString(myXCoordinate);
        String y = Integer.toString(myYCoordinate);
        String color = Toolkit.colorToString( myColor );
        String sRadius = Integer.toString(radius);
        String shapeVar = (shape + " " + x + " " + y + " " + color + " " + sRadius);
        return shapeVar;
    }

    /**
     * gets String with information of the sahpe and coverts to int
     * @param shapeVar    
     */
    public Circle(String shapeVar){
        if(shapeVar.contains("Square")){
            String Array[] = shapeVar.split(" ");
            myXCoordinate = Integer.parseInt(Array[1]);
            myYCoordinate = Integer.parseInt(Array[2]);
            myColor = Toolkit.stringToColor(Array[3]);
            radius = Integer.parseInt(Array[4]);
        }
    }

    /**
     * Draws a Circle
     * 
     * @param myArray    
     */
    @Override 
    public void draw(JLabel[][] myArray){
        Toolkit.drawCircle(myXCoordinate, myYCoordinate, radius, myColor, myArray);

    }
}
