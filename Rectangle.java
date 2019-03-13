import java.awt.*;
import javax.swing.*;

/**
 * public class Rectangle
 *
 * @author Alex Kiiru
 * @version 12/16/18
 */
public class Rectangle extends AbstractShape
{
    //instance variables
    protected int myWidth;
    protected int myHeight;
    public static final int DEFAULT_width = 20;
    public static final int DEFAULT_height = 20;
    //public static final Color DEFAULT_COLOR; 
    //private JLabel[][] myArray;
    /**
     * Constructor for objects of class Rectangle
     */
    Rectangle(){
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR, DEFAULT_width, DEFAULT_height);
    }

    /**
     * Constructor for objects of class Rectangle
     * @param x for x coordinate of the center of the rectangle
     * @param y for  y coordinate of the center of the rectangle
     * @param shapeColor    
     * @param width    
     * @param height    
     */
    public Rectangle(int x, int y, Color shapeColor, int width, int height)
    {
        super(x,y,shapeColor);
        myWidth = width;
        myHeight = height;
    }

    
    /**
     * Constructor for objects of class FilledRectangle
     * @param shapeData    String with information about the shape
     */
    public Rectangle(String shapeVar){
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
     * Draws a filled in rectangle
     * 
     * @param myArray    
     */
    @Override public void draw(JLabel[][] myArray){
        //setting the rows and columns 
        int startRow = myYCoordinate - myHeight/2;
        int startCol = myXCoordinate - myWidth/2;

        int endRow = myYCoordinate + myHeight/2;
        int endCol = myXCoordinate + myWidth/2;

        for (int j = startRow; j <= endRow; j++){
            if(j >= 0 && j < myArray.length && startCol >= 0){
                myArray[j][startCol].setBackground( myColor );
            }
            if(j < myArray.length && j >= 0 && endCol < myArray.length ){
                myArray[j][endCol].setBackground( myColor );

            }
        }

        for (int i = startCol; i < endCol; i++){
            if(i >= 0 && i < myArray[0].length && startRow >= 0){
                myArray[startRow][i].setBackground( myColor );
            }
            if(i >= 0 && i < myArray[0].length && endRow < myArray.length){
                myArray[endRow][i].setBackground( myColor );
            }
        }

    }

    /**
     * 
     * @return String of shape contents 
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
}
