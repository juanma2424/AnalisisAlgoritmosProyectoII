/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigosEncriptacion;


import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
 
public class RSA
{
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int        bitlength = 1024;
    private Random     r;
 
    public RSA()
    {
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }
 
    public RSA(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }
 
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException
    {
        RSA rsa = new RSA();
        DataInputStream in = new DataInputStream(System.in);
        String teststring;
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: "
                + bytesToString(teststring.getBytes()));
        // encrypt
        byte[] encrypted = rsa.encrypt(teststring.getBytes());
        // decrypt
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
    }
 
    private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
 
    // Encrypt message
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
 
    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}

//import java.util.*;
//import java.math.*;
//
//class RSA {
//
//    public static void main(String args[]) {
//        Scanner sc = new Scanner(System.in);
//        int p, q, n, z, d = 0, e, i;
//        System.out.println("Enter the number to be encrypted and decrypted");
//        int msg = sc.nextInt();
//        double c;
//        BigInteger msgback;
//        System.out.println("Enter 1st prime number p");
//        p = sc.nextInt();
//        System.out.println("Enter 2nd prime number q");
//        q = sc.nextInt();
//
//        n = p * q;
//        z = (p - 1) * (q - 1);
//        System.out.println("the value of z = " + z);
//
//        for (e = 2; e < z; e++) {
//            if (gcd(e, z) == 1) // e is for public key exponent
//            {
//                break;
//            }
//        }
//        System.out.println("the value of e = " + e);
//        for (i = 0; i <= 9; i++) {
//            int x = 1 + (i * z);
//            if (x % e == 0) //d is for private key exponent
//            {
//                d = x / e;
//                break;
//            }
//        }
//        System.out.println("the value of d = " + d);
//        c = (Math.pow(msg, e)) % n;
//        System.out.println("Encrypted message is : -");
//        System.out.println(c);
//        //converting int value of n to BigInteger
//        BigInteger N = BigInteger.valueOf(n);
//        //converting float value of c to BigInteger
//        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
//        msgback = (C.pow(d)).mod(N);
//        System.out.println("Derypted message is : -");
//        System.out.println(msgback);
//
//    }
//
//    static int gcd(int e, int z) {
//        if (e == 0) {
//            return z;
//        } else {
//            return gcd(z % e, e);
//        }
//    }
//}
