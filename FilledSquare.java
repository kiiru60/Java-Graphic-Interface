import java.awt.*;
import javax.swing.*;

/**
 * public class FilledSquare
 *
 * @author Alex Kiiru
 * @version 12/16/18
 */
public class FilledSquare extends Rectangle
{
    /**
     * Constructor for objects of class FilledSquare
     */
    public FilledSquare()
    {
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR, DEFAULT_width );
    }

    /**
     * Constructor for objects of class FilledSquare
     * @param x for x coordinate of the center of the rectangle
     * @param y for y coordinate of the center of the rectangle
     * @param shapeColor    
     * @param width 
     */
    public FilledSquare(int x, int y, Color shapeColor, int width){
        
        myXCoordinate = x;
        myYCoordinate = y;
        myColor = shapeColor;
        width = width;
        
    }
    
    /**
     * Constructor for objects of class FilledSquare
     * @param shapeVar    
     */
    public FilledSquare(String shapeVar){
        if(shapeVar.contains("Filled Square")){
            String Array[] = shapeVar.split(" ");
            myXCoordinate = Integer.parseInt(Array[2]);
            myYCoordinate = Integer.parseInt(Array[3]);
            myColor = Toolkit.stringToColor(Array[4]);
            myWidth = Integer.parseInt(Array[5]);
            myHeight = Integer.parseInt(Array[6]);
        }
    }
    
    /**
     * Returns a string containing information about the shape such as the type,
     * the x and y coordinates, the color, the width, and the height
     * @return String of shape 
     */
    public String toString(){
        String shape = "Filled Square";
        String x = Integer.toString(myXCoordinate);
        String y = Integer.toString(myYCoordinate);
        String color = Toolkit.colorToString( myColor );
        String width = Integer.toString(myWidth);
        String height = Integer.toString(myHeight);
        String shapeVar = (shape + " " + x + " " + y + " " + color + " " + width + " " + height);
        return shapeVar;
    }

    /**
     * Draws a filled Square
     * 
     * @param myArray    
     */
    @Override public void draw(JLabel[][] myArray){
        //setting up rows and cols 
        int startRow = myYCoordinate - myWidth/2;
        int startCol = myXCoordinate - myWidth/2;

        int endRow = myYCoordinate + myWidth/2;
        int endCol = myXCoordinate + myWidth/2;

        for (int i = startRow; i <= endRow; i++){
            for (int j = startCol; j < endCol; j++){
                if(i >= 0 && i < myArray.length && j >=0 && j < myArray[0].length){
                    myArray[i][j].setBackground (myColor);
                }
            }
        }
    }
}
