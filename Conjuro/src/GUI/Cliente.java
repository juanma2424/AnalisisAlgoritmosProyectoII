package GUI;

import logic.Controller;

public class Cliente extends javax.swing.JFrame {

    /**
     * Creates new form Cliente
     */
    private int globalClicks = 0;
    private boolean readyMoveOne = false;
    private boolean readyMoveTwo = false;
    private int globalJugada = 0;
    private Controller globalController;

    public Cliente(Controller pController) {
        initComponents();
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
    }

    private void setCart(String pData) {
        String lastTexMove;
        globalClicks++;
        if (!readyMoveOne || !readyMoveTwo) {

            // si ya existe algo escrito
            if (textMove.getText().length() != 0) {
                lastTexMove = textMove.getText();
                textMove.setText(lastTexMove + "," + pData);
                if (globalClicks == 3) {//si global clicks es igual a 3
                    sendMove.setEnabled(true);//setMsg=true;
                }
            } else {
                textMove.setText(pData);// si es la primera vez que se escribe
            }
            if (globalClicks > 3) {
                textMove.setText(pData);
                sendMove.setEnabled(false);//setMsg=false;
                globalClicks = 1;// global click refresh
                avalibleORNot(true);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textMove = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
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

        textMove.setEditable(false);
        textMove.setBackground(new java.awt.Color(0, 0, 51));
        textMove.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        textMove.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(textMove);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 420, 90));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 51));
        jTextField2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 210, 60));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 180, 120));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 320, 180));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(0, 0, 51));
        jTextField3.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 210, 60));

        jButton1.setText("SEND MOVES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, 150, 60));

        editMoveTwo.setText("Editar jugada 1");
        editMoveTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMoveTwoActionPerformed(evt);
            }
        });
        jPanel2.add(editMoveTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, -1));

        editMoveOne.setText("Editar jugada 1");
        editMoveOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMoveOneActionPerformed(evt);
            }
        });
        jPanel2.add(editMoveOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, -1, -1));

        sendMove.setText("SetJugada");
        sendMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMoveActionPerformed(evt);
            }
        });
        jPanel2.add(sendMove, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 100, 40));

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
        setText("c3=3des", C3,2);
    }//GEN-LAST:event_C3ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
        setText("c1=sha256", C1,0);
    }//GEN-LAST:event_C1ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
        setText("c4=aes", C4,3);
    }//GEN-LAST:event_C4ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
        setText("c6=rsa", C6,5);
    }//GEN-LAST:event_C6ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
        setText("c5=plain", C5,4);
    }//GEN-LAST:event_C5ActionPerformed

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        // TODO add your handling code here:
        setText("c7=pgp", C7,6);
    }//GEN-LAST:event_C7ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
        setText("c2=md5", C2,1);
    }//GEN-LAST:event_C2ActionPerformed

    private void sendMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMoveActionPerformed
        // TODO add your handling code here:
        selectMove();
        textMove.setText(null);// CLEAN
        sendMove.setEnabled(false);
        if (readyMoveOne && readyMoveTwo) {
            jButton1.setEnabled(true);
        }
        avalibleORNot(true);
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
        readyMoveOne = false;///
        cleanMove(0);
        avalibleORNot(true);
    }//GEN-LAST:event_editMoveOneActionPerformed

    private void editMoveTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMoveTwoActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(false);
        avalibleORNot(true);
        jTextField2.setText(null);
        textMove.setText(null);
        editMoveTwo.setEnabled(false);
        readyMoveTwo = false;///
        cleanMove(1);
    }//GEN-LAST:event_editMoveTwoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        globalController.sendMoves();
        jButton1.setEnabled(false);
        editMoveTwo.setEnabled(false);
        editMoveOne.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void appendMoves(String pMove){
        jTextArea1.append(pMove+"\n");
    }
    
    public void appendFindCards(String pType){
        jTextArea2.append(pType+"\n");
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
        if(globalJugada == 6)
            globalJugada = globalJugada - 3;
        else{
            globalJugada = 0;
        }
        this.globalClicks = 0;
        globalController.cleanMove(pData);

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
            jTextField3.setText(textMove.getText());
            editMoveOne.setEnabled(true);
            readyMoveOne = true;
        } else {
             avalibleORNot(false);
            jTextField2.setText(textMove.getText());
            editMoveTwo.setEnabled(true);
            readyMoveTwo = true;
           
        }
    }

    private void setText(String pData, javax.swing.JButton jData, int pNum) {
        if (!readyMoveOne || !readyMoveTwo) {
            selectedCard(pNum);
            setCart(pData);
            jData.setEnabled(false);
        }
    }
    
    private void selectedCard(int pCard) {
            globalController.selectCard(pCard, !readyMoveOne);
        globalJugada++;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton sendMove;
    private javax.swing.JTextField textMove;
    // End of variables declaration//GEN-END:variables
}
