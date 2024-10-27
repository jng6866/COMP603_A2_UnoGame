/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package COMP603_A2_Uno;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Game class.
 * @author haydenwinterburn & mustafakamish
 */
public class GameTest {

    private Game game;
    private String[] playerIds;
    private GameStage gameStage; 

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    //initialize game instance
    public void setUp() {
        playerIds = new String[]{"Player1", "Player2", "Player3"};
        gameStage = new GameStage();
        game = new Game(playerIds, gameStage); 
    }

    @After
    public void tearDown() {
    }

    @Test
    //test the start of the game
    public void testStartGame() {
        game.startGame();
        assertNotNull("The game should start with a valid top card.", game.getTopCard());
        assertEquals("There should be 3 players in the game.", 3, playerIds.length);
        for (String pid : playerIds) {
            assertEquals("Each player should start with 7 cards.", 7, game.getPlayerHandSize(pid));
        }
    }

    @Test
    // tests if current player is correct
    public void testGetCurrentPlayer() {
        assertEquals("The initial current player should be Player1.", "Player1", game.getCurrentPlayer());
    }

    @Test
    //verify initial hand of player
    public void testGetPlayerHand() {
        ArrayList<Card> player1Hand = game.getPlayerHand("Player1");
        assertEquals("Player1 should have 7 cards initially.", 7, player1Hand.size());
    }

    @Test
    // very valid amount of cards
    public void testGetPlayerHandSize() {
        int handSize = game.getPlayerHandSize("Player1");
        assertEquals("Player1 should have 7 cards at the start of the game.", 7, handSize);
    }

    @Test
    //check for valid top card at start of game
    public void testGetTopCard() {
        game.startGame();
        Card topCard = game.getTopCard();
        assertNotNull("The top card should not be null after starting the game.", topCard);
    }

    @Test
    // tests that game duration counts time
    public void testGetGameDuration() {
        int duration = game.getGameDuration();
        assertTrue("Game duration should be a non-negative value.", duration >= 0);
    }

    @Test
    //verify players turn function
    public void testCheckPlayerTurn() {
        try {
            game.checkPlayerTurn("Player1");
        } catch (Exception e) {
            fail("Player1 should be allowed to play initially.");
        }
    }

    @Test
    // tests that the game end function works with an empty hand
    public void testIsGameOver() {
        // Clear Player1's hand to simulate a game over scenario
        game.getPlayerHand("Player1").clear();
        assertTrue("Game should be over if Player1 has no cards.", game.isGameOver());
    }

    @Test
    // tests image loading for top card
    public void testGetTopCardImage() {
        ImageIcon topCardImage = game.getTopCardImage();
        assertNotNull("The top card image should not be null.", topCardImage);
    }

    @Test
    //tests wild card colour selection
    public void testSetCardColour() {
        game.setCardColour(Card.Colour.Red);
        assertEquals("The top card color should be set to Red.", Card.Colour.Red, game.getTopCard().getColour());
    }

    @Test
    // tests the draw card function
    public void testSubmitDraw() {
        int initialHandSize = game.getPlayerHandSize("Player1");
        try {
            game.submitDraw("Player1");
        } catch (Exception e) {
            fail("Player1 should be allowed to draw a card.");
        }
        assertEquals("Player1's hand size should increase by 1.", initialHandSize + 1, game.getPlayerHandSize("Player1"));
    }

    @Test
    //tests players card placement
    public void testSubmitPlayerCard() {
        game.startGame();
        Card topCard = game.getTopCard();
        Card validCard = new Card(topCard.getColour(), topCard.getValue());
        try {
            game.submitPlayerCard("Player1", validCard, topCard.getColour());
        } catch (Exception e) {
            fail("Player1 should be able to play a valid card.");
        }
    }
}