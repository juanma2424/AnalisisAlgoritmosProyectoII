package GUI;

import logic.Controller;

public class Cliente extends javax.swing.JFrame {

    /**
     * Creates new form Cliente
     */
    private int click = 0;
    private int jugada = 0;
    private boolean setMsg = true;
    private Controller globalController ;
    
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
        jButton8.setEnabled(false);
        jButton9.setEnabled(false);
        jButton10.setEnabled(false);
    }
    
    private void setCart(String Pdata){
        String auxGettxt;
        click++;
        if(setMsg ){
        if(jTextField1.getText().length()!=0 ){
             auxGettxt = jTextField1.getText();
             jTextField1.setText(auxGettxt+","+Pdata); 
             if (click==3) jButton8.setEnabled(true);//setMsg=true;
        }else{
             jTextField1.setText(Pdata);
        }
        if(click>3 ){
            jTextField1.setText(Pdata);
            jButton8.setEnabled(false);//setMsg=false;
            click =1;
        }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
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

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 0, 51));
        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTextField1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 420, 90));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 51));
        jTextField2.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 210, 60));

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

        jButton10.setText("Editar jugada 1");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, -1));

        jButton9.setText("Editar jugada 1");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, -1, -1));

        jButton8.setText("SetJugada");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 100, 40));

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
         setCart("c3=3des");   
    }//GEN-LAST:event_C3ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
        setCart("c1=sha256");
    }//GEN-LAST:event_C1ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
         setCart("c4=aes");   
    }//GEN-LAST:event_C4ActionPerformed

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
         setCart("c6=rsa");   
    }//GEN-LAST:event_C6ActionPerformed

    private void C5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C5ActionPerformed
        // TODO add your handling code here:
         setCart("c5=plain");   
    }//GEN-LAST:event_C5ActionPerformed

    private void C7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C7ActionPerformed
        // TODO add your handling code here:
         setCart("c7=pgp");   
    }//GEN-LAST:event_C7ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        // TODO add your handling code here:
         setCart("c2=md5");   
    }//GEN-LAST:event_C2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        if(jugada==0){
            jTextField3.setText(jTextField1.getText());
            jugada++;
            jButton9.setEnabled(true);
        }else{
             jTextField2.setText(jTextField1.getText());
             jButton10.setEnabled(true);
             setMsg = false;
        }
         jTextField1.setText(null);
        jButton8.setEnabled(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jTextField3.setText(null);
        jugada=0;
        jButton9.setEnabled(false);
         setMsg = true;
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        jTextField2.setText(null);
        jButton9.setEnabled(false);
         setMsg = true;
    }//GEN-LAST:event_jButton10ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton C1;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}