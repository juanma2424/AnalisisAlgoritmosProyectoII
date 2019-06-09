/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.*;
import java.util.Random;
import static lib.Constants.*;

public class MyFileReader {

    private String[] conjuroAES = new String[CANT_SAVE];
    private String[] conjuroSHA = new String[CANT_SAVE];
    private String[] keys = new String[CANT_SAVE];
    private String[] dataPlay = new String[3];

    public String[] getDataPlay() {
        return dataPlay;
    }

    public String[] getConjuroAES() {
        return conjuroAES;
    }

    public String[] getConjuroSHA() {
        return conjuroSHA;
    }

    public String[] getKeys() {
        return keys;
    }

    public void readConjuro(String pPathBook, String pPathkeys) throws java.io.IOException {

        String readLinebyLine;
        String readKey;
        String buildStr = "";

        BufferedReader bufferBook = new BufferedReader(new FileReader(pPathBook));
        BufferedReader bufferKey = new BufferedReader(new FileReader(pPathkeys));
        readLinebyLine = bufferBook.readLine();

        int indexAES = 0;
        int indexSHA = 0;
        while (!(LIMIT_FILE).equals(readLinebyLine)) {

            if ((LIMIT_ENCRYPTATION_AES).equals(readLinebyLine)) {

                conjuroAES[indexAES] = buildStr;

                readKey = bufferKey.readLine();
                keys[indexAES] = readKey;

                indexAES++;
                buildStr = "";
            }
            if ((LIMIT_ENCRYPTATION_SHA256).equals(readLinebyLine)) {
                conjuroSHA[indexSHA] = buildStr;
                indexSHA++;
                buildStr = "";
            }
            if (!(LIMIT_ENCRYPTATION_SHA256).equals(readLinebyLine)
                    && !(LIMIT_ENCRYPTATION_AES).equals(readLinebyLine)) {
                buildStr = buildStr + readLinebyLine;//build strg
            }
            readLinebyLine = bufferBook.readLine();// skip lines

        }
        bufferBook.close();
        bufferKey.close();
    }

    private void makeDataPlay() {

        Random r = new Random();
        int intPost = r.nextInt((99 - 0) + 1) + 0;
        dataPlay[0] = conjuroAES[intPost];
        dataPlay[1] = conjuroSHA[intPost];
        dataPlay[2] = keys[intPost];

    }

    public static void main(String[] args) throws java.io.IOException {
        MyFileReader a = new MyFileReader();
        a.readConjuro("C:\\Users\\USER\\Desktop\\Nueva carpeta\\Conjuro.txt", "C:\\Users\\USER\\Desktop\\Nueva carpeta\\Save.txt");
        a.makeDataPlay();
        System.out.println(a.getDataPlay()[0]);
        System.out.println(a.getDataPlay()[1]);
        System.out.println(a.getDataPlay()[2]);
    }

}
