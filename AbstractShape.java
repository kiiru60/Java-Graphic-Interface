import java.awt.*;
import javax.swing.*;
/**
 * Abstract class AbstractShape
 *
 * @author Alex Kiiru
 * @version 12/16/18
 */
 public abstract class AbstractShape
{
    // declaring variables
    protected int myXCoordinate;
    protected int myYCoordinate;
    protected Color myColor;
    protected static final Color DEFAULT_COLOR = Color.black;
    protected static final int DEFAULT_X = 0;
    protected static final int DEFAULT_Y = 0;
    
    /**
     * Constructor for AbstractShape
     * @param x for x coordinate of the center of the shape
     * @param y  for y coordinate of the center of the shape
     * @param shapeColor    
     */
    public AbstractShape(int x, int y, Color shapeColor)
    {
        myXCoordinate = x;
        myYCoordinate = y;
        myColor = shapeColor;
    }
    
    /**
     * Default constructor for AbstractShape
     */
    AbstractShape(){
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_COLOR);
    }
    
    /**
     * Abstract method for drawing shapes that is overwritten by subclasses
     * 
     * @param myArray    
     */
    public abstract void draw(JLabel[][] myArray);
    
    /**
     * sets the color of a shape currently being drawn
     * 
     * @param newColor   
     */
    public void setColor(Color newColor){
        myColor = newColor;
    }

}
