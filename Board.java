
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Board
{
    //Game Boards
    private String[][] pBoard= {
        
            {"   ","1","2","3","4","5","6","7","8","9","10"},
            {"A |","~","~","~","~","~","~","~","~","~","~","|"},
            {"B |","~","~","~","~","~","~","~","~","~","~","|"},
            {"C |","~","~","~","~","~","~","~","~","~","~","|"},
            {"D |","~","~","~","~","~","~","~","~","~","~","|"},
            {"E |","~","~","~","~","~","~","~","~","~","~","|"},
            {"F |","~","~","~","~","~","~","~","~","~","~","|"},
            {"G |","~","~","~","~","~","~","~","~","~","~","|"},
            {"H |","~","~","~","~","~","~","~","~","~","~","|"},
            {"I |","~","~","~","~","~","~","~","~","~","~","|"},
            {"J |","~","~","~","~","~","~","~","~","~","~","|"},
        
    };
    
    private String[][] cBoard= {
            {"   ","1","2","3","4","5","6","7","8","9","10"},
            {"A |","~","~","~","~","~","~","~","~","~","~","|"},
            {"B |","~","~","~","~","~","~","~","~","~","~","|"},
            {"C |","~","~","~","~","~","~","~","~","~","~","|"},
            {"D |","~","~","~","~","~","~","~","~","~","~","|"},
            {"E |","~","~","~","~","~","~","~","~","~","~","|"},
            {"F |","~","~","~","~","~","~","~","~","~","~","|"},
            {"G |","~","~","~","~","~","~","~","~","~","~","|"},
            {"H |","~","~","~","~","~","~","~","~","~","~","|"},
            {"I |","~","~","~","~","~","~","~","~","~", "~","|"},
            {"J |","~","~","~","~","~","~","~","~","~","~","|"},
        
    };
    
    //ArrayLists to store ship position and shots taken
    ArrayList pCoordinates = new ArrayList();
    ArrayList cCoordinates = new ArrayList();
    ArrayList pShotsTaken = new ArrayList();
    ArrayList cShotsTaken = new ArrayList();
    
    //scanner for taking commands
    Scanner input = new Scanner(System.in);
    
    //booleans for determining valid locations and bearings
    public boolean validLocation = false;
    public boolean validBearing = false;
    
    //Array of Ships
    Ship[] pFleet = new Ship[2];
    Ship[] cFleet = new Ship[2];
    
    /**
     * Constructor for Board Class
     */
    public Board()
    {
        pFleet[0] = new Ship("Battleship",4); //shipType, hitPoints
        pFleet[1] = new Ship("Submarine",3);
        
        cFleet[0] = new Ship("Battleship",4);
        cFleet[1] = new Ship("Submarine",3);
    }
    
    /**
     * 
     */
    public void placeShips()
    {
        //variables for rudder locations and bearings
        String rudderY, bearing = "";
        int rudderX = 0;
        
        //Place Ships
        for(Ship s : pFleet)
        {
            String type = s.getShipType();
            
            //set ship rudder location
            do
            {
               rudderX = 0;
               rudderY = "";
               validLocation = false;
               
               //diplay grid
               System.out.printf("\f**************** BATTLESHIP ********************\n");
               System.out.printf("\n" + displayPlayerBoard());
                       
               //message string
               System.out.printf("\n\nCommander, please mark where the " + type + "'s" + " rudder should be positioned.\n");
               
               //get rudder location
               System.out.printf("Set course for Latitude (A-J)\n");
               rudderY = input.next().toUpperCase();
               System.out.printf("Set course for Longitude (1-10)\n");
               try
               {
                    rudderX = Integer.parseInt(input.next());
               }
               catch (NumberFormatException ex) 
               {
                    System.out.printf("\fSir, Longitude must be a number.");
                    wait(3);
               }
               validateLocation(rudderX, rudderY);
            }
            while (!validLocation);
            
            //set ship bearing
            do
            {
               bearing = "";
               validBearing = false;
               
               System.out.printf("Commander, please indicate the " + type + "'s" + " bearing. S or E?\n");
               bearing = input.next().toUpperCase();
               validateBearing(bearing);
            }
            while (!validBearing);
        }
        //ready message
        System.out.printf("Fleet formation confirmed sir! Ready for Battle\n");
    }
    
    /**
     * method checkIfHit() checks to see if the selection by the player or computer hit a ship
     */
    public void checkIfHit()
    {
        
    }
    
    /**
     * method validateLocation validates player input to determine whether the ship rudder can
     *        placed at that location.
     * 
     * @param x holds the input rudder location on x axis
     * @param y holds the input rudder location on y axis
     * @param validLocation is a boolean indicating whether location input is valid
     * @return validLocation is returned when validation has succeded.
     */
    public boolean validateLocation(int rx, String ry)
    {
        int x = rx;
        String y = ry;
        validLocation = false;
        
        if(x == 0)
        {
            System.out.println("\nLongitude not recieved. Please repeat.\n");
            wait(3);
            return validLocation;
        }
        else if(x < 0 || x > 10)
        {
            System.out.println("\fNegative sir, longitude is out of range.\n");
            wait(3);
            return validLocation;
        }
        else if(!validStr(ry))
        {
            System.out.printf("\fNegative sir, latitude is out of range.\n");
            wait(3);
        }
        else validLocation = true;
        
        return validLocation;
    }
    
    /**
     * method validateBearing() calls to other methods, such as validBearing(), shipCollision(), and shipOnGrid()
     *        to determine if input is valid. Depending on results, this method writes to the console to inform
     *        the player of errors, otherwise it returns Boolean validBearing as true.
     *        
     * @params b is a string that holds the bearing input string
     * @params validBearing is a boolean that is first set to false
     * @return validBearing is returned to indicate that validation of ships bearing has succeded.
     */
    public boolean validateBearing(String bearing)
    {
        String b = bearing;
        validBearing = false;
        
        if(!validBearing(b)) //input other than South or East "S" or "E"
        {
            System.out.printf("Negative sir, we must face or flank the battle grid in order to fire.\n");
            wait(3);
        }
        else if(shipCollision()) //ship positions collide with one another
        {
            System.out.printf("Negative sir, our ships would collide in this formation.\n");
            wait(3);
        }
        else if(!shipOnGrid()) //ship position is off the grid
        {
            System.out.printf("Negative sir, that bearing would take us out of the battle grid.\n");
            wait(3);
        }
        else validBearing = true;
        
        return validBearing;
      }
        
    /**
     * method displayPlayerBoard loops through the pBoard array and builds a string for display,
     * 
     * @params result is string built to return the contents of pBoard
     */
    public String displayPlayerBoard()
    {
        String result = "";
            
        for(int r=0; r < pBoard.length; r++)
        {
            result += "\n";
                
            for(int c=0; c < pBoard[r].length; c++)
            {
                
                result += pBoard[r][c]+ " ";
                
                
            } // for c
        } // for r
            return result;
    }
    
    /**
     * method displayComputerBoard loops through the cBoard array and builds a string for display
     * 
     * @params result is string built to return the contents of cBoard
     */ 
        public String displayComputerBoard()
        {
            String result = "";
            
            for(int r=0; r < cBoard.length; r++)
        {
            result += "\n";
                
            for(int c=0; c < cBoard[r].length; c++)
            {
                
                result += cBoard[r][c]+ " ";
                
                
            } //for c
        } // for r
            return result;
    }
    
     /**
     * method - drawBoard redraws the current status of each game board
     *  
     */
    public void drawBoard()
    {
        System.out.printf("\n***** Your Fleet *******");
        System.out.printf("\n---------------------------");
        getPlayerBoard();
        System.out.printf(displayPlayerBoard());
        System.out.printf("\n***** Enemy Fleet ******");
        System.out.printf("\n---------------------------");
        getComputerBoard();
        System.out.printf(displayComputerBoard());
    }
    
    /**
     * method validStr validates input to determine ships rudder location
     */
    public boolean validStr(String str)
    {
        String[] latitude = {"A","B","C","D","E","F","G","H","I","J"};  
        return (Arrays.asList(latitude).contains(str));
    }
    
    /**
     * method validBearing() validates input to determine ships bearing
     */
    public boolean validBearing(String ber)
    {
        String[] bearing = {"S","E"};  
        return (Arrays.asList(bearing).contains(ber));
    }
    
    /**
     * method shipCollision() is a check to see if the bearing of a ship causes it to collide with the other.
     */
    public boolean shipCollision()
    {
        boolean collisionDetected = false;
        
        return collisionDetected;
    }
    
    /**
     * method shipOnGrid() is a validation method to check if the ship was place on the battle grid
     */
    public boolean shipOnGrid()
    {
        boolean shipOnGrid = true;
        
        return shipOnGrid;
        
    }
    
    /**
     * method wait() causes a delay so messages can be read beforethey are cleared from the console. 
     */
    public void wait(int k){
          long time0, time1;
          time0 = System.currentTimeMillis();
          do{
          time1 = System.currentTimeMillis();
          }
          while (time1 - time0 < k * 1000);
      }
    
     /**
     * Method getPlayerBoard returns the updated board for human player
     */
    public String[][] getPlayerBoard()
    {
        return pBoard;
    }
    
    /**
     * Method getComputerBoard returns the updated board for computer player
     */
    public String [][] getComputerBoard()
    {
        return cBoard;
    }
}
