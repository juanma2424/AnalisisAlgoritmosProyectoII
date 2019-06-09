/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.*;

public class MyFileReader {

    public void readConjuro(String pPathBook, String pPathkeys) throws java.io.IOException {

        String readLinebyLine;
        String readKey;
        String buildStr = "";
      
        BufferedReader bufferBook = new BufferedReader(new FileReader(pPathBook));
        BufferedReader bufferKey = new BufferedReader(new FileReader(pPathkeys));
        readLinebyLine = bufferBook.readLine();
        

        while (!("END").equals(readLinebyLine)) {

            if (("X--X").equals(readLinebyLine)) {
                System.out.println("encip " + buildStr);
                readKey =  bufferKey.readLine();
                System.out.println("");
                System.out.println("key " +readKey);
                buildStr = "";
                readLinebyLine = bufferBook.readLine();
            }
            if (("X---X").equals(readLinebyLine)) {
                System.out.println("Sha " + buildStr);
                buildStr = "";
                readLinebyLine = bufferBook.readLine();
            }
            buildStr = buildStr + readLinebyLine;//build strg
            readLinebyLine = bufferBook.readLine();// skip lines
        }

    }

   public static void main (String[] args) throws java.io.IOException
    {
// "C:\\Users\\USER\\Desktop\\Nueva carpeta\\Conjuro.txt"
        MyFileReader a = new MyFileReader();
        a.readConjuro("C:\\Users\\USER\\Desktop\\Nueva carpeta\\Conjuro.txt","C:\\Users\\USER\\Desktop\\Nueva carpeta\\Save.txt");
        
        
        
//        String s1;
//        String saux = "";
//        boolean aes = true;
// 
//        // Cargamos el buffer con el contenido del archivo
//        BufferedReader br = new BufferedReader (new FileReader ("C:\\Users\\USER\\Desktop\\Nueva carpeta\\Conjuro.txt"));
// 
//        // Leemos la primera linea
//        s1 = br.readLine();
//     
//        while(!("END").equals( s1)){
//            
//            if(("X--X").equals(s1)){
//                System.out.println("encip "+saux);
//                saux="";
//                s1 = br.readLine();
//            }
//            if(("X---X").equals(s1)){
//               System.out.println("Sha "+saux);
//               saux="";
//               s1 = br.readLine();
//            }
//            saux = saux + s1;
//            s1 = br.readLine();
//        }
//        System.out.println("ajaaa " +  saux);
// 
////        System.out.println ("La primera linea del archivo es: " + s1);
////        System.out.println ("La linea tiene " + s1.length() + " caracteres");
//// 
////        System.out.println ();
////        System.out.println ("Separando la linea en trozos tenemos las siguientes palabras:");
//// 
////        int numTokens = 0;
////        StringTokenizer st = new StringTokenizer (s1);
//// 
////        // bucle por todas las palabras
////        while (st.hasMoreTokens())
////        {
////            s2 = st.nextToken();
////            numTokens++;
////            System.out.println ("    Palabra " + numTokens + " es: " + s2);
////        }
 }

}