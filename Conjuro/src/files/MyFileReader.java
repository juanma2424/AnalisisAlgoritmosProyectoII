package files;

import java.io.*;
import java.util.Random;
import static lib.Constants.*;

public class MyFileReader {

    private String[] keys = new String[CANT_SAVE];
    private String dataSave;

    public void readConjuro(String pPathkeys) throws java.io.IOException {

        String auxKey = "";
        BufferedReader bufferKey = new BufferedReader(new FileReader(pPathkeys));
        int indexKEY = DATA_CERO;
        while (indexKEY < MAX_KEYS) {
            auxKey = bufferKey.readLine();
            keys[indexKEY] = auxKey;
            indexKEY++;
            auxKey = "";
        }
        bufferKey.close();
        makeDataPlay();
    }

    private void makeDataPlay() {
        Random r = new Random();
        int intPost = r.nextInt((MAX_DATA_PLAY - MIN_DATA_PLAY) + DATA_ONE) + MIN_DATA_PLAY;
        dataSave = keys[intPost];

    }

    public String getDataKey() {
        return dataSave;
    }

    public void write() {
        try {

            FileWriter fileWriter = new FileWriter("C:\\Users\\USER\\Documents\\GitHub\\AnalisisAlgoritmosProyectoII\\C++\\DataPlay.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(getDataKey());
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        MyFileReader a = new MyFileReader();
        a.readConjuro("C:\\Users\\USER\\Desktop\\Nueva carpeta\\Save.txt");
        a.write();
    }

}
