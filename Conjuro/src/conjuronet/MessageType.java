/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuronet;

public enum MessageType {
    PLAYER_NAME(0),
    CARDS_SELECTED(1);

    public int value;

    MessageType(int pValue) {
        this.value = pValue;
    }
}