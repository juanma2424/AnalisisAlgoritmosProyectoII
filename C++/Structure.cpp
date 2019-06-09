#include "Structure.h"
// Auxiliary function for KeyExpansion
Structure st = Structure();

void Structure::KeyExpansionCore(unsigned char *in, unsigned char i)
{
	unsigned char t = in[0];
	in[0] = in[1];
	in[1] = in[2];
	in[2] = in[3];
	in[3] = t;

	// S-box 4 bytes
	in[0] = s[in[0]];
	in[1] = s[in[1]];
	in[2] = s[in[2]];
	in[3] = s[in[3]];

	// RCon
	in[0] ^= rcon[i];
}

/* La función principal de KeyExpansion
 * Genera claves adicionales utilizando la clave original.
 * Total de 11 claves de 128 bits generadas, incluida la original
 * Las claves se almacenan una después de la otra en claves expandidas
 *  ..........................NO SE POR QUE 176................................... 
 * 
 */
void Structure::KeyExpansion(unsigned char inputKey[16], unsigned char expandedKeys[176])
{
	// Los primeros 128 bits son la clave original.
	for (int i = 0; i < 16; i++)
	{
		expandedKeys[i] = inputKey[i];
	}

	int bytesGenerated = 16;  // Bytes que hemos generado hasta ahora
	int rconIteration = 1;	// Realiza un seguimiento del valor rcon
	unsigned char tmpCore[4]; // Temp almacenaje para core

	while (bytesGenerated < 176)
	{
	/* Leer 4 bytes para el núcleo.
    * Son los 4 bytes generados previamente.
	* Inicialmente, estos serán los últimos 4 bytes de la clave original
	*/
		for (int i = 0; i < 4; i++)
		{
			tmpCore[i] = expandedKeys[i + bytesGenerated - 4];
		}

		// Realizar el núcleo una vez por cada clave de 16 bytes
		if (bytesGenerated % 16 == 0)
		{
			KeyExpansionCore(tmpCore, rconIteration++);
		}

		for (unsigned char a = 0; a < 4; a++)
		{
			expandedKeys[bytesGenerated] = expandedKeys[bytesGenerated - 16] ^ tmpCore[a];
			bytesGenerated++;
		}
	}
}


