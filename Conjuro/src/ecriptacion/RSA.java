/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecriptacion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSA {

	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private String privateKey;
	private String publicKey;

	public RSA(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
                System.out.println(this.privateKey);
		this.publicKey = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());
	}

	public String getPrivateKey() {
		return this.privateKey;
	}

	public String getPublicKey() {
		return this.publicKey;
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public static void main(String[] args) {
		RSA gk;
		try {
			gk = new RSA(1024);
			gk.createKeys();
			gk.writeToFile("KeyPair/publicKey", Base64.getDecoder().decode(gk.getPublicKey()));
			gk.writeToFile("KeyPair/privateKey", Base64.getDecoder().decode(gk.getPrivateKey()));
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.err.println(e.getMessage());
		} catch (IOException ex) { 
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            } 

	}
    /*
        import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSA {

	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private String publicKey;

	public RSA(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
	}

	public void createKeys() throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.pair = this.keyGen.generateKeyPair();
                this.privateKey = pair.getPrivate();
                byte[] s = this.privateKey.getEncoded();
                String l = Base64.getEncoder().encodeToString(s);
                s = Base64.getDecoder().decode(l);
                PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(s);
		KeyFactory kf = KeyFactory.getInstance("RSA");
                System.out.println(this.privateKey);
                PrivateKey b = kf.generatePrivate(spec);
                System.out.println(b);
		this.publicKey = new String(pair.getPublic().getEncoded());
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public String getPublicKey() {
		return this.publicKey;
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

	public static void main(String[] args) {
		RSA gk;
		try {
			gk = new RSA(1024);
			gk.createKeys();
			//gk.writeToFile("KeyPair/publicKey", gk.getPublicKey().getBytes());
			//gk.writeToFile("KeyPair/privateKey", gk.getPrivateKey().getBytes());
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.err.println(e.getMessage());
		} catch (InvalidKeySpecException ex) { 
                Logger.getLogger(RSA.class.getName()).log(Level.SEVERE, null, ex);
            } 

	}
        */
}
