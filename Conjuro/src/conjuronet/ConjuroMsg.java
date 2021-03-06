/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuronet;

import java.util.*;
import lib.Constants;

public class ConjuroMsg implements Constants {

    private Hashtable<String, String> values;
    private MessageType type;

    // formato del msg
    // Type, key=value, key=value...key=value
    public ConjuroMsg(String pData) {
        String[] msgValues = pData.split(MESSAGE_SEPARATOR);
        
        if (msgValues != null && msgValues.length > 0) {
            type = MessageType.values()[Integer.parseInt(msgValues[0])];
            //type = MessageType.values()[1];

            values = new Hashtable<String, String>();

            for (int valuesIndex = 1; valuesIndex < msgValues.length; valuesIndex++) {
                String[] keyMap = msgValues[valuesIndex].split(MESSAGE_VALUES_SEPARATOR);
                System.out.println(keyMap[0]);
                values.put(keyMap[0], keyMap[1]);
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
