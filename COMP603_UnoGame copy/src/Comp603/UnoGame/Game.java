/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comp603.UnoGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck deck;  // deck for current game
    private Player p1;  // first player
    private Player p2;  // second player
    private DiscardPile discardPile;    // piles for discard
    private UserManager userManager;    // manage scores and related data
    private gameLogger gameLogger; 

    
    // Constructor for the game
    public Game(String p1Name, String p2Name) {
        this.deck = new Deck();
        this.p1 = new Player(p1Name);  // initialize player name
        this.p2 = new Player(p2Name);  // initialize player name
        this.userManager = new UserManager(); //initialize manger
        this.discardPile = new DiscardPile();  //initialize discard
        this.gameLogger = new gameLogger(); //initialize log
        startGame();
    }
    


    // Game setup. dealing cards and starting a discard pile.
    private void startGame() {
        for (int i = 0; i < 7; i++) {
            p1.addCard(deck.drawCard());  // p1 draw card
            p2.addCard(deck.drawCard());  // p2 draw card
        }
        
        // discard pile initialized to start game
        if (!deck.isEmpty()) {
            discardPile.addCard(deck.drawCard());
        } else {
            System.out.println("Deck is empty, cannot start game.");
        }
        System.out.println("Game has started...");
        loopGame();
    }

    private void loopGame() {
        Scanner scan = new Scanner(System.in); //scanner object
        Player currentPlayer = p1; //initialize p1 as first
        Card.Colour chooseColour = null; // wild car colour tracking

        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn."); // print current plaeyr
            System.out.println(currentPlayer);  // print current hand

            if (chooseColour == null) {
                System.out.println("Top of discard: " + discardPile.getTopCard());
            } else {
                System.out.println("Next card must match the colour: " + chooseColour);
            }

            boolean validMove = false; // check for valid card placement
            while (!validMove) {
                System.out.println("Card choice to play (enter index) or -1 to draw [enter 'q' to quit, enter 'l' for logs : ");
                String input = scan.nextLine().trim();
                // quit game loop implementation
                if (input.equalsIgnoreCase("q")) {
                    System.out.println("Are you sure you want to quit? Your scores will not be saved. [y/n]: ");
                    String confirm = scan.nextLine().trim().toLowerCase();
                    if (confirm.equals("y")) {
                        System.out.println("Exiting game. Thank you for playing!");
                        return;
                    } else {
                        System.out.println("Resuming game...");
                        continue;
                    }
                }
                
                if (input.equalsIgnoreCase("l")) {
                    System.out.println("\nGame Log History:");
                    gameLogger.readLog(); 
                    continue; 
                }
            try {
                int choice = Integer.parseInt(input); //input to int for index of card selection

                if (choice == -1) {  // draw card
                    currentPlayer.addCard(deck.drawCard());
                    validMove = true;
                } else {
                    // valdite index is within paramaters 
                    if (choice < 0 || choice >= currentPlayer.getHand().size()) {
                        System.out.println("Invalid index. Please choose a valid card index.");
                        continue;
                    }
                    Card card = currentPlayer.getHand().get(choice);
                    Card topCard = discardPile.getTopCard();

                    // Validate play based on the current game and top of discard pile
                    // match either colour or number under normal play
                    // if wild placed match only by colour
                    // any wild can be placed
                    if ((chooseColour == null && (card.getColour() == topCard.getColour() || card.getValue() == topCard.getValue())) ||
                    (chooseColour != null && card.getColour() == chooseColour) || 
                    card.getColour() == Card.Colour.WILD) {

                        discardPile.addCard(currentPlayer.playCard(choice));
                        validMove = true; // turn will end after valid card play

                        // reset to null after a card is played after a wild
                        chooseColour = null;

                        // handles instances of cards with wild and wild + 4
                        if (card.getColour() == Card.Colour.WILD) {
                            // choose colour as part of wild card selection
                            chooseColour = chooseColour(scan);
                            break;
                        }
                        // draw, skip, reverse card handeling
                        if (card instanceof ActionCard) {
                            ActionCard actionCard = (ActionCard) card;
                            switch (actionCard.getAction()) {
                                case SKIP:
                                    System.out.println("Next player has been skipped");
                                    currentPlayer = (currentPlayer == p1) ? p2 : p1; // skip next and return to current player
                                    break;
                                case DRAW_TWO:
                                    currentPlayer = (currentPlayer == p1) ? p2 : p1; // Switch to the next player
                                    System.out.println(currentPlayer.getName() + " has to draw 2 cards. " + currentPlayer.getName() + " skips a turn.");
                                    currentPlayer.addCard(deck.drawCard());  //draw card
                                    currentPlayer.addCard(deck.drawCard());  //draw card
                                    break;
                            }
                        }

                        // wild card and wild card plus four handeling
                        if (card instanceof WildCard && ((WildCard) card).getWildType() == WildCard.WildType.DRAW_FOUR) {
                            currentPlayer = (currentPlayer == p1) ? p2 : p1; // Next player
                            System.out.println(currentPlayer.getName() + " has to draw 4 cards. " + currentPlayer.getName() + " skips a turn.");
                            for (int i = 0; i < 4; i++) {                    //loop to draw 4 cards
                                currentPlayer.addCard(deck.drawCard());      // draw card
                            }
                        }
                    } else {
                        System.out.println("Invalid card.\nPlease pick a colour or number matching the card top of discard pile."); // tells player to 
                        }
                    }
                } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number."); // catches input that isn't a number, ask player to re-enter a valid input.
                }
            }
            
            
            // announce winner when hand is empty
            if (currentPlayer.getHand().isEmpty()) {
                System.out.println(currentPlayer.getName() + " wins!");  // announce winner
                gameLogger.logResult(currentPlayer.getName(), (currentPlayer == p1) ? p2.getName() : p1.getName()); // add to log file
                userManager.updateScore(currentPlayer.getName());        // update score file with long term win record
                break;  // exit program/game
            }
            currentPlayer = (currentPlayer == p1) ? p2 : p1;  // Switch to next player
        }
    }
    // method prompt for wild card selection
    private Card.Colour chooseColour(Scanner scan) {
        while (true) {  //loop for colour choice
            System.out.println("Choose a colour [R/G/B/Y]");
            String colorChoice = scan.nextLine().trim().toUpperCase(); // read input which has been normalised to uppercase
            switch (colorChoice) {
                // switch for colour choice when wild selected (r/g/b/y)
                case "R":
                    return Card.Colour.RED;
                case "G":
                    return Card.Colour.GREEN;
                case "B":
                    return Card.Colour.BLUE;
                case "Y":
                    return Card.Colour.YELLOW;
                default:
                    System.out.println("Invalid input! [R/G/B/Y]");
            }
        }
    }
}
