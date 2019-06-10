/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author adri-
 */
public class ReadFile {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String pText, String pPath) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(pPath);
            pw = new PrintWriter(fichero);

            pw.println(pText);

            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readDescription() {
        String result = "";
        File miDir = new File("");
        String path = miDir.getAbsoluteFile().getAbsolutePath() + "\\Descriptions.txt";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                result += linea;
            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
