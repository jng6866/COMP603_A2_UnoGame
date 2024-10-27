/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COMP603_A2_Uno;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.File;                // Import for java.io.File
import javax.swing.ImageIcon;       // Ensures that ImageIcon class is recognised

/**
 * Custom GameStage class created using JFrame Form.
 * This class controls the main game window, displaying player hands and 
 * handling interactions during game play.
 * @author haydenwinterburn
 */
public class GameStage extends javax.swing.JFrame {

    // ArrayList to store temporary player names added by the user
    ArrayList<String> temp = new ArrayList<>();
    String[] pids;  // Array of player IDs
    Game game;  // Instance of the game to handle game mechanics
    ArrayList<JButton> cardButtons = new ArrayList<JButton>();  // List of card button components
    ArrayList<String> cardIds;  // List of card IDs representing the player's hand
    PopUp pu;  // Pop-up window to handle card selection and interactions
    
    /**
     * Creates new form GameStage
     */
    public GameStage(ArrayList<String> playerIds) {
        initComponents();
        temp = playerIds;  // Assigns player IDs to the temporary ArrayList
        pids = temp.toArray(new String[temp.size()]);  // Converts ArrayList to array for processing
        game = new Game(pids, this);  // Creates a new instance of Game with player IDs
        
        // Adds players to the database for tracking
        for (String playerId : pids) {
            PlayerDB.addPlayer(playerId); // Adds player names to the database
        }
        
        // Allows the Escape key to act as an 'Exit' button
        jPanel1.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            javax.swing.KeyStroke.getKeyStroke("ESCAPE"), "exitAction");
        jPanel1.getActionMap().put("exitAction", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                exitButton.doClick();  // Simulates a click on the 'Exit' button
            }
        });
        
        // Sets the window to open at the centre of the screen
        setLocationRelativeTo(null);
        
        populateArrayList();  // Method to populate the list of card buttons
        game.startGame();  // Calls method to initialise the game start
        
        // Sets up initial names and card icons for players
        setPidName();
        setButtonIcons();
        updateTopCardColor();
        updateTopCardButton();

        // Sets the icon for the 'downButton' component from the image file path
        String DownButtonrelativePath = System.getProperty("user.dir") + "/resources/images/small/Deck.png"; //Image relative path
        File downButtonImgFile = new File(DownButtonrelativePath); 
        if (downButtonImgFile.exists()) { //checking if image exists in directory
            ImageIcon icon = new ImageIcon(downButtonImgFile.getAbsolutePath());
            downButton.setIcon(icon);  // Sets the icon if the image exists
        } else {
            System.out.println("Image file not found: " + DownButtonrelativePath); // print statement if cannot be found
        }

    }
    
    // Updates the icon of the 'topCardButton' based on the top card in the game
    public void updateTopCardButton(){
        String relativePath = System.getProperty("user.dir") + "/resources/images/small/" + game.getTopCardImage();
        File imgFile = new File(relativePath);

        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
            topCardButton.setIcon(icon);  // Sets the icon if the image file is found
        } else {
            System.out.println("Image file not found: " + relativePath);
            topCardButton.setText("Image not found");  // Displays message if file is missing
        }
    }
    
    // Method to set up the icons for each button in the player's hand
    public void setButtonIcons(){
        String listString = game.getPlayerHand(game.getCurrentPlayer())
                .stream().map(Object::toString).collect(Collectors.joining(","));
        String[] cardNames = listString.split(",");
        cardIds = new ArrayList<>(Arrays.asList(cardNames));
        
        int totalCards = cardIds.size();
        for (int i = 0; i < totalCards && i < 7; i++) {  // First row of cards (slots 1–7)
            String relativePath = System.getProperty("user.dir") + "/resources/images/small/" + cardIds.get(i) + ".png"; // relative path of card images
            //cardis is the name of each image
            
            File imgFile = new File(relativePath);

            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                cardButtons.get(i).setIcon(icon);  // Sets icon for each card button
            } else {
                System.out.println("Image file not found: " + relativePath);
            }
        }

        // If more than 7 cards, place the remaining ones in the bottom row (slots 8–14)
        for (int j = 7; j < totalCards && j < 14; j++) {  // Buttons (slots 8–14)
            String relativePath = System.getProperty("user.dir") + "/resources/images/small/" + cardIds.get(j) + ".png";
            File imgFile = new File(relativePath);

            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                cardButtons.get(j).setIcon(icon); //sets the card button to the image icon
            } else {
                System.out.println("Image file not found: " + relativePath);
            }
        }

            // Clear any remaining buttons if there are fewer than 14 cards
            for (int k = totalCards; k < cardButtons.size(); k++) {
                cardButtons.get(k).setIcon(null);  // Set the icon to null if no card
            }
    }
    
    // Updates the display text to show the colour of the top card
    public void updateTopCardColor() {
        if (game != null && game.getTopCard() != null) {
            Card topCard = game.getTopCard();
            String colorText = "Top Card Colour: " + topCard.getColour().toString();
            topCardColourLabel.setText(colorText);  // Updates the label to show the current colour
        }
    }
    
    // Populates the array list into each card button
    // Will add card to array list in order of buttons (1 - 14)
    public void populateArrayList(){
        
        cardButtons.add(jButton1);
        cardButtons.add(jButton2);
        cardButtons.add(jButton3);
        cardButtons.add(jButton4);
        cardButtons.add(jButton5);
        cardButtons.add(jButton6);
        cardButtons.add(jButton7);
        cardButtons.add(jButton8);
        cardButtons.add(jButton9);
        cardButtons.add(jButton10);
        cardButtons.add(jButton11);
        cardButtons.add(jButton12);
        cardButtons.add(jButton13);
        cardButtons.add(jButton14);
    }
    
    // Setter for pid name
    public void setPidName(){
        String currentPlayer = game.getCurrentPlayer(); // Retrieves name of current player from game
        pidNameLabel.setText(currentPlayer + "'s Cards"); // Set the name label to the current player
    }
    
    // Setter for pid name
    public void setPidName(String currentPlayer){
        pidNameLabel.setText(currentPlayer + "'s Cards"); // Set the name label to the current player
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        drawCardButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        topCardButton = new javax.swing.JButton();
        pidNameLabel = new javax.swing.JLabel();
        downButton = new javax.swing.JButton();
        topCardColourLabel = new javax.swing.JLabel();
        unoGameLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UnoGame");
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(220, 75, 75));
        jPanel1.setToolTipText("");
        jPanel1.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        drawCardButton.setBackground(new java.awt.Color(229, 105, 105));
        drawCardButton.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        drawCardButton.setForeground(new java.awt.Color(255, 255, 255));
        drawCardButton.setText("+1 card");
        drawCardButton.setBorderPainted(false);
        drawCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawCardButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(229, 105, 105));
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(103, 146));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(229, 105, 105));
        jButton2.setBorderPainted(false);
        jButton2.setPreferredSize(new java.awt.Dimension(103, 146));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(229, 105, 105));
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(229, 105, 105));
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(229, 105, 105));
        jButton5.setToolTipText("");
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(229, 105, 105));
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(229, 105, 105));
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(229, 105, 105));
        jButton8.setBorderPainted(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(229, 105, 105));
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(229, 105, 105));
        jButton10.setBorderPainted(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(229, 105, 105));
        jButton11.setBorderPainted(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(229, 105, 105));
        jButton12.setBorderPainted(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(229, 105, 105));
        jButton13.setActionCommand("jButton15");
        jButton13.setBorderPainted(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(229, 105, 105));
        jButton14.setBorderPainted(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        topCardButton.setBackground(new java.awt.Color(229, 105, 105));
        topCardButton.setBorderPainted(false);
        topCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topCardButtonActionPerformed(evt);
            }
        });

        pidNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        pidNameLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        pidNameLabel.setForeground(new java.awt.Color(255, 255, 255));

        downButton.setBackground(new java.awt.Color(229, 105, 105));
        downButton.setBorderPainted(false);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        topCardColourLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        topCardColourLabel.setForeground(new java.awt.Color(255, 255, 255));
        topCardColourLabel.setText("Top Card Colour: Yellow");

        unoGameLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 70)); // NOI18N
        unoGameLabel.setForeground(new java.awt.Color(255, 255, 255));
        unoGameLabel.setText("UnoGame");

        exitButton.setBackground(new java.awt.Color(229, 105, 105));
        exitButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("EXIT GAME");
        exitButton.setToolTipText("shortcut='Esc'");
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(pidNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(drawCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(topCardColourLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(downButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(topCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(unoGameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(unoGameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(downButton, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(topCardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(topCardColourLabel)
                        .addGap(62, 62, 62)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawCardButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pidNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    //====================== JButtons for each card button =====================
    
    /**
     * Action for card buttons: Opens a PopUp window to play the card if it's available.
     * checks if in the array of players hand is not null (has card)
     * sets index to number
     * opens popup if button is clicked
     * sets popup is visible
     * cannot resize popup windows
     */
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(2) != null){
            int index = 2;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(1) != null){
            int index = 1;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);

            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(13) != null){
            int index = 13;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(3) != null){
            int index = 3;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(4) != null){
            int index = 4;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(5) != null){
            int index = 5;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(11) != null){
            int index = 11;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(6) != null){
            int index = 6;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(10) != null){
            int index = 10;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(9) != null){
            int index = 9;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(8) != null){
            int index = 8;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(7) != null){
            int index = 7;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if(cardIds.get(0) != null){
            int index = 0;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        if(cardIds.get(12) != null){
            int index = 12;
            String cardId = cardIds.get(index);
            pu = new PopUp(game, index, cardId, cardButtons, this, topCardButton);
            pu.setVisible(true);
            pu.setResizable(false);
            pu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jButton13ActionPerformed
    
    // Draws a card for the current player and updates their hand display
    private void drawCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawCardButtonActionPerformed
        // TODO add your handling code here:
        JLabel message = new JLabel(game.getCurrentPlayer() + " drew a card.");
        message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
            
        try {
            game.submitDraw(game.getCurrentPlayer());
        } catch (InvalidPlayerTurnException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // Updates player name and card icons after drawing a card
        this.setPidName(game.getCurrentPlayer());
        this.setButtonIcons();
        updateTopCardColor();
    }//GEN-LAST:event_drawCardButtonActionPerformed

    private void topCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topCardButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_topCardButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        // TODO add your handling code here:
        // Constructs relative path to Deck.png image
        
    }//GEN-LAST:event_downButtonActionPerformed
    
    // Exit button for gamestage
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
        System.exit(0); // closes the program
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameStage().setVisible(true);
            }
        });
    }
    //Fixes error in main
    public GameStage(){}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton downButton;
    private javax.swing.JButton drawCardButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pidNameLabel;
    private javax.swing.JButton topCardButton;
    private javax.swing.JLabel topCardColourLabel;
    private javax.swing.JLabel unoGameLabel;
    // End of variables declaration//GEN-END:variables
}
