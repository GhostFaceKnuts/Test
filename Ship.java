/**
 * Write a description of class Ship here.
 * 
 * @author Ian Knutson 
 * @version Nov 17, 2014
 * 
 * @param shipType is a String describing the type of ship that is being placed.
 * @param hitPoints is the number of hits the ship can take, and how many grid squares it occupies
 * @param sunk is a boolean indicating whether the ship has been sunk or not. True means the ship has been sunk.
 */
public class Ship
{
    // instance variables - replace the example below with your own
    public String shipType;
    public int hitPoints;
    public boolean sunk;

    /**
     * Constructor for objects of class Warship
     */
    public Ship(String type, int hp)
    {
        // initialise instance variables
        this.shipType = type;
        this.hitPoints = hp;
        this.sunk = false;
    }
    
    //get Methods --------------------------------------------------------------------------
    
    /**
     * getShipType returns the string that describes which ship the player is placing
     * 
     * @return shipType returns the kind of ship it is.
     */
    public String getShipType()
    {
        String s = this.shipType;
        return s;
    }//end getShipType()
    
    /**
     * getHitPoints returns an integer that indicates how many hits the ship has taken, 
     * or has left before it is sunk.
     * 
     * @return shipType returns the kind of ship it is.
     */
    public int getHitPoints(String shipType)
    {
        return this.hitPoints;
    }//end getHitpoints()
    
    /**
     * getSunkBool returns whether the ship has been sunk or not.
     * 
     * @return sunk boolean.
     */
    public boolean getSunkBool()
    {
        return this.sunk;
    }//end getSunkBool()
    
    //set Methods --------------------------------------------------------------------------
    
    /**
     * setHitPoints sets an integer that indicates how many hits the ship has taken, 
     * or has left before it is sunk.
     *  
     */
    public void setHitPoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }//end setHitpoints()
    
    /**
     * setSunkBool sets the boolean indicating whether the ship has been sunk or not.
     * 
     * @return sunk boolean.
     */
    public void setSunkBool()
    {
        this.sunk = true;
    }//end setSunkBool()
}