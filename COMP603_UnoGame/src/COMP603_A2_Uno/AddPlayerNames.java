/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package COMP603_A2_Uno;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author haydenwinterburn
 */
public class AddPlayerNames extends javax.swing.JFrame {
    
    public ArrayList<String> playerIds;
    
    
    /**
     * Creates new form AddPlayerNames
     */
    public AddPlayerNames() {
        initComponents();
        playerIds = new ArrayList<>();
        
    }

    public String[] getPids(){
        String[] pids = playerIds.toArray(new String[playerIds.size()]);
        return pids;
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
        SaveButton = new javax.swing.JButton();
        DoneButton = new javax.swing.JButton();
        pidTextBox = new javax.swing.JTextField();
        INSERT_TITLE = new javax.swing.JLabel();
        PLAYERNAME_LABEL = new javax.swing.JLabel();
        pidOneLabel = new javax.swing.JLabel();
        pidTwoLabel = new javax.swing.JLabel();
        pidThreeLabel = new javax.swing.JLabel();
        pidFourLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 400));
        setMinimumSize(new java.awt.Dimension(600, 400));

        jPanel1.setBackground(new java.awt.Color(220, 75, 75));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 400));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));

        SaveButton.setBackground(new java.awt.Color(229, 105, 105));
        SaveButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        SaveButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveButton.setText("ADD PLAYER");
        SaveButton.setToolTipText("");
        SaveButton.setBorderPainted(false);
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        DoneButton.setBackground(new java.awt.Color(229, 105, 105));
        DoneButton.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        DoneButton.setForeground(new java.awt.Color(255, 255, 255));
        DoneButton.setText("PLAY GAME");
        DoneButton.setBorderPainted(false);
        DoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoneButtonActionPerformed(evt);
            }
        });

        pidTextBox.setBackground(new java.awt.Color(229, 105, 105));
        pidTextBox.setForeground(new java.awt.Color(255, 255, 255));
        pidTextBox.setPreferredSize(new java.awt.Dimension(64, 30));

        INSERT_TITLE.setFont(new java.awt.Font("Helvetica Neue", 1, 41)); // NOI18N
        INSERT_TITLE.setForeground(new java.awt.Color(255, 255, 255));
        INSERT_TITLE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        INSERT_TITLE.setText("INSERT PLAYER NAMES");

        PLAYERNAME_LABEL.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        PLAYERNAME_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        PLAYERNAME_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PLAYERNAME_LABEL.setText("PLAYER NAME:");

        pidOneLabel.setBackground(new java.awt.Color(229, 105, 105));
        pidOneLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        pidOneLabel.setForeground(new java.awt.Color(255, 255, 255));
        pidOneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pidOneLabel.setOpaque(true);

        pidTwoLabel.setBackground(new java.awt.Color(229, 105, 105));
        pidTwoLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        pidTwoLabel.setForeground(new java.awt.Color(255, 255, 255));
        pidTwoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pidTwoLabel.setToolTipText("");
        pidTwoLabel.setOpaque(true);

        pidThreeLabel.setBackground(new java.awt.Color(229, 105, 105));
        pidThreeLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        pidThreeLabel.setForeground(new java.awt.Color(255, 255, 255));
        pidThreeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pidThreeLabel.setOpaque(true);

        pidFourLabel.setBackground(new java.awt.Color(229, 105, 105));
        pidFourLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        pidFourLabel.setForeground(new java.awt.Color(255, 255, 255));
        pidFourLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pidFourLabel.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Player One");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Player Three");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Player Four");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Player Two");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(INSERT_TITLE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(PLAYERNAME_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pidTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SaveButton)
                .addGap(0, 38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pidOneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pidTwoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pidThreeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pidFourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(DoneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(INSERT_TITLE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pidTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PLAYERNAME_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pidOneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pidThreeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pidFourLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pidTwoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(DoneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
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

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        // TODO add your handling code here:
        if(pidTextBox.getText().isEmpty()){
            JLabel message = new JLabel("Please enter a player name!");
            message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null,message);
        }
        else{
            String name = pidTextBox.getText().trim();
            playerIds.add(name);
            
            if(playerIds.size() == 1){
                pidOneLabel.setText(playerIds.get(0));
            }
            else if(playerIds.size() == 2){
                pidOneLabel.setText(playerIds.get(0));
                pidTwoLabel.setText(playerIds.get(1));
            }
            else if(playerIds.size() == 3){
                pidOneLabel.setText(playerIds.get(0));
                pidTwoLabel.setText(playerIds.get(1));
                pidThreeLabel.setText(playerIds.get(2));
            }
            else if(playerIds.size() == 4){
                pidOneLabel.setText(playerIds.get(0));
                pidTwoLabel.setText(playerIds.get(1));
                pidThreeLabel.setText(playerIds.get(2));
                pidFourLabel.setText(playerIds.get(3));
            }
            if(playerIds.size() > 0 && playerIds.size() < 5){
                JLabel message = new JLabel("Save successful!");
                message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
                pidTextBox.setText("");
                
            }
            if(playerIds.size() == 5){
                playerIds.remove(name);
                JLabel message = new JLabel("Can only have 2 - 4 players for Uno Game.");
                message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
                JOptionPane.showMessageDialog(null,message);
            }
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void DoneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoneButtonActionPerformed
        // TODO add your handling code here:
        if(playerIds.size() == 1 || playerIds.size() == 0){
            JLabel message = new JLabel("Mimimum of 2 players is required to play.");
            message.setFont(new Font("Helvetica Neue", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null,message);
        }
        else{
            this.dispose();
            new GameStage(playerIds).setVisible(true);
        }
    }//GEN-LAST:event_DoneButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AddPlayerNames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPlayerNames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPlayerNames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPlayerNames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPlayerNames().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DoneButton;
    private javax.swing.JLabel INSERT_TITLE;
    private javax.swing.JLabel PLAYERNAME_LABEL;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pidFourLabel;
    private javax.swing.JLabel pidOneLabel;
    private javax.swing.JTextField pidTextBox;
    private javax.swing.JLabel pidThreeLabel;
    private javax.swing.JLabel pidTwoLabel;
    // End of variables declaration//GEN-END:variables
}
