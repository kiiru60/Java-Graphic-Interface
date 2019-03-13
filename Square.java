import java.awt.*;
import javax.swing.*;

/**
 * public class Square
 *
 * @author Alex Kiiru
 * @version 12/16/18
 */
public class Square extends Rectangle
{
    /**
     * Constructor for objects of class Square
     */
    public Square()
    {
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR, DEFAULT_width );
    }

    /**
     * Constructor for objects of class Square
     * @param x    
     * @param y    
     * @param shapeColor    
     * @param width    
     */
    public Square(int x, int y, Color shapeColor, int width){
        myXCoordinate = x;
        myYCoordinate = y;
        myColor = shapeColor;
        myWidth = width;

    }

    /**
     * Constructor for objects of class Square
     * @param shapeVar    
     */
    public Square(String shapeVar){

        if(shapeVar.contains("Square")){
            String Array[] = shapeVar.split(" ");
            myXCoordinate = Integer.parseInt(Array[1]);
            myYCoordinate = Integer.parseInt(Array[2]);
            myColor = Toolkit.stringToColor(Array[3]);
            myWidth = Integer.parseInt(Array[4]);
            myHeight = Integer.parseInt(Array[5]);
        }
    }

    /**
     * Draws a Square
     * 
     * @param myArray    
     */
    @Override public void draw(JLabel[][] myArray){
        int startRow = myYCoordinate - myWidth/2;
        int startCol = myXCoordinate - myWidth/2;

        int endRow = myYCoordinate + myWidth/2;
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
     * Returns a string containing  the type of shape 

     * @return String of shape contents
     */
    public String toString(){
        String shape = "Square";
        String x = Integer.toString(myXCoordinate);
        String y = Integer.toString(myYCoordinate);
        String color = Toolkit.colorToString( myColor );
        String width = Integer.toString(myWidth);
        String height = Integer.toString(myHeight);
        String shapeVar = (shape + " " + x + " " + y + " " + color + " " + width + " " + height);
        return shapeVar;
    }
}
