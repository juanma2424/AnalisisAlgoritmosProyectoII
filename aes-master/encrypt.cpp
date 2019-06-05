//cout << "\n--------------\n";

#include <iostream>
#include <cstring>
#include <fstream>
#include <sstream>
#include "structures.h"

using namespace std;

/* Sirve como la ronda inicial durante el cifrado
 * AddRoundKey es simplemente un XOR de un bloque de 128 bits con la clave de 128 bits.
 * la subclave se combina con el estado. 
 * Para cada ronda, una subclave cada uno Por la parte inferior de la subclave
 * (estado,subclave)
 * entra un estado u una Subclave y se unen en una nueva mediante un XOR binario 
 */
void AddRoundKey(unsigned char *state, unsigned char *roundKey)
{
	for (int i = 0; i < 16; i++)
	{
		state[i] ^= roundKey[i]; //Asignación con XOR binario conmbinado uno a uno
	}
}

/* Realizar sustitución a cada uno de los 16 bytes.
 * Utiliza 'S' como tabla de búsqueda
 * En el  'S'  paso, cada byte en la matriz se actualiza usando un 8-bit  
 * caja de sustitución , la  S.
 * entra un estado y se codifica con la primer fila de la tabla S 
 */
void SubBytes(unsigned char *state)
{
	for (int i = 0; i < 16; i++)
	{
		state[i] = s[state[i]]; // matriz de stado = S[num]//primer columna
	}
}

// Desplazar a la izquierda rotacion correspondiente a las columnas
// entra un estado sale la tabla con el shif hecho
void ShiftRows(unsigned char *state)
{
	unsigned char tmp[16];

	/* Column 1 */
	tmp[0] = state[0];
	tmp[1] = state[5];
	tmp[2] = state[10];
	tmp[3] = state[15];

	/* Column 2 */
	tmp[4] = state[4];
	tmp[5] = state[9];
	tmp[6] = state[14];
	tmp[7] = state[3];

	/* Column 3 */
	tmp[8] = state[8];
	tmp[9] = state[13];
	tmp[10] = state[2];
	tmp[11] = state[7];

	/* Column 4 */
	tmp[12] = state[12];
	tmp[13] = state[1];
	tmp[14] = state[6];
	tmp[15] = state[11];

	for (int i = 0; i < 16; i++)
	{
		state[i] = tmp[i];
	}
}

/* MixColumns usa mul2, mul3 tablas de consulta
  * Fuente de difusión.
  * El   paso MixColumns también se puede utilizar como una multiplicación
  * En el MixColumns los cuatro bytes de cada estado se combinan
  * cada columna se multiplica por la matriz conocida.
  *
  * | 2 | 3 | 1 | 1 |
  * | 1 | 2 | 3 | 1 |
  * | 1 | 1 | 2 | 3 |
  * | 3 | 1 | 1 | 2 |
  * 
  */
void MixColumns(unsigned char *state)
{
	unsigned char tmp[16];

	tmp[0] = (unsigned char)mul2[state[0]] ^ mul3[state[1]] ^ state[2] ^ state[3];
	tmp[1] = (unsigned char)state[0] ^ mul2[state[1]] ^ mul3[state[2]] ^ state[3];
	tmp[2] = (unsigned char)state[0] ^ state[1] ^ mul2[state[2]] ^ mul3[state[3]];
	tmp[3] = (unsigned char)mul3[state[0]] ^ state[1] ^ state[2] ^ mul2[state[3]];

	tmp[4] = (unsigned char)mul2[state[4]] ^ mul3[state[5]] ^ state[6] ^ state[7];
	tmp[5] = (unsigned char)state[4] ^ mul2[state[5]] ^ mul3[state[6]] ^ state[7];
	tmp[6] = (unsigned char)state[4] ^ state[5] ^ mul2[state[6]] ^ mul3[state[7]];
	tmp[7] = (unsigned char)mul3[state[4]] ^ state[5] ^ state[6] ^ mul2[state[7]];

	tmp[8] = (unsigned char)mul2[state[8]] ^ mul3[state[9]] ^ state[10] ^ state[11];
	tmp[9] = (unsigned char)state[8] ^ mul2[state[9]] ^ mul3[state[10]] ^ state[11];
	tmp[10] = (unsigned char)state[8] ^ state[9] ^ mul2[state[10]] ^ mul3[state[11]];
	tmp[11] = (unsigned char)mul3[state[8]] ^ state[9] ^ state[10] ^ mul2[state[11]];

	tmp[12] = (unsigned char)mul2[state[12]] ^ mul3[state[13]] ^ state[14] ^ state[15];
	tmp[13] = (unsigned char)state[12] ^ mul2[state[13]] ^ mul3[state[14]] ^ state[15];
	tmp[14] = (unsigned char)state[12] ^ state[13] ^ mul2[state[14]] ^ mul3[state[15]];
	tmp[15] = (unsigned char)mul3[state[12]] ^ state[13] ^ state[14] ^ mul2[state[15]];

	for (int i = 0; i < 16; i++)
	{
		state[i] = tmp[i];
	}
}

/* Cada ronda opera en 128 bits a la vez
  * El número de rondas se define en AESEncrypt ()
  * sub proceso
  */
void Round(unsigned char *state, unsigned char *key)
{
	SubBytes(state);
	ShiftRows(state);
	MixColumns(state);
	AddRoundKey(state, key);
}

// Igual que Round () excepto que no mezcla columnas
// proceso final
void FinalRound(unsigned char *state, unsigned char *key)
{
	SubBytes(state);
	ShiftRows(state);
	AddRoundKey(state, key);
}

/* La función de cifrado AES
 * Organiza los pasos de confusión y difusión en una función
 *  (Mensaje a encriptar ,expandedKey ,encryptedMessage )
 *  (mensaje + i (16), arraychar[176], arraychar[largodelmns])
 */
void AESEncrypt(unsigned char *message, unsigned char *expandedKey, unsigned char *encryptedMessage)
{

	unsigned char state[16]; // Almacena los primeros 16 bytes del mensaje original
	for (int i = 0; i < 16; i++)
	{
		state[i] = message[i]; // los introduce en la matriz de estado
	}

	int numberOfRounds = 9; // numerode rondas para la encriptacion 10 empieza en 0

	AddRoundKey(state, expandedKey); // ronda inicial cifra con la tabla S

	for (int i = 0; i < numberOfRounds; i++)
	{
		// estado y sub-llave
		Round(state, expandedKey + (16 * (i + 1)));
	}

	FinalRound(state, expandedKey + 160);

	// Copiar el estado encriptado al buffer
	for (int i = 0; i < 16; i++)
	{
		encryptedMessage[i] = state[i];
	}
}

string encryp(string msg)
{

	// cout << "=============================" << endl;
	// cout << " 128-bit AES Encryption Tool   " << endl;
	// cout << "=============================" << endl;

	char message[1024];
	strcpy(message, msg.c_str());

	// cout << "Enter the message to encrypt: ";
	//cin.getline(message, sizeof(message));
	cout << message << endl;

	// Pad message to 16 bytes
	int originalLen = strlen((const char *)message);

	int paddedMessageLen = originalLen; // largo del mensaje

	if ((paddedMessageLen % 16) != 0)
	{														 // si existe un paddedMessageLen que multiblicado por un N me de 16
		paddedMessageLen = (paddedMessageLen / 16 + 1) * 16; // el nuevo valor de la variable sera el ajuste para que (paddedMessageLen % 16) == 0
	}

	unsigned char *paddedMessage = new unsigned char[paddedMessageLen]; // el puntero paddedMessage apunta a un arreglo de chars de paddedMessageLen de largo

	for (int i = 0; i < paddedMessageLen; i++) // mientras que i sea menor a ese largo
	{
		if (i >= originalLen)
		{
			paddedMessage[i] = 0; // rellena de 0 s
		}
		else
		{
			paddedMessage[i] = message[i]; // introduce los datos del mensaje
		}
	}

	unsigned char *encryptedMessage = new unsigned char[paddedMessageLen]; //el puntero encryptedMessage apunta a un arreglo de chars de paddedMessageLen de largo

	string str;
	ifstream infile;							   // flujo de entrada para operar en archivos.
	infile.open("keyfile", ios::in | ios::binary); // abre el archivo

	if (infile.is_open())
	{
		getline(infile, str); // La primera línea de archivo debe ser la clave guardad en str
		infile.close();
	}

	else
		cout << "Unable to open file";

	istringstream hex_chars_stream(str); //Clase de flujo de entrada para operar en cadenas

	unsigned char key[16]; // llave de 16 chars
	int i = 0;
	unsigned int a; //4104

	while (hex_chars_stream >> hex >> a) //  (X_X)
	{
		key[i] = a;
		i++;
	}

	unsigned char expandedKey[176]; //  (X_X) why 176?

	//h (llave ,arreglo 176)
	KeyExpansion(key, expandedKey);

	for (int i = 0; i < paddedMessageLen; i += 16) // largo del mensaje de 16 en 16
	{
		// (mensaje + i (16), arraychar[176], arraychar[largodelmns])
		AESEncrypt(paddedMessage + i, expandedKey, encryptedMessage + i);
	}

	// mensaje encriptado en hex
	//	cout << "Encrypted message in hex:" << endl;
	for (int i = 0; i < paddedMessageLen; i++)
	{
		//	cout << hex << (int)encryptedMessage[i];
		//	cout << " ";
	}

	cout << endl;

	string sName((char *)encryptedMessage);

	cout << "\n------------line--------------------\n";
	cout << sName;

	cout << "\n--------------------------------\n";
	char *data = (char *)encryptedMessage;
	int size = 200;
	std::string myString(data, size);

	cout << "\n-----------200---------------------\n";
	cout << myString;

	cout << "\n--------------------------------\n";

	// Write the encrypted string out to file "message.aes"
	ofstream outfile;
	outfile.open("message.aes", ios::out | ios::binary);
	if (outfile.is_open())
	{
		outfile << encryptedMessage;
		outfile.close();
		//	cout << "Wrote encrypted message to file message.aes" << endl;
	}

	else
		cout << "Unable to open file";

	// Free memory
	delete[] paddedMessage;
	delete[] encryptedMessage;
	return  sName;
}