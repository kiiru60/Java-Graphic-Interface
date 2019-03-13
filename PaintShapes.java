import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
/**
 * public class PaintShapes
 *
 * @authorAlex Kiiru
 * @version 12/12/18
 */
public class PaintShapes extends WindowManager
{
    // Some useful constants.
    private final int NUM_ROWS = 100;    // # of rows in the layout for colored labels
    private final int NUM_COLS = 100;    // # of cols in the layout for colored labels
    private final int PAD      = 20;   // amount of padding around the grid

    // Graphical user interface components we will need to access later.
    private JComboBox  myColorComboBox;
    private JComboBox  myShapeComboBox;
    private JTextField myXCordField;
    private JTextField myYCordField;
    private JLabel     myXCordLabel;
    private JLabel     myYCordLabel;
    private JButton    myClearButton;
    private JButton    myLoadButton;
    private JButton    mySaveButton;
    private JButton    myQuitButton;
    private JButton    myAddShapeButton;
    private JButton    myUndoButton;

    private JLabel[][] myCanvas;   // The drawing area

    // Instance variables related to the state of the application
    //current color
    public Color      myCurrentColor; 
    //current x andy cordinates 
    private int myCurrentX;    
    private int  myCurrentY; 
    
    //current stored shape
    private String myCurrentShape; 

    //current background color
    private Color defaultBackground;

    //setting an arraylist to store shapes 
    private ArrayList<AbstractShape> myShapeStore; 
    /**
     * Constructor for PaintShapes objects.
     */
    public PaintShapes()
    {
        // call the parent class constructor to create and display the window
        super( "Paint Shapes", 750, 750 );

        // use a border (geographic) layout 
        setLayout( new BorderLayout() );

        //ComboBox For Colors
        String[] colorOptions = { "black","blue","cyan","dark gray","gray",
                "green","light gray","magenta","orange",
                "pink","red","white","yellow" };
        myColorComboBox = new JComboBox( colorOptions );
        myColorComboBox.setName("Colors");               //Naming the color combo box so we can use it later
        myColorComboBox.setMaximumRowCount( colorOptions.length );
        myColorComboBox.addActionListener( this );       //make it listen for the mouse

        //ComboBox For Shape
        String[] shapeOptions = { "Rectangle", "Filled Rectangle", "Square" , "Filled Square", "Circle", 
            "Filled Circle", "Triangle","Filled Triangle"};
        myShapeComboBox = new JComboBox( shapeOptions );
        myShapeComboBox.setName("Shapes");               //Naming the shape combo box so we can use it later
        myShapeComboBox.setMaximumRowCount( shapeOptions.length );
        myShapeComboBox.addActionListener( this );

        //Buttons
        myClearButton = new JButton( "Clear" );
        myClearButton.addActionListener( this );    // make it listen for mouse clicks
        myLoadButton = new JButton( "Load Image" );
        myLoadButton.addActionListener( this );    // make it listen for mouse clicks
        mySaveButton = new JButton( "Save Image" );
        mySaveButton.addActionListener( this );    // make it listen for mouse clicks
        myQuitButton = new JButton( "Quit" );
        myQuitButton.addActionListener( this );    // make it listen for mouse clicks
        myAddShapeButton = new JButton( "Add Shape" );
        myAddShapeButton.addActionListener( this );// make it listen for mouse clicks
        myUndoButton = new JButton( "Undo" );
        myUndoButton.addActionListener( this );    // make it listen for mouse clicks

        //JTextField for X and Y Coordinates
        myXCordField = new JTextField( 5 );        
        myXCordField.addKeyListener( this );
        myYCordField = new JTextField ( 5 );       
        myYCordField.addKeyListener( this );

        //JLabel for X and Y Coordinates
        myXCordLabel = new JLabel ( "Center X Coord" );
        myYCordLabel = new JLabel (" Center Y Coord" );

        //Initializing the JTextFields to be zero
        myXCordField.setText(Integer.toString(0));
        myYCordField.setText(Integer.toString(0));

        // setting up the pane for  text field, button, and lone-label
        JPanel southPanel = new JPanel();
        JPanel northPanel = new JPanel();

        // recall that by default components are added left to right, so add
        // the text field, button, and label to the panel
        southPanel.add( myClearButton ); 
        southPanel.add( myAddShapeButton );
        southPanel.add( myUndoButton );
        southPanel.add( myLoadButton );
        southPanel.add( mySaveButton );
        southPanel.add( myQuitButton );
        southPanel.add( myQuitButton );

        //Adding to North Panel
        northPanel.add( myColorComboBox );
        northPanel.add( myShapeComboBox );
        northPanel.add( myXCordLabel );
        northPanel.add( myXCordField );
        northPanel.add( myYCordLabel );
        northPanel.add( myYCordField );

        // add the panel to the south region of the window
        add( northPanel, BorderLayout.NORTH );
        add( southPanel, BorderLayout.SOUTH );

        // construct a panel to hold the click-to-color labels
        JPanel gridPanel = new JPanel();

        // set its layout to be a 7X7 grid and draw an empty padding border around
        gridPanel.setLayout( new GridLayout( NUM_ROWS, NUM_COLS ) );
        gridPanel.setBorder( 
            BorderFactory.createEmptyBorder( 10,25,0,0 ) );

        // construct the array
        myCanvas = new JLabel[NUM_ROWS][NUM_COLS];

        // then walk through the array one-by-one...
        for (int i = 0; i < myCanvas.length; i++)
        {
            for (int j = 0; j < myCanvas[i].length; j++)
            {
                // and construct each JLabel in the array
                myCanvas[i][j] = new JLabel();

                // add it to the panel
                gridPanel.add( myCanvas[i][j] );

                // make that background color show up (opaque becomes true)
                myCanvas[i][j].setBackground( null );
                myCanvas[i][j].setOpaque( true );

                // make each JLabel listen for mouse clicks
                myCanvas[i][j].addMouseListener( this );
            }
        }

        // add the panel of JLabels to the center of the window
        add( gridPanel, BorderLayout.CENTER );

        //Setting instance variables to some default values
        myCurrentColor = Color.black;
        myCurrentShape = "Rectangle";

        // currenty x and y coordinates
        myCurrentX = 50;
        myCurrentY = 50;

        //setting up background color 
        defaultBackground = myCanvas[0][0].getBackground();

        //allocating space to the arrayList 
        myShapeStore = new ArrayList<AbstractShape>();
    }

    /**
     * Called whenever a JLabel instance is clicked in the application.
     */
    @Override
    public void labelClicked( JLabel whichLabel )
    {
        
        for (int i = 0; i < myCanvas.length; i++)
        {
            for (int j = 0; j < myCanvas[i].length; j++)
            {
                if(myCanvas[i][j] == whichLabel){
                    myYCordField.setText(Integer.toString(i));
                    myCurrentX = j;
                    myXCordField.setText(Integer.toString(j));  
                    myCurrentY = i;
                }
            }
        }

    }

    /**
     * This method is called whenever the use clicks on a particular
     * JButton.  
     */
    @Override
    public void buttonClicked( JButton whichButton )
    {
        
        if ( whichButton.getText().equals(myClearButton.getText()) )
        {
            
            clearImage();
            //System.out.println("Clear Button was clicked");
        }
        else if (whichButton.getText().startsWith(myUndoButton.getText())){
            
            undoImage();
            //System.out.println("undo button was clicked");
        }
        else if (whichButton.getText().startsWith(myLoadButton.getText()))
        {
            
            loadImage();
            //System.out.println("load button was clicked");
        }
        else if (whichButton.getText().startsWith(mySaveButton.getText()))
        {
            
            saveImage();
        }
        else if (whichButton.getText().startsWith(myAddShapeButton.getText()))
        {

            AbstractShape myShape = nameToShape(); 
            myShapeStore.add(myShape);             
            redrawShapes();                        
            //System.out.println(myShape.toString());
            //System.out.println("AddShapeButton was clicked");
            //System.out.println(myShapeStore);
        }
        else if (whichButton.getText().equals(myQuitButton.getText()))
        {
           
            System.exit(0);
        }
    }

    /**
     * Clears the drawing area to reflect its initial state.
     */
    public void clearImage()
    {
        
        for (int i = 0; i < myShapeStore.size(); i++){
            myShapeStore.get(i).setColor(defaultBackground);
        }
        redrawShapes();         //changing color to background color
        myShapeStore.clear();   //Clearing the shape array
        
    }

    /**
     * Clears the drawing area to reflect its initial state.
     */
    public void undoImage(){
        //Gets the last index of the array and sets to default background
        myShapeStore.get(myShapeStore.size()-1).setColor(defaultBackground);
        redrawShapes();        
        myShapeStore.remove(myShapeStore.size()-1); 
    }

    /**
     * Allow the user to select an image to load.
     */
    public void loadImage()
    {
        JFileChooser chooser = new JFileChooser( new File(".") );
        int value = chooser.showOpenDialog( this );
        if (value == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();

            try
            {
                Scanner infile = new Scanner( new FileReader( file ) );

                //While the file has lines to read
                while(infile.hasNextLine()){
                    String line = infile.nextLine(); 
                    if(line.startsWith("Rectangle")){ 
                        AbstractShape shape = new Rectangle(line); 
                        myShapeStore.add(shape);//add to the array
                    }
                    else if(line.contains("Filled Rectangle")){ 
                        AbstractShape shape = new FilledRectangle(line);
                        myShapeStore.add(shape);//add to the array
                    }
                    else if(line.startsWith("Square")){ 
                        AbstractShape shape = new Square(line);
                        myShapeStore.add(shape);//add to the array
                    }
                    else if(line.contains("Filled Square")){ 
                        AbstractShape shape = new FilledSquare(line);
                        myShapeStore.add(shape);//add to the array
                    }
                    else if(line.startsWith("Circle")){ 
                        AbstractShape shape = new Circle(line);
                        myShapeStore.add(shape);//add to the array
                    }
                    else if(line.contains("Filled Circle")){ 
                        AbstractShape shape = new FilledCircle(line);
                        myShapeStore.add(shape);//add to the array
                    }
                    else if(line.startsWith("Triangle")){
                        AbstractShape shape = new Triangle(line);
                        myShapeStore.add(shape);//add to the array
                    }
                }
                redrawShapes(); //redraw the shapes
                infile.close(); 
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Save the current drawing to a file selected by the user.
     */
    public void saveImage()
    {
        JFileChooser chooser = new JFileChooser( new File(".") );
        int value = chooser.showSaveDialog( this );
        if (value == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            try
            {
                PrintWriter outputFile = new PrintWriter( new File( file.getName() ) );

                //gets the string stored in arrsy to output
                for (int i = 0; i < myShapeStore.size(); i++){
                    outputFile.println(myShapeStore.get(i).toString());
                }

                outputFile.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Record changes in drop-down menus.
     */
    public void selectionMade( JComboBox whichComboBox )
    {
        //Setting the color and shape to default values
        String color = "Black";
        String shape = "Rectangle";
        if (whichComboBox.getName().equals("Shapes")){ 
            shape = (String) whichComboBox.getSelectedItem(); 
            myCurrentShape = shape; 
            //System.out.println(shape + " was selected.");
        }

        else if(whichComboBox.getName().equals("Colors")){ 
            color = (String) whichComboBox.getSelectedItem();
            myCurrentColor = Toolkit.stringToColor(color); 
            //System.out.println(color + " was selected.");
        }

        //System.out.println(myCurrentColor);
    }

    /**
     * Creates a new shape object based on the current selected color, shape, and 
     * x and y coordinates.
     * @return AbstractShape that was constructed 
     */
    public AbstractShape nameToShape(){
        try{
            if(myCurrentShape.equals("Rectangle")){
                String result1 = JOptionPane.showInputDialog(this,"Enter the width of the rectangle:");
                int width = Integer.parseInt(result1);
                String result2 = JOptionPane.showInputDialog(this,"Enter the height of the rectangle:");
                        
                int height = Integer.parseInt(result2);
                Rectangle myNewshape = new Rectangle(myCurrentX , myCurrentY, myCurrentColor , width , height);
                return myNewshape;
            }
            else if(myCurrentShape.equals("Filled Rectangle")){
                String result1 = JOptionPane.showInputDialog(this,"Enter the width of the rectangle:");
                        
                int width = Integer.parseInt(result1);
                String result2 = JOptionPane.showInputDialog(this,"Enter the height of the rectangle:");
                        
                int height = Integer.parseInt(result2);
                FilledRectangle myNewshape = new FilledRectangle(myCurrentX , myCurrentY, myCurrentColor , width , height);
                return myNewshape;
            }
            else if(myCurrentShape.equals("Square")){
                String result1 = JOptionPane.showInputDialog(this,"Enter the width of the square:");
                        
                int width = Integer.parseInt(result1);
                Square myNewshape = new Square(myCurrentX , myCurrentY, myCurrentColor , width);
                return myNewshape;
            }
            else if(myCurrentShape.equals("Filled Square")){
                String result1 = JOptionPane.showInputDialog(this,"Enter the width of the square:");
                        
                int width = Integer.parseInt(result1);
                FilledSquare myNewshape = new FilledSquare(myCurrentX , myCurrentY, myCurrentColor , width);
                return myNewshape;
            }
            else if(myCurrentShape.equals("Circle")){
                String result1 = JOptionPane.showInputDialog(this, "Enter the radius of the circle:");
                       
                int radius = Integer.parseInt(result1);
                Circle myNewshape = new Circle(myCurrentX , myCurrentY, myCurrentColor , radius);
                return myNewshape;
            }
            else if(myCurrentShape.equals("Filled Circle")){
                String result1 = JOptionPane.showInputDialog(this,"Enter the radius of the circle:");
                        
                int radius = Integer.parseInt(result1);
                FilledCircle myNewshape = new FilledCircle(myCurrentX , myCurrentY, myCurrentColor , radius);
                return myNewshape;
            }
            else if(myCurrentShape.equals("Triangle")){
                String result1 = JOptionPane.showInputDialog(this,"Enter the width of the triangle:");
                        
                int width = Integer.parseInt(result1);
                String result2 = JOptionPane.showInputDialog(this,"Enter the perpendicular height of the triangle:");
                        
                int height = Integer.parseInt(result2);
                Triangle myNewshape = new Triangle(myCurrentX , myCurrentY, myCurrentColor , width , height);
                return myNewshape;
            } else if(myCurrentShape.equals("Filled Triangle")){
                String result1 = JOptionPane.showInputDialog(this,"Enter the width of the triangle:");
                        
                int width = Integer.parseInt(result1);
                String result2 = JOptionPane.showInputDialog(this,"Enter the perpendicular height of the triangle:");
                        
                int height = Integer.parseInt(result2);
                FilledTriangle myNewshape = new FilledTriangle(myCurrentX , myCurrentY, myCurrentColor , width , height);
                return myNewshape;
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Please enter a valid input.");
        }
        return null;
    }

    /**
     * Redraws the shapes present in the shape store array 
     */
    public void redrawShapes(){

        //calling the redrawShapes method for each shape
        for (int i = 0; i < myShapeStore.size(); i++){
            myShapeStore.get(i).draw(myCanvas);
            //System.out.println("redrawShapes was called ");
        }
        repaint();

    }
}
