package conjuronet;

public enum MessageType {
    PLAYER_NAME(0),
    CARDS_SELECTED(1),
    CONJURO(2),
    CARD_FOUND(3),
    DEFEAT(4);
    
    
    public int value;

    MessageType(int pValue) {
        this.value = pValue;
    }
}