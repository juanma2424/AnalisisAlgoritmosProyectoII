#ifndef ENCRYPT_H
#define ENCRYPT_H
#include <iostream>
#include <cstring>
#include <fstream>
#include <sstream>
#include "Structure.h"
using namespace std;
class Encrypt
{
	public:
		Structure stke;
		Encrypt(){stke = Structure();}
		void AddRoundKey(unsigned char *state, unsigned char *roundKey);
		void SubByte(unsigned char *state);
		void ShiftRow(unsigned char *state);
		void MixColumns(unsigned char *state);
		void Round(unsigned char *state, unsigned char *key);
		void FinalRound(unsigned char *state, unsigned char *key);
		void AESEncrypt(unsigned char *message, unsigned char *expandedKey, unsigned char *encryptedMessage);
		string encryp(string msg);
		
	protected:
};

#endif
