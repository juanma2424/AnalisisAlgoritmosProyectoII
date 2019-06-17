package GUI;


import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import lib.Constants;
import static lib.Constants.LIMIT_TIME;
import logic.Controller;

public class Cliente extends javax.swing.JFrame implements Constants {

    /**
     * Creates new form Cliente
     */
    private int globalClicks = DATA_CERO;
    private boolean readyMoveOne = false;
    private boolean readyMoveTwo = false;
    private int globalJugada = DATA_CERO;
    private Controller globalController;
    private int counter;
    private int limit;
    private String strLimit;
    private Timer timer;

    public Cliente(Controller pController) {
        initComponents();
        counter = 0;
        limit =0;
        globalController = pController;
        C1.setToolTipText("Name: C1 | Tipe: Sha256 |desc cifrada | desc no cifrada| key1 | key2");
        C2.setToolTipText("Name: C2 | Tipe: MD5 |desc cifrada | desc no cifrada| key1 | key2");
        C3.setToolTipText("Name: C3 | Tipe: 3DES |desc cifrada | desc no cifrada| key1 | key2");
        C4.setToolTipText("Name: C4 | Tipe: AES |desc cifrada | desc no cifrada| key1 | key2");
        C5.setToolTipText("Name: C5 | Tipe: Plain |desc cifrada | desc no cifrada| key1 | key2");
        C6.setToolTipText("Name: C6 | Tipe: RSA |desc cifrada | desc no cifrada| key1 | key2");
        C7.setToolTipText("Name: C7 | Tipe: PGP |desc cifrada | desc no cifrada| key1 | key2");
        sendMove.setEnabled(false);
        jButton1.setEnabled(false);
        editMoveOne.setEnabled(false);
        editMoveTwo.setEnabled(false);
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        jTextArea4.setEditable(false);
        jTextArea4.append("My cards found :\n");
        jTextArea2.append("Opponent cards found :\n");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        startTimer();
        String strLimit ="";
        jTextField1.setText("Tiempo Restante : \n  12 Min");
    }

    private void setCart(String pData) {
        String lastTexMove;
        globalClicks++;
        if (!readyMoveOne || !readyMoveTwo) {

            // !IF EMPTY
            if (textMove.getText().length() != DATA_CERO) {
                lastTexMove = textMove.getText();
                textMove.setText(lastTexMove + "," + pData);
                if (globalClicks == JUGADA_NUMBER) {//IF GLOBAL CLICKS AS EQUAL A 3
                    sendMove.setEnabled(true);//SETMSG = TRUE 
                }
            } else {
                textMove.setText(pData);// ITS FIRST TIME TO WRITE
            }
            if (globalClicks > JUGADA_NUMBER) {
                textMove.setText(pData);// SAVE DATA
                sendMove.setEnabled(false);//SETMSG = FALSE
                globalClicks = DATA_ONE;//GLOBALCLICKS REFRESH
                avalibleORNot(true);//AVALIBLE BUTTON CARDS
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        textMove = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        editMoveTwo = new javax.swing.JButton();
        editMoveOne = new javax.swing.JButton();
        sendMove = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        C1 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        C6 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        C7 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1084, 646));

        jPanel2.setMaximumSize(new java.awt.Dimension(1084, 646));
        jPanel2.setMinimumSize(new java.awt.Dimension(1084, 646));
        jPanel2.setPreferredSize(new java.awt.Dimension(1084, 646));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/opponent.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 200, 60));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/msg.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 90, 60));

        jTextArea3.setBackground(new java.awt.Color(0, 0, 51));
        jTextArea3.setColumns(20);
        jTextArea3.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 290, 150));

        textMove.setEditable(false);
        textMove.setBackground(new java.awt.Color(0, 0, 51));
        textMove.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        textMove.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(textMove);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 420, 90));

        jTextArea5.setEditable(false);
        jTextArea5.setBackground(new java.awt.Color(0, 0, 51));
        jTextArea5.setColumns(20);
        jTextArea5.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea5.setRows(5);
        jTextArea5.setText("\tKEY");
        jScrollPane6.setViewportView(jTextArea5);

        jPanel2.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 580, 620, 60));

        jLabel1.setBackground(new java.awt.Color(0, 0, 51));
        jLabel1.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 580, 170, 50));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 51));
        jTextField2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 210, 60));

        jTextArea6.setEditable(false);
        jTextArea6.setBackground(new java.awt.Color(0, 0, 51));
        jTextArea6.setColumns(20);
        jTextArea6.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea6.setRows(5);
        jScrollPane7.setViewportView(jTextArea6);

        jPanel2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 290, 150));

        jTextArea2.setBackground(new java.awt.Color(0, 0, 51));
        jTextArea2.setColumns(20);
        jTextArea2.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 200, 80));

        jTextArea1.setBackground(new java.awt.Color(0, 0, 51));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 420, 130));

        jTextArea4.setBackground(new java.awt.Color(0, 0, 51));
        jTextArea4.setColumns(20);
        jTextArea4.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea4.setRows(5);
        jScrollPane5.setViewportView(jTextArea4);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 210, 80));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 190, 60));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(0, 0, 51));
        jTextField3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 210, 60));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/sendMoves.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 280, 220, 60));

        editMoveTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edj2.png"))); // NOI18N
        editMoveTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMoveTwoActionPerformed(evt);
            }
        });
        jPanel2.add(editMoveTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, 170, 40));

        editMoveOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edj1.png"))); // NOI18N
        editMoveOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMoveOneActionPerformed(evt);
            }
        });
        jPanel2.add(editMoveOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 150, 40));

        sendMove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/setjugada.png"))); // NOI18N
        sendMove.setText("SetJugada");
        sendMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMoveActionPerformed(evt);
            }
        });
        jPanel2.add(sendMove, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 150, 50));

        C3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/carta3.png"))); // NOI18N
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });
        jPanel2.add(C3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, 120, 170));

        C1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/carta1.png"))); // NOI18N
        C1.setToolTipText("");
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });
        jPanel2.add(C1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 120, 170));

        C4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/carta4.png"))); // NOI18N
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });
        jPanel2.add(C4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 120, 170));

        C6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/carta5.png"))); // NOI18N
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });
        jPanel2.add(C6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 400, 120, 170));

        C5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/carta6.png"))); // NOI18N
        C5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C5ActionPerformed(evt);
            }
        });
        jPanel2.add(C5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, 120, 170));

        C7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/carta7.png"))); // NOI18N
        C7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C7ActionPerformed(evt);
            }
        });
        jPanel2.add(C7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 400, 120, 170));

        C2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/carta2.png"))); // NOI18N
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });
        jPanel2.add(C2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 120, 170));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/wp.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setMaximumSize(new java.awt.Dimension(1084, 646));
        jLabel2.setMinimumSize(new java.awt.Dimension(1084, 646));
        jLabel2.setPreferredSize(new java.awt.Dimension(1084, 646));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
        setText("c3=3des", C3, DATA_TWO);
    }//GEN-LAST:event_C3ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
        setText("c1=sha256", C1, DATA_CERO);
    }//GEN-LAST:event_C1ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
        setText("c4=aes", C4, DATA_THREE);
    }//GEN-LAST:event_C4ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
        setText("c6=rsa", C6, DATA_FIVE);
    }//GEN-LAST:event_C6ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
        setText("c5=plain", C5, DATA_FOUR);
    }//GEN-LAST:event_C5ActionPerformed

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        // TODO add your handling code here:
        setText("c7=pgp", C7, DATA_SIX);
    }//GEN-LAST:event_C7ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
        setText("c2=md5", C2, DATA_ONE);
    }//GEN-LAST:event_C2ActionPerformed

    private void sendMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMoveActionPerformed
        // TODO add your handling code here:
        selectMove();
        textMove.setText(null);// CLEAN GUI
        sendMove.setEnabled(false);
        if (readyMoveOne && readyMoveTwo) {
            jButton1.setEnabled(true);//AVALIBLE BUTTON TRUE
        }
        avalibleORNot(true);// SET AVALIBLE ALLCARDS
    }//GEN-LAST:event_sendMoveActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void editMoveOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMoveOneActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(false);
        jTextField3.setText(null);
        textMove.setText(null);
        editMoveOne.setEnabled(false);
        readyMoveOne = false;
        cleanMove(DATA_CERO);//CLEAN MOVE IN CONTROLLER 0 BECAUSE IS A FIRST MOVE
        avalibleORNot(true);// SET AVALIBLE ALLCARDS
    }//GEN-LAST:event_editMoveOneActionPerformed

    private void editMoveTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMoveTwoActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(false);
        avalibleORNot(true);// SET AVALIBLE ALLCARDS
        jTextField2.setText(null);
        textMove.setText(null);
        editMoveTwo.setEnabled(false);
        readyMoveTwo = false;
        cleanMove(DATA_ONE);// CLEAN MOVE IN CONTROLLER 0 BECAUSE IS A SECOND MOVE
    }//GEN-LAST:event_editMoveTwoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        globalController.sendMoves();// SENDMOVES TO CONTROLLER 
        jButton1.setEnabled(false);
        editMoveTwo.setEnabled(false);
        editMoveOne.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        globalController.sendConjuro(jTextArea3.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public void appendMoves(String pMove) {
        jTextArea1.append(pMove + "\n");
    }

    public void appendFindCards(String pType) {
        jTextArea2.append(pType + "\n");
    }

    public void appendFindCardsC(String pType) {
        jTextArea4.append(pType + "\n");
    }

    public void viewConjuro(String pConjuro) {
        jTextArea6.append(pConjuro);
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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    private void cleanMove(int pData) {
        if (globalJugada == SELECT_CARDS) {// IF ARE TWO MOVES  
            globalJugada = globalJugada - JUGADA_NUMBER;
        } else {
            globalJugada = DATA_CERO;
        }

        this.globalClicks = DATA_CERO;
        globalController.cleanMove(pData);// CLEAN DATA IN CONTROLLER 
    }

    private void avalibleORNot(boolean pData) {
        C1.setEnabled(pData);
        C2.setEnabled(pData);
        C3.setEnabled(pData);
        C4.setEnabled(pData);
        C5.setEnabled(pData);
        C6.setEnabled(pData);
        C7.setEnabled(pData);
    }

    private void selectMove() {
        if (!readyMoveOne) {
            jTextField3.setText(textMove.getText());//GET MOVES TO TEXT PRINCIPAL BOARD
            editMoveOne.setEnabled(true);//EDIT MOVE ONE
            readyMoveOne = true; //MOVE ONE READY
        } else {
            avalibleORNot(false);// GET MOVE TO TEXT PRINCIPAL BOARD
            jTextField2.setText(textMove.getText());
            editMoveTwo.setEnabled(true);//EDIT MOVE TOW
            readyMoveTwo = true;// MOVE TWO READY
        }
    }

    private void setText(String pData, javax.swing.JButton jData, int pNum) {
        if (!readyMoveOne || !readyMoveTwo) {// !IF TWO MOVES ARENT DONE
            selectedCard(pNum);//SELECT CARD BY NUM CARD 
            setCart(pData);// SET CARD INTO GUI
            jData.setEnabled(false);//SET ENABLE CARD 
        }
    }

    private void selectedCard(int pCard) {
        globalController.selectCard(pCard, !readyMoveOne);
        globalJugada++;
    }

    public void viewKey(String pKey) {
        jTextArea5.append("\n" + pKey);
    }

    public void setNameVS(String pName) {
        jLabel1.setText(pName);
    }
    
    public void gameOver() {
        this.dispose();
         JOptionPane.showMessageDialog(null, "GAME OVER");
    }

    public void startTimer() {
        
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            }
        };

        //create thread to print counter value
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        if ( (LIMIT_TIME - counter) == DATA_CERO ) {
                            timer.cancel();
                            gameOver();
                            break;
                        }
                        
                        Thread.sleep(TIME_GAME_REFRESH);
                        counter++;
                        limit = LIMIT_TIME - counter ;
                        strLimit = Integer.toString(limit);
                        
                        
                        if ( (LIMIT_TIME - counter) <  DANGER_TIME) {
                           jTextField1.setText("Tiempo Restante : \n "+strLimit +" Min");
                           jTextField1.setForeground(Color.red);
                        }else{
                          jTextField1.setText("Tiempo Restante : \n "+strLimit +" Min");
                        } 
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        timer = new Timer("MyTimer");//create a new timer
        timer.scheduleAtFixedRate(timerTask, 30, 3000);//start timer in 30ms to increment  counter
        t.start();//start thread to display counter

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton C1;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    private javax.swing.JButton editMoveOne;
    private javax.swing.JButton editMoveTwo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton sendMove;
    private javax.swing.JTextField textMove;
    // End of variables declaration//GEN-END:variables
}
