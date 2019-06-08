
#ifndef AES_H
#define AES_H

class AES
{
	public:
		void handleErrors(void);
		int encrypt(unsigned char *plaintext, int plaintext_len, unsigned char *key,unsigned char *iv, unsigned char *ciphertext);
		int decrypt(unsigned char *ciphertext, int ciphertext_len, unsigned char *key,unsigned char *iv, unsigned char *plaintext);
	protected:
};

#endif
#ifndef AES_H
#define AES_H

class AES
{
	public:
	protected:
};

#endif
