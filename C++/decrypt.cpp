#include <iostream>
#include <cstring>
#include <fstream>
#include <sstream>
#include "Decrypt.h"
//

/* Se usa en Round () y sirve como la ronda final durante el descifrado
�* SubRoundKey es simplemente un XOR de un bloque de 128 bits con la clave de 128 bits.
�* As� que b�sicamente hace lo mismo que AddRoundKey en el cifrado
�*/




void Decrypt::SubRoundKey(unsigned char *state, unsigned char *roundKey)
{
	for (int i = 0; i < 16; i++)
	{
		state[i] ^= roundKey[i];
	}
}
//
///* InverseMixColumns usa las tablas de consulta mul9, mul11, mul13, mul14
//�* Desmezcla las columnas invirtiendo el efecto de MixColumns en el cifrado
// * | 14 | 11 | 13 | 9  |
// * | 9  | 14 | 11 | 13 |
// * | 13 | 9  | 14 | 11 |
// * | 11 | 13 | 9  | 14 |
// //////////////////////////////////
// * | 2 | 3 | 1 | 1 |
// * | 1 | 2 | 3 | 1 |
// * | 1 | 1 | 2 | 3 |
// * | 3 | 1 | 1 | 2 |
//�*/
void Decrypt::InverseMixColumns(unsigned char *state)
{
	unsigned char tmp[16];

	tmp[0] = (unsigned char)stu.mul14[state[0]] ^ stu.mul11[state[1]] ^ stu.mul13[state[2]] ^ stu.mul9[state[3]];
	tmp[1] = (unsigned char)stu.mul9[state[0]] ^ stu.mul14[state[1]] ^ stu.mul11[state[2]] ^ stu.mul13[state[3]];
	tmp[2] = (unsigned char)stu.mul13[state[0]] ^ stu.mul9[state[1]] ^ stu.mul14[state[2]] ^ stu.mul11[state[3]];
	tmp[3] = (unsigned char)stu.mul11[state[0]] ^ stu.mul13[state[1]] ^ stu.mul9[state[2]] ^ stu.mul14[state[3]];
//
	tmp[4] = (unsigned char)stu.mul14[state[4]] ^ stu.mul11[state[5]] ^ stu.mul13[state[6]] ^ stu.mul9[state[7]];
	tmp[5] = (unsigned char)stu.mul9[state[4]] ^ stu.mul14[state[5]] ^ stu.mul11[state[6]] ^ stu.mul13[state[7]];
	tmp[6] = (unsigned char)stu.mul13[state[4]] ^ stu.mul9[state[5]] ^ stu.mul14[state[6]] ^ stu.mul11[state[7]];
	tmp[7] = (unsigned char)stu.mul11[state[4]] ^ stu.mul13[state[5]] ^ stu.mul9[state[6]] ^ stu.mul14[state[7]];
//
	tmp[8] = (unsigned char)stu.mul14[state[8]] ^ stu.mul11[state[9]] ^ stu.mul13[state[10]] ^ stu.mul9[state[11]];
	tmp[9] = (unsigned char)stu.mul9[state[8]] ^ stu.mul14[state[9]] ^ stu.mul11[state[10]] ^ stu.mul13[state[11]];
	tmp[10] = (unsigned char)stu.mul13[state[8]] ^ stu.mul9[state[9]] ^ stu.mul14[state[10]] ^ stu.mul11[state[11]];
	tmp[11] = (unsigned char)stu.mul11[state[8]] ^ stu.mul13[state[9]] ^ stu.mul9[state[10]] ^ stu.mul14[state[11]];
//
	tmp[12] = (unsigned char)stu.mul14[state[12]] ^ stu.mul11[state[13]] ^ stu.mul13[state[14]] ^ stu.mul9[state[15]];
	tmp[13] = (unsigned char)stu.mul9[state[12]] ^ stu.mul14[state[13]] ^ stu.mul11[state[14]] ^ stu.mul13[state[15]];
	tmp[14] = (unsigned char)stu.mul13[state[12]] ^ stu.mul9[state[13]] ^ stu.mul14[state[14]] ^ stu.mul11[state[15]];
	tmp[15] = (unsigned char)stu.mul11[state[12]] ^ stu.mul13[state[13]] ^ stu.mul9[state[14]] ^ stu.mul14[state[15]];

	for (int i = 0; i < 16; i++)
	{
		state[i] = tmp[i];
	}
}

//// Desplaza las filas a la derecha (en lugar de a la izquierda) para el descifrado
void Decrypt::ShiftRows(unsigned char *state)
{
	unsigned char tmp[16];

	/* Column 1 */
	tmp[0] = state[0];
	tmp[1] = state[13];
	tmp[2] = state[10];
	tmp[3] = state[7];

	/* Column 2 */
	tmp[4] = state[4];
	tmp[5] = state[1];
	tmp[6] = state[14];
	tmp[7] = state[11];

	/* Column 3 */
	tmp[8] = state[8];
	tmp[9] = state[5];
	tmp[10] = state[2];
	tmp[11] = state[15];

	/* Column 4 */
	tmp[12] = state[12];
	tmp[13] = state[9];
	tmp[14] = state[6];
	tmp[15] = state[3];

	for (int i = 0; i < 16; i++)
	{
		state[i] = tmp[i];
	}
}

/* Realizar sustituci�n a cada uno de los 16 bytes.
�* Utiliza caja S inversa como tabla de b�squeda
�*/
void Decrypt::SubBytes(unsigned char *state)
{
	for (int i = 0; i < 16; i++)
	{ // Realizar sustituci�n a cada uno de los 16 bytes
		state[i] = stu.inv_s[state[i]];
	}
}

/* Cada ronda opera en 128 bits a la vez
�* El n�mero de rondas se define en AESDecrypt ()
//�* No en vano, los pasos son los pasos de encriptaci�n pero invertidos.
//�*/
void Decrypt::Round(unsigned char *state, unsigned char *key)
{
	SubRoundKey(state, key);
	InverseMixColumns(state);
	ShiftRows(state);
	SubBytes(state);
}

// Igual que Round () pero no InverseMixColumns
void Decrypt::InitialRound(unsigned char *state, unsigned char *key)
{
	SubRoundKey(state, key);
	ShiftRows(state);
	SubBytes(state);
}

///* La funci�n de descifrado AES
//�* Organiza todos los pasos de descifrado en una funci�n
//�*/
void Decrypt::AESDecrypt(unsigned char *encryptedMessage, unsigned char *expandedKey, unsigned char *decryptedMessage)
{
	unsigned char state[16]; // Stores the first 16 bytes of encrypted message

	for (int i = 0; i < 16; i++)
	{
		state[i] = encryptedMessage[i];
	}

	InitialRound(state, expandedKey + 160);

	int numberOfRounds = 9;

	for (int i = 8; i >= 0; i--)
	{
		Round(state, expandedKey + (16 * (i + 1)));
	}

	SubRoundKey(state, expandedKey); // Final round

	// Copy decrypted state to buffer
	for (int i = 0; i < 16; i++)
	{
		//cout<<"A es: "<<i<<endl;
		decryptedMessage[i] = state[i];
	}
}

void Decrypt::decryptData(string pData)
{
	cout<<"1"<<endl;

    string msgstr = pData;
	char *msg = new char[msgstr.size() + 1];


	strcpy(msg, msgstr.c_str());

	int n = strlen((const char *)msg);

	

	unsigned char *encryptedMessage = new unsigned char[n];
	for (int i = 0; i < n; i++)
	{
		encryptedMessage[i] = (unsigned char)msg[i];
	}

	// Free memory
	delete[] msg;

	// Read in the key
	string keystr = "00 11 22 33 44 55 66 77 88 99 aa bb cc dd ee ff";

	istringstream hex_chars_stream(keystr);
	unsigned char key[16];
	int i = 0;
	unsigned int c;
	while (hex_chars_stream >> hex >> c)
	{
		key[i] = c;
		i++;
	}

	unsigned char expandedKey[176];

	stu.KeyExpansion(key, expandedKey);

	int messageLen = strlen((const char *)encryptedMessage);
	cout<<"Largo: "<<messageLen<<endl;

	unsigned char *decryptedMessage = new unsigned char[messageLen];

	for (int i = 0; i < messageLen; i += 16)
	{
		//cout<<"I es: "<<i<<endl;
		AESDecrypt(encryptedMessage + i, expandedKey, decryptedMessage + i);
	//	break;
	}

	cout << "Decrypted message: ";
	for (int i = 0; i < messageLen; i++)
	{
		cout << decryptedMessage[i];
	}
	cout << endl;
	cout << "Fin: "<<endl;

	
}