#ifndef DECRYPT_H
#define DECRYPT_H
#include "Structure.h"

using namespace std;

class Decrypt
{
	public:
		Decrypt(){stu = Structure();}
		void SubRoundKey(unsigned char *state, unsigned char *roundKey);
		void InverseMixColumns(unsigned char *state);
		void ShiftRows(unsigned char *state);
		void SubBytes(unsigned char *state);
		void Round(unsigned char *state, unsigned char *key);
		void InitialRound(unsigned char *state, unsigned char *key);
		void AESDecrypt(unsigned char *encryptedMessage, unsigned char *expandedKey, unsigned char *decryptedMessage);
		void decryptData(string);
		Structure stu;
	protected:
};

#endif
