/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COMP603_A2_Uno;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Main Game class implementing the GameInterface
 */
public class Game implements GameInterface {

    private int currentPlayer;
    private String[] playerIds;
    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile;
    private Card.Colour validColour;
    private Card.Value validValue;
    private GameStage gamestage;
    boolean direction;
    private long gameStartTime;
    private int cardsPlayed = 0;

    public Game(String[] pids) {
        deck = new Deck();
        deck.shuffle();
        stockPile = new ArrayList<>();
        playerIds = pids;
        currentPlayer = 0;
        direction = false;
        playerHand = new ArrayList<>();
        this.gamestage = gamestage;

        for (String pid : pids) {
            ArrayList<Card> hand = new ArrayList<>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }

        gameStartTime = System.currentTimeMillis();
        cardsPlayed = 0;
    }

    @Override
    public void startGame() {
        Card card = deck.drawCard();
        validColour = card.getColour();
        validValue = card.getValue();

        if (card.getValue() == Card.Value.Wild || card.getValue() == Card.Value.wildFour || card.getValue() == Card.Value.DrawTwo) {
            startGame();
            return;
        }

        if (card.getValue() == Card.Value.Skip) {
            showMessage(playerIds[currentPlayer] + " was skipped.");
            advancePlayer();
        } else if (card.getValue() == Card.Value.Reverse) {
            showMessage(playerIds[currentPlayer] + " reversed the game direction.");
            direction = !direction;
            currentPlayer = playerIds.length - 1;
        }

        stockPile.add(card);
    }

    @Override
    public String getCurrentPlayer() {
        return this.playerIds[this.currentPlayer];
    }

    @Override
    public ArrayList<Card> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid);
        return playerHand.get(index);
    }

    @Override
    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size();
    }

    @Override
    public Card getTopCard() {
        return new Card(validColour, validValue);
    }

    @Override
    public int getGameDuration() {
        long currentTime = System.currentTimeMillis();
        return (int) ((currentTime - gameStartTime) / 1000);
    }

    @Override
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        if (!this.playerIds[this.currentPlayer].equals(pid)) {
            throw new InvalidPlayerTurnException("It is not " + pid + "'s turn", pid);
        }
    }
    
    @Override
    public boolean isGameOver() {
        return Arrays.stream(playerIds).anyMatch(this::hasEmptyHand);
    }

    private boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public ImageIcon getTopCardImage() {
        return new ImageIcon(validColour + "_" + validValue + ".png");
    }
    public void setCardColour(Card.Colour colour) {
        validColour = colour;
    }
    public void submitDraw(String pid) throws InvalidPlayerTurnException {
        checkPlayerTurn(pid);

        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());
        advancePlayer();
    }

    public void submitPlayerCard(String pid, Card card, Card.Colour declaredColour)
            throws InvalidColourSubmissionException, InvalidPlayerTurnException, InvalidValueSubmissionException {

        cardsPlayed++;
        checkPlayerTurn(pid);

        ArrayList<Card> pHand = getPlayerHand(pid);
        if (card.getColour() == Card.Colour.Wild) {
            validColour = declaredColour;
            validValue = card.getValue();
        } else if (!validCardPlay(card)) {
            showMessage("Invalid move. Expected colour: " + validColour + ". Given color: " + card.getColour() + ".");
            throw new InvalidColourSubmissionException("Invalid colour", card.getColour(), validColour);
        } else {
            validColour = card.getColour();
            validValue = card.getValue();
        }

        pHand.remove(card);

        if (isGameOver()) {
            endGame(pid);
        }

        stockPile.add(card);
        handleSpecialCards(card);
    }

    private boolean validCardPlay(Card card) {
        return card.getColour() == validColour || card.getValue() == validValue;
    }

    private void handleSpecialCards(Card card) {
        switch (card.getValue()) {
            case Reverse:
                showMessage(playerIds[currentPlayer] + " used reverse!");
                direction = !direction;
                advancePlayer();
                break;
            case DrawTwo:
                advancePlayer();
                String pid = playerIds[currentPlayer];
                getPlayerHand(pid).add(deck.drawCard());
                getPlayerHand(pid).add(deck.drawCard());
                showMessage(pid + ", draw 2 cards!");
                advancePlayer();
                break;
            case wildFour:
                advancePlayer();
                pid = playerIds[currentPlayer];
                for (int i = 0; i < 4; i++) {
                    getPlayerHand(pid).add(deck.drawCard());
                }
                showMessage(pid + ", draw 4 cards!");
                advancePlayer();
                break;
            case Skip:
                // Determine the player to be skipped
                int skippedPlayerIndex = direction
                        ? (currentPlayer - 1 + playerIds.length) % playerIds.length  // Anti-clockwise
                        : (currentPlayer + 1) % playerIds.length;  // Clockwise

                // Display message for the player who will be skipped
                showMessage(playerIds[skippedPlayerIndex] + " was skipped!");

                // Advance to the player after the skipped one
                advancePlayer();
                advancePlayer();
                break;
            default:
                advancePlayer();
                break;
        }
    }

    private void endGame(String pid) {
        System.out.println("Winner's PID: " + pid);
        String winnerName = pid;
        int winnerId = PlayerDB.getPlayerID(winnerName);
        int totalTimePlayed = getGameDuration();
        // Convert playerIds from String[] to int[]
        int[] playerIDs = Arrays.stream(playerIds)
                            .mapToInt(PlayerDB::getPlayerID) // Convert each String ID to an int ID
                            .toArray();
        GameStatsDB.addGameStats(cardsPlayed, totalTimePlayed);
        PlayerDB.addScore(winnerId, 1);
        new EndScreen(winnerName, playerIDs).setVisible(true);
        gamestage.dispose();
        System.exit(0);
    }

    private void advancePlayer() {
        currentPlayer = direction
                ? (currentPlayer - 1 + playerIds.length) % playerIds.length
                : (currentPlayer + 1) % playerIds.length;
    }

    private void showMessage(String messageText) {
        JLabel message = new JLabel(messageText);
        message.setFont(new Font("Arial", Font.BOLD, 36));
        JOptionPane.showMessageDialog(null, message);
    }
}

