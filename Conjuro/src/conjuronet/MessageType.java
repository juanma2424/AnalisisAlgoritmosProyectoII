package conjuronet;

import static lib.Constants.*;

public enum MessageType {
    PLAYER_NAME(DATA_CERO),
    CARDS_SELECTED(DATA_ONE),
    CONJURO(DATA_TWO),
    CARD_FOUND(DATA_THREE),
    KEY_MSG(DATA_FOUR),
    DEFEAT(DATA_FIVE);
  
    public int value;

    MessageType(int pValue) {
        this.value = pValue;
    }
}