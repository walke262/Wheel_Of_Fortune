
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tylerjohnson
 */
public class Game extends javax.swing.JFrame {

    /**
     * Creates new form Game
     */
    Client client;
    Person mPerson = new Guest();
    Person[] players = new Person[3];
    Wheel wheel = new Wheel();
    Phrase phrase = new Phrase("temp", "temp");    
//    Object mObject;
//    Runnable tempRun = new Runnable()
//    {
//        
//        @Override
//        public void run()
//        {
//            boolean loopover = false;
//            Object tempO = null;
//            while (!loopover)
//            {
//                tempO = client.receiveMessage();
//                System.out.println(tempO);
//                if (tempO instanceof Person[])
//                {
//                    updateObject(tempO);
//                    loopover = true;
//                }
//                if (tempO instanceof Wheel)
//                {
//                    updateObject(tempO);
//                    loopover = true;
//                }
//                if (tempO instanceof Phrase)
//                {
//                    updateObject(tempO);
//                    loopover = true;
//                }
//                if (tempO instanceof String)
//                {
//                    updateObject(tempO);
//                    loopover = true;
//                }
//            }
//        }
//        
//        
//    };
    
    public Game() {
        initComponents();
        client = new Client("127.0.0.1", 1337);
        mPerson = new Guest();
        lblPlayers.setText("Players - You are: " + mPerson.getUserName());
    }
    
    public Game(String ip, int port, Person person)
    {
        initComponents();
        client = new Client(ip, port);
        mPerson = person;
        lblPlayers.setText("Players - You are: " + person.getUserName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblPhrase = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPhraseCategory = new javax.swing.JLabel();
        txtGuess = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnLetter = new javax.swing.JButton();
        btnPhrase = new javax.swing.JButton();
        lblPlayers = new javax.swing.JLabel();
        lblPl1Name = new javax.swing.JLabel();
        lblPl2Name = new javax.swing.JLabel();
        lblPl3Name = new javax.swing.JLabel();
        lblPl1Bal = new javax.swing.JLabel();
        lblPl2Bal = new javax.swing.JLabel();
        lblPl3Bal = new javax.swing.JLabel();
        btnSpin = new javax.swing.JButton();
        lblWheelSpin = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Puzzle");

        lblPhrase.setText("Phrase Text here");

        jLabel3.setText("Wheel");

        lblPhraseCategory.setText("Category Here");

        jLabel5.setText("Guess");

        btnLetter.setText("Guess Letter");
        btnLetter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLetterActionPerformed(evt);
            }
        });

        btnPhrase.setText("Guess Puzzle");
        btnPhrase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhraseActionPerformed(evt);
            }
        });

        lblPlayers.setText("Players");

        lblPl1Name.setText("Player1");

        lblPl2Name.setText("Player2");

        lblPl3Name.setText("Player3");

        lblPl1Bal.setText("$0");

        lblPl2Bal.setText("$100");

        lblPl3Bal.setText("$200");

        btnSpin.setText("Spin!");
        btnSpin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpinActionPerformed(evt);
            }
        });

        lblWheelSpin.setText("0");

        lblStatus.setText("Status:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPl1Bal)
                    .addComponent(lblPl1Name))
                .addGap(161, 161, 161)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPl2Name)
                    .addComponent(lblPl2Bal))
                .addGap(142, 142, 142)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPl3Bal)
                    .addComponent(lblPl3Name))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblPhrase, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblWheelSpin)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(124, 124, 124)
                                .addComponent(lblStatus)))
                        .addGap(286, 286, 286))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPhrase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSpin)
                        .addGap(236, 236, 236))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(btnLetter))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPhraseCategory)
                            .addComponent(lblPlayers)
                            .addComponent(txtGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(lblStatus))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblWheelSpin))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblPhrase, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(lblPhraseCategory)
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGuess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLetter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPhrase)
                    .addComponent(btnSpin))
                .addGap(26, 26, 26)
                .addComponent(lblPlayers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPl1Name)
                    .addComponent(lblPl2Name)
                    .addComponent(lblPl3Name))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPl1Bal)
                    .addComponent(lblPl2Bal)
                    .addComponent(lblPl3Bal))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLetterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLetterActionPerformed
        // TODO add your handling code here:
        String guess = txtGuess.getText();
        if (phrase.guessLetterClient(guess))
        {
            client.sendMessage(guess);
            txtGuess.setText("");
            disableButtons();
            
            //sleep();
            
            //waitOnServer();
        }
        else
        {
            txtGuess.setText("");
        }
    }//GEN-LAST:event_btnLetterActionPerformed

    private void btnPhraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhraseActionPerformed
        // TODO add your handling code here:
        String guess = txtGuess.getText();
        client.sendMessage(guess);
        txtGuess.setText("");
        disableButtons();
        
        //sleep();

        //waitOnServer();
    }//GEN-LAST:event_btnPhraseActionPerformed

    private void btnSpinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpinActionPerformed
        // TODO add your handling code here:
        client.sendMessage(true);
        disableButtons();
        
        //sleep();
        
        //waitOnServer();
    }//GEN-LAST:event_btnSpinActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        disableButtons();
        client.openConnection();
        client.sendMessage(mPerson);        
        client.addClientListener(new ClientListener() {
            public void CatchObject(Object obj)
            {
                clientSentObject(obj);
            }
        });
        
        Thread getMessages = new Thread(client);
        getMessages.start();

        //sleep();
        
        //waitOnServer();
    }//GEN-LAST:event_formWindowOpened

    private void clientSentObject(Object obj)
    {
        Object temp = obj;
        System.out.println(obj);
        if (temp instanceof Person[])
            {
                players = (Person[])temp;
                updatePlayers();
            }
            else if (temp instanceof Wheel)
            {
                wheel = (Wheel)temp;
                updateWheel();
            }
            else if (temp instanceof Phrase)
            {
                phrase = (Phrase)temp;
                System.out.println(((Phrase)temp).displayPhrase());
                updatePhrase();
            }
            else if (temp instanceof String)
            {
                String tempString = (String)temp;
                if (tempString.equals("SPIN"))
                {
                    //unlock spin button
                    disableButtons();
                    btnSpin.setEnabled(true);
                    //over = true;
                }
                else if (tempString.equals("TURN"))
                {
                    //Unlock Guess
                    disableButtons();
                    btnLetter.setEnabled(true);
                    btnPhrase.setEnabled(true);
                    //over = true;
                }
                else if (tempString.equals("TURNLETTER"))
                {
                    //Unlock Guess
                    disableButtons();
                    btnLetter.setEnabled(true);
                    //over = true;
                }
                else if (tempString.equals("TURNPHRASE"))
                {
                    //Unlock Guess
                    disableButtons();
                    btnPhrase.setEnabled(true);
                    //over = true;
                }
                else if (tempString.equals("END"))
                {
                    JOptionPane.showConfirmDialog(rootPane, "The Game is over!");
                    //over = true;
                }
                else
                {
                    lblStatus.setText("Status: " + tempString);
                }
            }
    }
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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
    
    private void updatePlayers()
    {
        lblPl1Name.setText(players[0].getUserName());
        lblPl1Bal.setText(Integer.toString(players[0].getCurrentBalance()));
        lblPl2Name.setText(players[1].getUserName());
        lblPl2Bal.setText(Integer.toString(players[1].getCurrentBalance()));
        lblPl3Name.setText(players[2].getUserName());
        lblPl3Bal.setText(Integer.toString(players[2].getCurrentBalance()));
//        this.remove(lblPl1Name);
//        this.remove(lblPl2Name);
//        this.remove(lblPl3Name);
//        this.remove(lblPl1Bal);
//        this.remove(lblPl2Bal);
//        this.remove(lblPl3Bal);
        revalidate();
        repaint();
        revalidate();
        //sleep();
    }
    
    private void updatePhrase()
    {
        lblPhrase.setText(phrase.displayPhrase());
        lblPhraseCategory.setText(phrase.getCategory());
//        this.remove(lblPhrase);
//        this.remove(lblPhraseCategory);
        revalidate();
        repaint();
        revalidate();
        //sleep();
    }
    
    private void updateWheel()
    {
        if (wheel.LastSpin() < 0)
        {
            lblWheelSpin.setText("Bankrupt");
        }
        else
        {
            lblWheelSpin.setText(Integer.toString(wheel.LastSpin()));
        }
//        this.remove(lblWheelSpin);
        revalidate();
        repaint();
        revalidate();
        //sleep();
    }
    
    private void disableButtons()
    {
        btnLetter.setEnabled(false);
        btnPhrase.setEnabled(false);
        btnSpin.setEnabled(false);
//        this.remove(btnLetter);
//        this.remove(btnPhrase);
//        this.remove(btnSpin);
        revalidate();
        repaint();
        revalidate();
        //sleep();
    }
    
//    private void updateObject(Object obj)
//    {
//        mObject = obj;
//    }
    
    private void waitOnServer()
    {
        Boolean over = false;
        while (!over)
        {
            //sleep();
            //SwingUtilities.invokeLater(client);
            //SwingUtilities.invokeLater(tempRun);
            //Thread tempThread = new Thread(tempRun);
//            tempThread.start();
//            try
//            {
//                tempThread.join();
//            }
//            catch (InterruptedException ex)
//            {
//                ex.printStackTrace();
//            }
            
//            try
//            {
//                SwingUtilities.invokeAndWait(tempRun);
//            }
//            catch (InterruptedException ex)
//            {
//                ex.printStackTrace();
//            }
//            catch (InvocationTargetException ex2)
//            {
//                ex2.printStackTrace();
//            }
            Object temp = null;
            System.out.println(temp);
            if (temp instanceof Person[])
            {
                players = (Person[])temp;
                updatePlayers();
            }
            else if (temp instanceof Wheel)
            {
                wheel = (Wheel)temp;
                updateWheel();
            }
            else if (temp instanceof Phrase)
            {
                phrase = (Phrase)temp;
                updatePhrase();
            }
            else if (temp instanceof String)
            {
                String tempString = (String)temp;
                if (tempString.equals("SPIN"))
                {
                    //unlock spin button
                    disableButtons();
                    btnSpin.setEnabled(true);
                    over = true;
                }
                else if (tempString.equals("TURN"))
                {
                    //Unlock Guess
                    disableButtons();
                    btnLetter.setEnabled(true);
                    btnPhrase.setEnabled(true);
                    over = true;
                }
                else if (tempString.equals("TURNLETTER"))
                {
                    //Unlock Guess
                    disableButtons();
                    btnLetter.setEnabled(true);
                    over = true;
                }
                else if (tempString.equals("TURNPHRASE"))
                {
                    //Unlock Guess
                    disableButtons();
                    btnPhrase.setEnabled(true);
                    over = true;
                }
                else if (tempString.equals("END"))
                {
                    JOptionPane.showConfirmDialog(rootPane, "The Game is over!");
                    over = true;
                }
                else
                {
                    lblStatus.setText("Status: " + tempString);
                }
            }
            revalidate();
            repaint();
        }
    }
    
    private void sleep()
    {
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLetter;
    private javax.swing.JButton btnPhrase;
    private javax.swing.JButton btnSpin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblPhrase;
    private javax.swing.JLabel lblPhraseCategory;
    private javax.swing.JLabel lblPl1Bal;
    private javax.swing.JLabel lblPl1Name;
    private javax.swing.JLabel lblPl2Bal;
    private javax.swing.JLabel lblPl2Name;
    private javax.swing.JLabel lblPl3Bal;
    private javax.swing.JLabel lblPl3Name;
    private javax.swing.JLabel lblPlayers;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblWheelSpin;
    private javax.swing.JTextField txtGuess;
    // End of variables declaration//GEN-END:variables
}
