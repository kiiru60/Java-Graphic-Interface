import java.awt.*;
import javax.swing.*;

/**
 * public class Triangle
 *
 * @author Alex Kiiru
 * @version 12/15/18
 */
public class Triangle extends AbstractShape
{
    protected int myWidth;
    protected int myHeight;
    public static final int DEFAULT_WIDTH = 30;
    public static final int DEFAULT_HEIGHT = 30;
    /**
     * Constructor for objects of class Triangle
     */

    Triangle(){
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    /**
     * Constructor for objects of class Triangle
     * @param x  for x coordinate of the center of the rectangle
     * @param y for  y coordinate of the center of the rectangle
     * @param shapeColor    
     * @param  width 
     * @param height 
     */
    public Triangle(int x, int y, Color shapeColor, int width, int height)
    {
        super(x,y,shapeColor);
        myWidth = width;
        myHeight = height;
    }
    
    /**
     * Returns a string containing information about the shape such as the type,
     * the x and y coordinates, the color, and the width and height
     * @return String of shape information
     */
    public String toString(){
        String shape = "Triangle";
        String x = Integer.toString(myXCoordinate);
        String y = Integer.toString(myYCoordinate);
        String color = Toolkit.colorToString( myColor );
        String width = Integer.toString(myWidth);
        String height = Integer.toString(myHeight);
        String shapeVar = (shape + " " + x + " " + y + " " + color + " " + width + " " + height);
        return shapeVar;
    }

    /**
     * Constructor for objects of class FilledRectangle
     * @param shapeData    
     */
    public Triangle(String shapeVar){
        this();
        if(shapeVar.contains("Rectangle")){
            String Array[] = shapeVar.split(" ");
            myXCoordinate = Integer.parseInt(Array[1]);
            myYCoordinate = Integer.parseInt(Array[2]);
            myColor = Toolkit.stringToColor(Array[3]);
            myWidth = Integer.parseInt(Array[4]);
            myHeight = Integer.parseInt(Array[5]);
        }
    }

    /**
     * Draws a Triangle
     * 
     * @param myArray   
     */
    @Override public void draw(JLabel[][] myArray){
        int xCordLeft = myXCoordinate - myWidth/2;
        int yCordLeft = myYCoordinate + myWidth/2;
        int xCordMiddle = myXCoordinate;
        int yCordMiddle = myYCoordinate - myHeight/2;
        int xCordRight = myXCoordinate + myWidth/2;
        int yCordRight = myYCoordinate + myHeight/2;

        //Drawing the line from the bottom left vertice to the top vertice
        Toolkit.drawLine(xCordLeft, yCordLeft, xCordMiddle, yCordMiddle, myColor, 
            myArray);

        //Drawing the line from the top vertice to the right vertice
        Toolkit.drawLine(xCordMiddle, yCordMiddle, xCordRight, yCordRight, myColor, 
            myArray);
            
        //Drawing the line from the left vertice to the right vertice
        Toolkit.drawLine(xCordLeft, yCordLeft, xCordRight, yCordRight, myColor, 
            myArray);
               

    }
}
