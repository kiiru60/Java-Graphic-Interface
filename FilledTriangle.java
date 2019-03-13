import java.awt.*;
import javax.swing.*;

/**
 * public class FilledTriangle
 *
 * @author Alex Kiiru
 * @version 12/16/18
 */
public class FilledTriangle extends AbstractShape
{
    protected int myWidth;
    protected int myHeight;
    public static final int DEFAULT_WIDTH = 15;
    public static final int DEFAULT_HEIGHT = 15;
    /**
     * Constructor for objects of class FilledTriangle
     */

    FilledTriangle(){
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Constructor for objects of class FilledTriangle
     * @param x  for x coordinate of the center of the rectangle
     * @param y  for y coordinate of the center of the rectangle
     * @param shapeColor    
     * @param width 
     * @param height 
     */
    public FilledTriangle(int x, int y, Color shapeColor, int width, int height)
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
        String shape = "Rectangle";
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
     * @param shapeVar    
     */
    public FilledTriangle(String shapeVar){
        this();
        if(shapeVar.contains("Filled Triangle")){
            String Array[] = shapeVar.split(" ");
            myXCoordinate = Integer.parseInt(Array[2]);
            myXCoordinate = Integer.parseInt(Array[3]);
            myColor = Toolkit.stringToColor(Array[4]);
            myWidth = Integer.parseInt(Array[5]);
            myHeight = Integer.parseInt(Array[6]);
        }
    }

    /**
     * Draws a FilledTriangle
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
        int shapeWidth = myWidth;
        for (int i = 0; i < shapeWidth; i++){
            // bottom left vertice to the top vertice
            Toolkit.drawLine(myXCoordinate -  myWidth/2, myXCoordinate +  myWidth/2, myXCoordinate, myXCoordinate - myHeight/2, myColor, 
                myArray);

            // top vertice to the right vertice
            Toolkit.drawLine(myXCoordinate, myXCoordinate - myHeight/2, myXCoordinate + myWidth/2, myXCoordinate + myHeight/2, myColor, 
                myArray);

            // left vertice to the right vertice
            Toolkit.drawLine(myXCoordinate - myWidth/2, myXCoordinate + myWidth/2, myXCoordinate + myWidth/2,  myXCoordinate + myHeight/2, myColor, 
                myArray);
            myWidth--; 
            myHeight--;
        }
    }
}
