#include <iostream>
#include <cstring>
#include <fstream>
#include <sstream>
#include "Structure.h"
//
using namespace std;

/* Se usa en Round () y sirve como la ronda final durante el descifrado
 * SubRoundKey es simplemente un XOR de un bloque de 128 bits con la clave de 128 bits.
 * Así que básicamente hace lo mismo que AddRoundKey en el cifrado
 */
Structure stu = Structure();



void SubRoundKey(unsigned char *state, unsigned char *roundKey)
{
	for (int i = 0; i < 16; i++)
	{
		state[i] ^= roundKey[i];
	}
}

/* InverseMixColumns usa las tablas de consulta mul9, mul11, mul13, mul14
 * Desmezcla las columnas invirtiendo el efecto de MixColumns en el cifrado
 * | 14 | 11 | 13 | 9  |
 * | 9  | 14 | 11 | 13 |
 * | 13 | 9  | 14 | 11 |
 * | 11 | 13 | 9  | 14 |
 //////////////////////////////////
 * | 2 | 3 | 1 | 1 |
 * | 1 | 2 | 3 | 1 |
 * | 1 | 1 | 2 | 3 |
 * | 3 | 1 | 1 | 2 |
 */
void InverseMixColumns(unsigned char *state)
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

// Desplaza las filas a la derecha (en lugar de a la izquierda) para el descifrado
void ShiftRows(unsigned char *state)
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

/* Realizar sustitución a cada uno de los 16 bytes.
 * Utiliza caja S inversa como tabla de búsqueda
 */
void SubBytes(unsigned char *state)
{
	for (int i = 0; i < 16; i++)
	{ // Realizar sustitución a cada uno de los 16 bytes
		state[i] = stu.inv_s[state[i]];
	}
}

/* Cada ronda opera en 128 bits a la vez
 * El número de rondas se define en AESDecrypt ()
// * No en vano, los pasos son los pasos de encriptación pero invertidos.
// */
void Round(unsigned char *state, unsigned char *key)
{
	SubRoundKey(state, key);
	InverseMixColumns(state);
	ShiftRows(state);
	SubBytes(state);
}

// Igual que Round () pero no InverseMixColumns
void InitialRound(unsigned char *state, unsigned char *key)
{
	SubRoundKey(state, key);
	ShiftRows(state);
	SubBytes(state);
}

/* La función de descifrado AES
 * Organiza todos los pasos de descifrado en una función
 */
void AESDecrypt(unsigned char *encryptedMessage, unsigned char *expandedKey, unsigned char *decryptedMessage)
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
		decryptedMessage[i] = state[i];
	}
}

void decrypt()
{

	// cout << "=============================" << endl;
	// cout << " 128-bit AES Decryption Tool " << endl;
	// cout << "=============================" << endl;

	// Read in the message from message.aes
	string msgstr ="";
	ifstream infile;
	infile.open("message.aes", ios::in | ios::binary);

	if (infile.is_open())
	{
		getline(infile, msgstr); // The first line of file is the message
		cout << "Read in encrypted message from message.aes" << endl;
		infile.close();
	}

	else
		cout << "Unable to open file";

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
	string keystr;
	ifstream keyfile;
	keyfile.open("keyfile", ios::in | ios::binary);

	if (keyfile.is_open())
	{
		getline(keyfile, keystr); // The first line of file should be the key
		cout << "Read in the 128-bit key from keyfile" << endl;
		keyfile.close();
	}

	else
		cout << "Unable to open file";

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

	unsigned char *decryptedMessage = new unsigned char[messageLen];

	for (int i = 0; i < messageLen; i += 16)
	{
		AESDecrypt(encryptedMessage + i, expandedKey, decryptedMessage + i);
	}

	cout << "Decrypted message in hex:" << endl;
	for (int i = 0; i < messageLen; i++)
	{
		cout << hex << (int)decryptedMessage[i];
		cout << " ";
	}
	cout << endl;
	cout << "Decrypted message: ";
	for (int i = 0; i < messageLen; i++)
	{
		cout << decryptedMessage[i];
	}
	cout << endl;

	
}
