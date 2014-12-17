
/**
 * Write a description of class BattleShip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class BattleShip   
{
    //enum type
        private enum Status {WON, LOST, CONTINUE};
        private static String[] players;
        
    
    public static void main(String[] args)
    { 
        //initiate array
        players = new String[2];
        
        //add players to game
        players[0] = "Human";
        players[1] = "Computer";
        
        //play game
        playGame();
    }
    
    private static void playGame()
    {
        //variables
        int shotX = 0;
        String shotY = "";
        
        //scanner for taking commands
        Scanner input = new Scanner(System.in);
        
        //set game state to continue
        Status state = Status.CONTINUE;
        
        //create game Board
        Board board = new Board();
        
        //print out cool battleship art that I found on the interwebs
        System.out.printf("\n          WELCOME");
        board.wait(2); //delay for effect
        System.out.printf("\n                   TO");
        board.wait(3); //delay for effect
        System.out.printf("\n__________         __    __  .__           _________.__    .__        ");
        System.out.printf("\n\\______   \\_____ _/  |__/  |_|  |   ____  /   _____/|  |__ |__|_____  ");
        System.out.printf("\n |   | |  _/\\__  \\   __\\   __\\  | _/ __ \\ \\_____  \\ |  |  \\|  \\____ \\ ");
        System.out.printf("\n |   | |   \\ / __ \\|  |  |  | |  |_\\  ___/ /        \\|      \\  |  |_> >");
        System.out.printf("\n |______  /(____  /__|  |__| |____/\\___  >_______  /|___|  /__|   __/ ");
        System.out.printf("\n        \\/      \\/                     \\/        \\/      \\/   |__|    ");
        board.wait(4);
        
        System.out.printf("\fGood day Admiral. Let me be the first to welcome you aboard.");
        board.wait(3);
        System.out.printf("\nYour new fleet consists of 1 Battleship, and 1 Submarine.");
        board.wait(3);
        System.out.printf("\nIt's time to order the fleet into formation.");
        board.wait(3);
        
        board.placeShips(); //player places ships, computer places ships

       //Game begins, instructions given
        System.out.printf("\fSir, there is an incoming message:");
        board.wait(3); //delay for effect
        
        System.out.printf("\n'Greetings Admiral, an enemy fleet has been spotted close to your position."
        + "\nYour Mission: Destroy the enemy Battleship and Submarine.'\n\n");
        board.wait(6); //delay for effect
        
        board.drawBoard(); //initial drawing of board before shots are taken
        board.wait(3);
        
        //Player Turn
        do
        {
            board.drawBoard();
            
            //message to inform player of their turn
            System.out.printf("\n\nAdmiral, our guns are at the ready!");
            board.wait(3); //delay for effect
            
            System.out.printf("\n\nTarget Latitude!(A-J)");
            shotY = input.next().toUpperCase();
            System.out.printf("\n\nTarget Longitude!(1-10)");
            try
               {
                    shotX = Integer.parseInt(input.next());
               }
               catch (NumberFormatException ex) 
               {
                    System.out.printf("\fSir, Longitude must be a number.");
                    board.wait(3);
               }
            board.validateLocation(shotX, shotY);
            
            //board.checkShot(1, shotX, shotY); //player[], x, y coords
            
            //validate input
            
            //mark as hit or miss
            
            //check if ship is sunk
            
            //check if game over
           
            
            
            //Computer Turn
            
            //run targeting AI and shot
            
            //mark as hit or miss
            
            //check if ship is sunk
            
            //check if game over

            
        }
        while(state == Status.CONTINUE);
    }//playGame()     
}

