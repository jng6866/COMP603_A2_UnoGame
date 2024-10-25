/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COMP603_A2_Uno;

/**
 *
 * @author haydenwinterburn
 */
public class EndScreen extends javax.swing.JFrame {
    
    
    //Players in current game
    //Players current wins
    //Players current rank
        //Bronze: 0 - 5
        //Silver: 6 - 15
        //Gold: 16 - 30
        //Master: 31 + 
    
    /**
     * Creates new form EndScreen
     */
    private String winner;
    private int[] playerIds;
    private String playerStat;
    
    public EndScreen(String winner, int[] playerIds) {
        initComponents();
        this.winner = winner;
        this.playerIds = playerIds;        
        System.out.println("Winner received in EndScreen: " + winner);
        if (winner == null) {
            this.winner = "Unknown Player";  // Assign default if winner is null
        } else {
            this.winner = winner;  // Store the winner information in a class-level variable
        }

        winLabel.setText(winner + " has won the game!");
        
        
        
        displayPlayerStats();
        
        
    }

    
    
    public EndScreen() {}

    EndScreen(String winnerName, String[] playerIds) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    private void displayPlayerStats() {

        jLabel1.setVisible(false);  // Initially hide labels
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);

        for (int i = 0; i < playerIds.length; i++) {
            String playerName = PlayerDB.getPlayerName(playerIds[i]);  // Fetch the player's name from the DB
            int playerScore = PlayerDB.getPlayerTotalScore(playerIds[i]);  // Fetch the player's total score from DB

            if (playerName == null) {
                playerName = "Unknown Player";  // Fallback if player name is null
            }

            String playerStat = "Player " + (i + 1) + ": " + playerName + " - wins (" + playerScore + " total wins)";

            // Display stats in the respective labels
            switch (i) {
                case 0:
                    jLabel1.setText(playerStat);
                    jLabel1.setVisible(true);
                    break;
                case 1:
                    jLabel2.setText(playerStat);
                    jLabel2.setVisible(true);
                    break;
                case 2:
                    jLabel3.setText(playerStat);
                    jLabel3.setVisible(true);
                    break;
                case 3:
                    jLabel4.setText(playerStat);
                    jLabel4.setVisible(true);
                    break;
            }
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
        winLabel = new javax.swing.JLabel();
        playAgainButton = new javax.swing.JButton();
        saveExitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        mostStatsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 450));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 450));

        jPanel1.setBackground(new java.awt.Color(220, 72, 72));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 400));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 400));

        winLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        winLabel.setForeground(new java.awt.Color(255, 255, 255));
        winLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winLabel.setText("Hayden has won the game!");

        playAgainButton.setBackground(new java.awt.Color(229, 105, 105));
        playAgainButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        playAgainButton.setForeground(new java.awt.Color(255, 255, 255));
        playAgainButton.setText("PLAY AGAIN");
        playAgainButton.setBorderPainted(false);
        playAgainButton.setPreferredSize(new java.awt.Dimension(100, 30));
        playAgainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playAgainButtonActionPerformed(evt);
            }
        });

        saveExitButton.setBackground(new java.awt.Color(229, 105, 105));
        saveExitButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        saveExitButton.setForeground(new java.awt.Color(255, 255, 255));
        saveExitButton.setText("SAVE & EXIT");
        saveExitButton.setBorderPainted(false);
        saveExitButton.setMaximumSize(new java.awt.Dimension(100, 30));
        saveExitButton.setMinimumSize(new java.awt.Dimension(100, 30));
        saveExitButton.setPreferredSize(new java.awt.Dimension(120, 30));
        saveExitButton.setSize(new java.awt.Dimension(100, 30));
        saveExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveExitButtonActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel2");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("jLabel3");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("jLabel4");

        mostStatsButton.setBackground(new java.awt.Color(229, 105, 105));
        mostStatsButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        mostStatsButton.setForeground(new java.awt.Color(255, 255, 255));
        mostStatsButton.setText("MORE STATS");
        mostStatsButton.setBorderPainted(false);
        mostStatsButton.setPreferredSize(new java.awt.Dimension(100, 30));
        mostStatsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostStatsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(winLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(playAgainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(144, 144, 144))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(mostStatsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(winLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(mostStatsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playAgainButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
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

    private void playAgainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playAgainButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_playAgainButtonActionPerformed

    private void saveExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveExitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_saveExitButtonActionPerformed

    private void mostStatsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostStatsButtonActionPerformed
        MoreStats statsFrame = new MoreStats(); 
        statsFrame.setVisible(true); 
    }//GEN-LAST:event_mostStatsButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EndScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EndScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EndScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EndScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new EndScreen().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton mostStatsButton;
    private javax.swing.JButton playAgainButton;
    private javax.swing.JButton saveExitButton;
    private javax.swing.JLabel winLabel;
    // End of variables declaration//GEN-END:variables
}
