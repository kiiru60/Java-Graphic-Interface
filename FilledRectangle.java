import java.awt.*;
import javax.swing.*;

/**
 * public class FilledRectangle
 *
 * @author Alex Kiiru
 * @version 12/12/18
 */
public class FilledRectangle extends Rectangle
{
    /**
     * Constructor for objects of class FilledRectangle
     */
    public FilledRectangle()
    {
        this(DEFAULT_X,DEFAULT_Y,DEFAULT_COLOR,DEFAULT_width, DEFAULT_height);
    }

    /**
     * Constructor for objects of class FilledRectangle
     * @param x for x coordinate is the center of the rectangle
     * @param y for y coordinate is center of the rectangle
     * @param shapeColor 
     * @param width 
     * @param height 
     */
    public FilledRectangle(int x,int y,Color shapeColor ,int width,int height){
        //Using the variables from the Rectangle parent class
        super(x,y,shapeColor,width,height);
    }

    /**
     * Constructor for objects of class FilledRectangle
     * @param shapeVar
     */
    public FilledRectangle(String shapeVar){

        this();
        if(shapeVar.contains("Rectangle")){
            //Splitting the string by the spaces in between the strings
            String Array[] = shapeVar.split(" ");
            //puts the strings into their respective proper values
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
     * @return String of shape information
     */
    public String toString(){
        //
        String rectVar = super.toString();
        String shapeVar = rectVar.replace("Rectangle","Filled Rectangle");
        return shapeVar;
    }

    /**
     * Draws a filled in rectangle
     * 
     * @param myArray    
     */
    @Override 
    public void draw(JLabel[][] myArray){
        int startRow = myYCoordinate - myHeight/2;
        int startCol = myXCoordinate - myWidth/2;

        int endRow = myYCoordinate + myHeight/2;
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
