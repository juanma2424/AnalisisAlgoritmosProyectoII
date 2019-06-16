package conjuronet;

public enum MessageType {
    PLAYER_NAME(0),
    CARDS_SELECTED(1),
    CONJURO(2),
    CARD_FOUND(3),
    KEY_MSG(4),
    DEFEAT(5);
    
    
    public int value;

    MessageType(int pValue) {
        this.value = pValue;
    }
}