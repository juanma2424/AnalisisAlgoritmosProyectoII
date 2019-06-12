package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import lib.Logger;

public class FileManagement {

    public void readFile(String pPath) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String text = "";
        try {
            archivo = new File(pPath);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                text += linea;
            }
            fr.close();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }
    }
    
    public void writeFile(String pText, String pPath){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(pPath);
            pw = new PrintWriter(fichero);

            pw.println(pText);
            
            fichero.close();
        } catch (Exception ex) {
            Logger.Log(ex.getMessage());
        }   
    }
}