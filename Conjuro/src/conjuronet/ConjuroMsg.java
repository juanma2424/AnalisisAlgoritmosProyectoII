package conjuronet;

import java.util.*;
import lib.Constants;

public class ConjuroMsg implements Constants {
  
    private Hashtable<String, String> values;
    private MessageType type;

    
    // APPLI FORMAT MSG AND GET VALUE OF MSG XX
    public ConjuroMsg(String pData) {
        
        String[] msgValues = pData.split(MESSAGE_SEPARATOR);
        
        if (msgValues != null && msgValues.length > DATA_CERO) {
            type = MessageType.values()[Integer.parseInt(msgValues[DATA_CERO])];
            values = new Hashtable<String, String>();
            for (int valuesIndex = DATA_ONE; valuesIndex < msgValues.length; valuesIndex++) {
                int divKey = msgValues[valuesIndex].indexOf(MESSAGE_VALUES_SEPARATOR);
                String[] keyMap = {msgValues[valuesIndex].substring(DATA_CERO ,divKey), msgValues[valuesIndex].substring(divKey+1)};
                values.put(keyMap[DATA_CERO ], keyMap[DATA_ONE]);
            }
        }
    }

    public ConjuroMsg(MessageType pType) {
        this.type = pType;
        values = new Hashtable<String, String>();
    }

    public String getValue(String pKey) {
        String result = "";
        result = values.containsKey(pKey) ? values.get(pKey) : result;
        return result;
    }

    public void addValue(String pKey, String pValue) {
        values.put(pKey, pValue);
    }

    public String getStringMsg() {
        String result = "";
        String comma = "";

        result = result.concat(this.type.value + MESSAGE_SEPARATOR);

        for (String key : values.keySet()) {
            result = result.concat(comma);
            result = result.concat(key + MESSAGE_VALUES_SEPARATOR + values.get(key));
            comma = MESSAGE_SEPARATOR;
        }

        return result;
    }

    public MessageType getType() {
        return type;
    }
}