/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COMP603_A2_Uno;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author haydenwinterburn
 */
public class PickColourFrame extends javax.swing.JFrame {

    /**
     * Creates new form PickColourFrame
     */
    
    private Card.Colour wildColour = null;
    Boolean allow = false;
    PopUp popUp;
    
    public PickColourFrame() {
        initComponents();
        this.revalidate();
        this.repaint();
        System.out.println("Buttons initialized.");
        
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    public PickColourFrame(PopUp pop){
        initComponents();
        popUp = pop;  
        this.revalidate();
        this.repaint();
        System.out.println("Buttons initialized with PopUp.");
    }
    
    public Card.Colour chooseColour(Card card){
        
        if(card.getColour() == Card.Colour.Wild){
            this.setVisible(true);
            this.setResizable(false);
            this.setBounds(100,150,500,400);
            
            this.revalidate();
            this.repaint();
        }
        return card.getColour();
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
        redButton = new javax.swing.JButton();
        greenButton = new javax.swing.JButton();
        blueButton = new javax.swing.JButton();
        yellowButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Playing Wild Card");

        jPanel1.setBackground(new java.awt.Color(220, 75, 75));

        redButton.setBackground(new java.awt.Color(255, 102, 102));
        redButton.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        redButton.setForeground(new java.awt.Color(255, 255, 255));
        redButton.setText("RED");
        redButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redButtonActionPerformed(evt);
            }
        });

        greenButton.setBackground(new java.awt.Color(51, 255, 102));
        greenButton.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        greenButton.setForeground(new java.awt.Color(255, 255, 255));
        greenButton.setText("GREEN");
        greenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenButtonActionPerformed(evt);
            }
        });

        blueButton.setBackground(new java.awt.Color(102, 153, 255));
        blueButton.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        blueButton.setForeground(new java.awt.Color(255, 255, 255));
        blueButton.setText("BLUE");
        blueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blueButtonActionPerformed(evt);
            }
        });

        yellowButton.setBackground(new java.awt.Color(255, 204, 102));
        yellowButton.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        yellowButton.setForeground(new java.awt.Color(255, 255, 255));
        yellowButton.setText("YELLOW");
        yellowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yellowButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PLAYING WILD CARD");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PICK A COLOUR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(redButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(blueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(yellowButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(greenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGap(109, 109, 109)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(redButton)
                    .addComponent(greenButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(blueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yellowButton))
                .addContainerGap(88, Short.MAX_VALUE))
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

    private void redButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redButtonActionPerformed
        // TODO add your handling code here:
        wildColour = Card.Colour.Red;
        
        JLabel message = new JLabel("Wild Card Colour is red!");
        message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
       
        
        popUp.declaredColour = Card.Colour.Red;
        popUp.gamestage.setPidName(popUp.game.getCurrentPlayer());
        popUp.gamestage.setButtonIcons();
        //popUp.topCardButton.setIcon(new javax.swing.ImageIcon("/Users/haydenwinterburn/images/small/" + popUp.game.getTopCardImage()));
        
        String imagePath = System.getProperty("user.dir") + "/resources/images/small/" + popUp.game.getTopCardImage();
        File imgFile = new File(imagePath);

        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
            popUp.topCardButton.setIcon(icon);
        } else {
            System.out.println("Top card image not found at path: " + imagePath);
            popUp.topCardButton.setText("Image not found");
        }
        
        popUp.game.setCardColour(Card.Colour.Red);
        popUp.gamestage.updateTopCardColor();
        
        this.dispose();
        popUp.dispose();
        
    }//GEN-LAST:event_redButtonActionPerformed

    private void greenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenButtonActionPerformed
        // TODO add your handling code here:
        wildColour = Card.Colour.Green;
        
        JLabel message = new JLabel("Wild Card Colour is green!");
        message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
        
        allow = true;
        this.dispose();
        
        popUp.declaredColour = Card.Colour.Green;
        popUp.gamestage.setPidName(popUp.game.getCurrentPlayer());
        popUp.gamestage.setButtonIcons();
        //popUp.topCardButton.setIcon(new javax.swing.ImageIcon("/Users/haydenwinterburn/images/small/" + popUp.game.getTopCardImage()));
        
        String imagePath = System.getProperty("user.dir") + "/resources/images/small/" + popUp.game.getTopCardImage();
        File imgFile = new File(imagePath);

        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
            popUp.topCardButton.setIcon(icon);
        } else {
            System.out.println("Top card image not found at path: " + imagePath);
            popUp.topCardButton.setText("Image not found");
        }
        
        popUp.game.setCardColour(Card.Colour.Green);
        popUp.gamestage.updateTopCardColor();
        
        popUp.dispose();
    }//GEN-LAST:event_greenButtonActionPerformed

    private void blueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blueButtonActionPerformed
        // TODO add your handling code here:
        wildColour = Card.Colour.Blue;
        
        JLabel message = new JLabel("Wild Card Colour is blue!");
        message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
        
        allow = true;
        this.dispose();
        
        popUp.declaredColour = Card.Colour.Blue;
        popUp.gamestage.setPidName(popUp.game.getCurrentPlayer());
        popUp.gamestage.setButtonIcons();
        //popUp.topCardButton.setIcon(new javax.swing.ImageIcon("/Users/haydenwinterburn/images/small/" + popUp.game.getTopCardImage()));
        
        String imagePath = System.getProperty("user.dir") + "/resources/images/small/" + popUp.game.getTopCardImage();
        File imgFile = new File(imagePath);
        
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
            popUp.topCardButton.setIcon(icon);
        } else {
            System.out.println("Top card image not found at path: " + imagePath);
            popUp.topCardButton.setText("Image not found");
        }
        
        popUp.game.setCardColour(Card.Colour.Blue);
        popUp.gamestage.updateTopCardColor();
        
        popUp.dispose();
    }//GEN-LAST:event_blueButtonActionPerformed

    private void yellowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yellowButtonActionPerformed
        // TODO add your handling code here:
        wildColour = Card.Colour.Yellow;
        
        JLabel message = new JLabel("Wild Card Colour is yellow!");
        message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
        JOptionPane.showMessageDialog(null, message);
        
        allow = true;
        this.dispose();
        
        popUp.declaredColour = Card.Colour.Yellow;
        popUp.gamestage.setPidName(popUp.game.getCurrentPlayer());
        popUp.gamestage.setButtonIcons();
        //popUp.topCardButton.setIcon(new javax.swing.ImageIcon("/Users/haydenwinterburn/images/small/" + popUp.game.getTopCardImage()));
        
        String imagePath = System.getProperty("user.dir") + "/resources/images/small/" + popUp.game.getTopCardImage();
        File imgFile = new File(imagePath);
        
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
            popUp.topCardButton.setIcon(icon);
        } else {
            System.out.println("Top card image not found at path: " + imagePath);
            popUp.topCardButton.setText("Image not found");
        }
       
        popUp.game.setCardColour(Card.Colour.Yellow);
        popUp.gamestage.updateTopCardColor();
        
        popUp.dispose();
    }//GEN-LAST:event_yellowButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PickColourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PickColourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PickColourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PickColourFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PickColourFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blueButton;
    private javax.swing.JButton greenButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton redButton;
    private javax.swing.JButton yellowButton;
    // End of variables declaration//GEN-END:variables

}
