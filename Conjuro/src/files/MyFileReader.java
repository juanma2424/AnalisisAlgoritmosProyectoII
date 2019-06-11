package files;

import java.io.*;
import java.util.Random;
import static lib.Constants.*;

public class MyFileReader {

    private String[] conjuroAES = new String[CANT_SAVE];
    private String[] conjuroSHA = new String[CANT_SAVE];
    private String[] keys = new String[CANT_SAVE];
    private String[] dataPlay = new String[CANT_DATA_PLAY];

    public void readConjuro(String pPathBook, String pPathkeys) throws java.io.IOException {

        String readLinebyLine;
        String readKey;
        String buildStr = "";

        BufferedReader bufferBook = new BufferedReader(new FileReader(pPathBook));
        BufferedReader bufferKey = new BufferedReader(new FileReader(pPathkeys));
        readLinebyLine = bufferBook.readLine();

        int indexAES = DATA_CERO;
        int indexSHA = DATA_CERO;
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
        makeDataPlay();
    }

    private void makeDataPlay() {
        Random r = new Random();
        int intPost = r.nextInt((MAX_DATA_PLAY - MIN_DATA_PLAY) + DATA_ONE) + MIN_DATA_PLAY;
        dataPlay[DATA_CERO] = conjuroAES[intPost];
        dataPlay[DATA_ONE] = conjuroSHA[intPost];
        dataPlay[DATA_TWO] = keys[intPost];
    }

    //------------------------GETS-------------------------//
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

    public void write() {
        try {
             
            FileWriter fileWriter = new FileWriter("C:\\Users\\USER\\Documents\\GitHub\\AnalisisAlgoritmosProyectoII\\C++\\DataPlay.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            //printWriter.println(LIMIT_FILE);
            printWriter.println(getDataPlay()[DATA_CERO]);
            printWriter.println("X--X");
            printWriter.println(getDataPlay()[DATA_ONE]);
            printWriter.println("X-Y-X");
            printWriter.println(getDataPlay()[DATA_TWO]);
            printWriter.println("X-X-X");
            printWriter.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        MyFileReader a = new MyFileReader();
        a.readConjuro("C:\\Users\\USER\\Desktop\\Nueva carpeta\\Conjuro.txt", "C:\\Users\\USER\\Desktop\\Nueva carpeta\\Save.txt");
        a.write();

    }
}