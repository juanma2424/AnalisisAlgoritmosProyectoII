/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.*;
import static lib.Constants.*;

public class MyFileReader {

    public void readConjuro(String pPathBook, String pPathkeys) throws java.io.IOException {

        String readLinebyLine;
        String readKey;
        String buildStr = "";

        BufferedReader bufferBook = new BufferedReader(new FileReader(pPathBook));
        BufferedReader bufferKey = new BufferedReader(new FileReader(pPathkeys));
        readLinebyLine = bufferBook.readLine();

        while (!(LIMIT_FILE).equals(readLinebyLine)) {

            if ((LIMIT_ENCRYPTATION_AES).equals(readLinebyLine)) {
                System.out.println("encip " + buildStr);
                readKey = bufferKey.readLine();
                System.out.println("key " + readKey);
                buildStr = "";
            }
            if ((LIMIT_ENCRYPTATION_SHA256).equals(readLinebyLine)) {
                System.out.println("Sha " + buildStr);
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

    public static void main(String[] args) throws java.io.IOException {
        MyFileReader a = new MyFileReader();
        a.readConjuro("C:\\Users\\USER\\Desktop\\Nueva carpeta\\Conjuro.txt", "C:\\Users\\USER\\Desktop\\Nueva carpeta\\Save.txt");

    }

}
