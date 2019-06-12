/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import logic.Controller;

/**
 *
 * @author Juanma
 */
public class guiLogic {

    private int globalClicks = 0;
    private boolean readyMoveOne = false;
    private boolean readyMoveTwo = false;
    private int globalJugada = 0;
    private Controller globalController;
    private javax.swing.JButton C1;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    
    public guiLogic(Controller pController){
        globalController = pController;
    }

    private void setCart(String pData,javax.swing.JTextField textMove,javax.swing.JButton sendMove) {
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

    public void setjubutton(javax.swing.JButton pC1, javax.swing.JButton pC2, javax.swing.JButton pC3,
            javax.swing.JButton pC4, javax.swing.JButton pC5, javax.swing.JButton pC6,
            javax.swing.JButton pC7) {
        C1 = pC1;
        C2 = pC2;
        C3 = pC3;
        C4 = pC4;
        C5 = pC5;
        C6 = pC6;
        C7 = pC7;
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

    private void cleanMove(int pData) {
        globalJugada = globalJugada - 3;
        globalController.cleanMove(pData);

    }

    private void text() {
        if (globalJugada < 3) {
            globalController.selectCard(0, true);
        } else {
            globalController.selectCard(0, false);
        }
        globalController.selectCard(0, true);
        globalJugada++;
    }
    
    public void setText(String pData, javax.swing.JButton jData,
                        javax.swing.JTextField textMove,javax.swing.JButton sendMove) {
        setCart(pData,textMove,sendMove);
        jData.setEnabled(false);
        text();
    }

}
