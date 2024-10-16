/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COMP603_A2_Uno;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author haydenwinterburn
 */
public class PopUp extends javax.swing.JFrame {

    /**
     * Creates new form PopUp
     */
    
    String cardImage = "";
    Game game;
    ArrayList<Card> playerHand;
    int choice;
    ArrayList<JButton> cardButtons;
    GameStage gamestage;
    JButton topCardButton;
    Card.Colour declaredColour;
    
    public PopUp(Game game, int index, String cardName, ArrayList<JButton> cardButton, GameStage gamestage, JButton topCardButton) {
        initComponents();
        
        cardImage = cardName;
        this.game = game;
        playerHand = game.getPlayerHand(game.getCurrentPlayer());
        choice = index;
        this.cardButtons = cardButtons;
        cardLabel.setIcon(new javax.swing.ImageIcon("/Users/haydenwinterburn/images/small/" + cardImage + ".png"));
        this.gamestage = gamestage;
        this.topCardButton = topCardButton;
        
        // Set the image for the cardLabel using the absolute file path
        String filePath = "/Users/haydenwinterburn/images/small/" + cardImage + ".png";  // Replace with your correct path
        java.io.File file = new java.io.File(filePath);

        if (file.exists()) {
            cardLabel.setIcon(new javax.swing.ImageIcon(filePath));
        } else {
            System.out.println("Card image not found at path: " + filePath);
            cardLabel.setText("Image not found");
            }
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
        useCardButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        cardLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(220, 72, 72));

        useCardButton.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        useCardButton.setForeground(new java.awt.Color(255, 255, 255));
        useCardButton.setText("USE CARD");
        useCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useCardButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        cardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(useCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(cardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(cardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void useCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useCardButtonActionPerformed
        // TODO add your handling code here:
        PickColourFrame pickColour = new PickColourFrame(this);
        declaredColour = pickColour.chooseColour(playerHand.get(choice));
        
        if(declaredColour != null){
            try {
                game.submitPlayerCard(game.getCurrentPlayer(), playerHand.get(choice), declaredColour);
            } catch (InvalidColourSubmissionException ex) {
                Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidPlayerTurnException ex) {
                Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidValueSubmissionException ex) {
                Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.revalidate();
            
            if(declaredColour != Card.Colour.Wild){
                gamestage.setPidName(game.getCurrentPlayer());
                gamestage.setButtonIcons();
                topCardButton.setIcon(new javax.swing.ImageIcon("/Users/haydenwinterburn/images/small/" + cardImage + ".png"));
                this.dispose();
            }
            
        }
    }//GEN-LAST:event_useCardButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUp().setVisible(true);
            }
        });
    }
    public PopUp(){}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel cardLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton useCardButton;
    // End of variables declaration//GEN-END:variables
}
