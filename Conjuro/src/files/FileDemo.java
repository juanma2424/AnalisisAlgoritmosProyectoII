/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.File;

public class FileDemo {
   public static void main(String[] args) {      
      File f = null;
      File f1 = null;
      String v;
      boolean bool = false;

         // create new file
         
          File A = new File("");
          A.getAbsoluteFile().getParentFile().getAbsolutePath();
          System.out.println(A.getAbsoluteFile().getParentFile().getAbsolutePath()+"\\C++\\Save.txt" );        
  
   }
          
 
}