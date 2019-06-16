package files;

import java.io.*;
import java.util.Random;
import static lib.Constants.*;

public class MyFileReader {

   

//    public String readKeys(String pPathkeys) throws java.io.IOException {
//
//        
//        Random rand = new Random();
//        int randomNum = rand.nextInt(  (MAX_KEYS - DATA_CERO) + 1) + DATA_CERO;
//        
//        String auxKey = "";
//        BufferedReader bufferKey = new BufferedReader(new FileReader(pPathkeys));
//        
//        int indexKEY = DATA_CERO;
//         
//        while (DATA_CERO < randomNum) {
//            auxKey = bufferKey.readLine();
//            indexKEY++;
//        }
//        bufferKey.close();
//        return auxKey ;
//    }

//    private void makeDataPlay() {
//        Random r = new Random();
//        int intPost = r.nextInt((MAX_DATA_PLAY - MIN_DATA_PLAY) + DATA_ONE) + MIN_DATA_PLAY;
//        dataSave = keys[intPost];
//
//    }
//
//    public String getDataKey() {
//        return dataSave;
//    }
//
//    public void write() {
//        try {
//            File searchFile = new File("");
//            searchFile.getAbsoluteFile().getParentFile().getAbsolutePath();
//            String path = searchFile.getAbsoluteFile().getParentFile().getAbsolutePath() + PATH_PLAY_DATA;
//
//            FileWriter fileWriter = new FileWriter(path);
//            PrintWriter printWriter = new PrintWriter(fileWriter);
//            printWriter.println(getDataKey());
//            printWriter.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

//    public static void main(String[] args) throws java.io.IOException {
//
//        File searchFile = new File("");
//        searchFile.getAbsoluteFile().getParentFile().getAbsolutePath();
//        String path = searchFile.getAbsoluteFile().getParentFile().getAbsolutePath() + PATH_KEY;
//
//        MyFileReader My = new MyFileReader();
//        My.readConjuro(path);
//        My.write();
//    }

}