#ifndef ENCRYPT_H
#define ENCRYPT_H

class Encrypt
{
	public:
		Encrypt(){}
		void AddRoundKey(unsigned char *state, unsigned char *roundKey);
		void SubByte(unsigned char *state);
		void ShiftRow(unsigned char *state);
		void MixColumns(unsigned char *state);
		void Rounds(unsigned char *state, unsigned char *key);
		void FinalRound(unsigned char *state, unsigned char *key);
		void AESEncrypt(unsigned char *message, unsigned char *expandedKey, unsigned char *encryptedMessage);
		void encryp(std::string msg);
		
	protected:
};

#endif
