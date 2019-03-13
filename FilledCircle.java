import java.awt.*;
import javax.swing.*;

/**
 * public class FilledCircle
 *
 * @author Alex Kiiru
 * @version 12/16/18
 */
public class FilledCircle extends AbstractShape
{
    protected int radius;
    public static final int DEFAULT_RADIUS = 30;
    /**
     * Constructor for objects of class FilledCircle
     */

    public FilledCircle(){
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR, DEFAULT_RADIUS);

    }

    /**

     * @param x for x coordinate of the center of the rectangle
     * @param y for y coordinate of the center of the rectangle
     * @param shapeColor    
     * @param r    
     */
    public FilledCircle(int x, int y, Color shapeColor, int r)
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
        String shape = "Filled Circle";
        String x = Integer.toString(myXCoordinate);
        String y = Integer.toString(myYCoordinate);
        String color = Toolkit.colorToString( myColor );
        String myRadius = Integer.toString(radius);
        String shapeData = (shape + " " + x + " " + y + " " + color + " " + myRadius);
        return shapeData;
    }

    /**
     * Constructor for objects of class FilledCircle
     * @param shapeVar    
     */
    public FilledCircle(String shapeVar){
        this();
        if(shapeVar.contains("Filled Circle")){
            String Array[] = shapeVar.split(" ");
            myXCoordinate = Integer.parseInt(Array[2]);
            myYCoordinate = Integer.parseInt(Array[3]);
            myColor = Toolkit.stringToColor(Array[4]);
            radius = Integer.parseInt(Array[5]);
        }
    }

    /**
     * Draws a FilledCircle
     * 
     * @param myArray   
     */
    @Override public void draw(JLabel[][] myArray){
        //setting up cordinate parameters
        int xTopLeft = myXCoordinate - radius/2;
        int yTopLeft = myYCoordinate - radius/2;
        int xBottomRight = myXCoordinate + radius/2;
        int yBottomRight = myYCoordinate + radius/2;
        int xTopRight = myXCoordinate + radius/2;
        int yTopRight = myYCoordinate - radius/2;
        int xBottomLeft = myXCoordinate - radius/2;
        int yBottomLeft = myYCoordinate + radius/2;
        int myRadius = radius;
        for(int j = 0; j < 2; j++){
            for (int i = 0; i < radius; i++){
                Toolkit.drawCircle(myXCoordinate, myYCoordinate, radius - i, myColor, myArray);
            }

            for (int i = 0; i < radius; i++){
                Toolkit.drawCircle(myXCoordinate - i, myYCoordinate, radius - i, myColor, myArray);
            }

            for (int i = 0; i < radius; i++){
                Toolkit.drawCircle(myXCoordinate + i, myYCoordinate, radius - i, myColor, myArray);
            }

            for (int i = 0; i < radius; i++){
                Toolkit.drawCircle(myXCoordinate, myYCoordinate - i, radius - i, myColor, myArray);
            }

            for (int i = 0; i < radius; i++){
                Toolkit.drawCircle(myXCoordinate, myYCoordinate + i, radius - i, myColor, myArray);
            }
            Toolkit.drawLine(xTopRight, yTopRight, xBottomLeft, yBottomLeft, myColor, 
                myArray);
            Toolkit.drawLine(xTopLeft, yTopLeft, xBottomRight, yBottomRight, myColor, 
                myArray);
            radius--;
        }
    }
}
