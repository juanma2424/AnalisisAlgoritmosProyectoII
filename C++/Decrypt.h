#ifndef DECRYPT_H
#define DECRYPT_H

class Decrypt
{
	public:
		Decrypt(){}
		void SubRoundKey(unsigned char *state, unsigned char *roundKey);
		void InverseMixColumns(unsigned char *state);
		void ShiftRows(unsigned char *state);
		void SubBytes(unsigned char *state);
		void Round(unsigned char *state, unsigned char *key);
		void InitialRound(unsigned char *state, unsigned char *key);
		void AESDecrypt(unsigned char *encryptedMessage, unsigned char *expandedKey, unsigned char *decryptedMessage);
		void decryptData(	unsigned char * );
	protected:
};

#endif
