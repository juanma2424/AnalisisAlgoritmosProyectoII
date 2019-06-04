/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *https://www.sanfoundry.com/java-program-implement-rsa-algorithm/
 */
package ecriptacion;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSA implements IAlgorithm {
    public byte[] encryptedAUX ;
    public String auxdata; 
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 1024;
    private Random r;

    public RSA() {
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public RSA(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
          RSA rsa = new RSA();
          rsa.encrypt("hola", "0");
          rsa.decrypt(rsa.getAux(), "a");
          
//        DataInputStream in = new DataInputStream(System.in);
//        String teststring;
//        System.out.println("Enter the plain text:");
//        teststring = in.readLine();
//        System.out.println("Encrypting String: " + teststring);
//        System.out.println("String in Bytes: "
//                + bytesToString(teststring.getBytes()));
//        // encrypt
//        byte[] encrypted = rsa.encryptRSA(teststring.getBytes());
//        // decrypt
//        byte[] decrypted = rsa.decryptRSA(encrypted);
//        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
//        System.out.println("Decrypted String: " + new String(decrypted




    }

    public String getAux(){
        return this.auxdata;
    }
    private static String bytesToString(byte[] encrypted) {
        String test = "";
        for (byte b : encrypted) {
            test += Byte.toString(b);
        }
        return test;
    }

    // Encrypt message
    public byte[] encryptRSA(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    // Decrypt message
    public byte[] decryptRSA(byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }

    public String encrypt(String pdata, String pkey) {
        System.out.println("msg : "+ pdata);
        bytesToString(pdata.getBytes());
        byte[] encrypted = encryptRSA(pdata.getBytes());
        encryptedAUX =  encrypted ; 
        System.out.println("en " + encrypted); 
        auxdata = bytesToString(pdata.getBytes());
        System.out.println("String in Bytes: "+ bytesToString(pdata.getBytes()));
        return  auxdata;
    }

    public String decrypt(String pdata, String pkey) {
        //System.out.println("pdata " + pdata);
        try{ 
            byte[] b = pdata.getBytes();
            System.out.println("////" + b);
        } catch (Exception e) {
            System.out.println(e);
        }
       
        
        byte[] encrypted = encryptedAUX ;
        byte[] decrypted = decryptRSA(encrypted);
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
        return decrypted.toString();
    }

}