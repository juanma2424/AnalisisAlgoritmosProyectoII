#ifndef AES_H
#define AES_H

#include<cstring>
#include <iostream>
#include <string>
#include <cstdlib> 
#include <ctime> 
#include <iostream>
#include <stdlib.h> 
#include <iomanip>
#include <sstream>

//256 AES

using namespace std;

class AES
{
private:
  int Nb;
  int Nk;
  int Nr;
  

 
  
  unsigned int blockBytesLen;

  void SubBytes(unsigned char **state);

  void ShiftRow(unsigned char **state, int i, int n);    // shift row i on n positions

  void ShiftRows(unsigned char **state);

  unsigned char xtime(unsigned char b);    // multiply on x

  unsigned char mul_bytes(unsigned char a, unsigned char b);

  void MixColumns(unsigned char **state);

  void AddRoundKey(unsigned char **state, unsigned char *key);

  void SubWord(unsigned char *a);

  void RotWord(unsigned char *a);

  void XorWords(unsigned char *a, unsigned char *b, unsigned char *c);

  void Rcon(unsigned char * a, int n);

  void InvSubBytes(unsigned char **state);

  void InvMixColumns(unsigned char **state);

  void InvShiftRows(unsigned char **state);

  unsigned char* PaddingNulls(unsigned char in[], unsigned int inLen, unsigned int alignLen);
  
  unsigned int GetPaddingLength(unsigned int len);

  void KeyExpansion(unsigned char key[], unsigned char w[]);

  void EncryptBlock(unsigned char in[], unsigned char out[], unsigned  char key[]);

  void DecryptBlock(unsigned char in[], unsigned char out[], unsigned  char key[]);

  void XorBlocks(unsigned char *a, unsigned char * b, unsigned char *c, unsigned int len);

public:
  AES(int keyLen);
  
  string encriptAES(unsigned char po[]);
  
  string decryptAes(unsigned char in[], string pKey);
  
  string getKey();

  unsigned char *EncryptECB(unsigned char in[], unsigned int inLen, unsigned  char key[], unsigned int &outLen);

  unsigned char *DecryptECB(unsigned char in[], unsigned int inLen, unsigned  char key[], unsigned int &outLen);

  unsigned char *newKey(); 
  

};


const unsigned char sbox[16][16] = {
    0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5,
    0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
    0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0,
    0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
    0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc,
    0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
    0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a,
    0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
    0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0,
    0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
    0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b,
    0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
    0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85,
    0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
    0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5,
    0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
    0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17,
    0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
    0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88,
    0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
    0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c,
    0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
    0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9,
    0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
    0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6,
    0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
    0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e,
    0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
    0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94,
    0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
    0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68,
    0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
  };

const unsigned char inv_sbox[16][16] = {
    0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38,
    0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
    0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87,
    0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
    0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d,
    0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
    0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2,
    0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
    0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16,
    0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
    0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda,
    0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
    0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a,
    0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
    0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02,
    0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
    0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea,
    0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
    0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85,
    0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
    0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89,
    0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
    0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20,
    0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
    0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31,
    0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
    0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d,
    0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
    0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0,
    0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
    0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26,
    0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d, };
    
    using namespace std;
string auxkey;
AES::AES(int keyLen = 256)
{
  this->Nb = 4;
  switch (keyLen)
  {
  case 128:
    this->Nk = 4;
    this->Nr = 10;
    break;
  case 192:
    this->Nk = 6;
    this->Nr = 12;
    break;
  case 256:
    this->Nk = 8;
    this->Nr = 14;
    break;
  default:
    throw "Incorrect key length";
  }
  blockBytesLen = 4 * this->Nb * sizeof(unsigned char);
}

 string AES::getKey(){
 	return auxkey;
 }




unsigned char* AES::newKey(){

	 time_t now = time(0);
     tm *ltm = localtime(&now);
	int numkeys[32];
	auxkey = "";
	for(int indexTime= 0; indexTime<32;indexTime++){
		int min = 1 + ltm->tm_min;
	    int sec = 1 + ltm->tm_sec; 
		int numKey = (rand()%1000)+1+sec+min;
		numkeys[indexTime] = numKey ;
		
		string out_string;
		stringstream ss;
		ss << numKey;
		out_string = ss.str();
		if(indexTime<=30){
				auxkey =  auxkey + out_string +".";	
		}else{
				auxkey =  auxkey + out_string ;	
		}
		
		
		}
		 unsigned char outkey [32] = {numkeys[0],numkeys[1],numkeys[2],numkeys[3],numkeys[4],numkeys[5],numkeys[6],numkeys[7],
									  numkeys[8],numkeys[9],numkeys[10],numkeys[11],numkeys[12],numkeys[13],numkeys[14],numkeys[15], 
									  numkeys[16],numkeys[17],numkeys[18],numkeys[19],numkeys[20],numkeys[21],numkeys[22],numkeys[23],
									 numkeys[24],numkeys[25],numkeys[26],numkeys[27],numkeys[28],numkeys[29],numkeys[30],numkeys[31]
									 };
	    return  outkey;
}


string AES::encriptAES(unsigned char po[])
{
  AES aes(256);
  int lenChar = 208 * sizeof(unsigned char);
  unsigned char* keydata = aes.newKey(); 
  unsigned char key[] = {keydata[0],keydata[1],keydata[2],keydata[3],keydata[4],keydata[5],keydata[6],keydata[7],
                         keydata[8],keydata[9],keydata[10],keydata[11],keydata[12],keydata[13],keydata[14],keydata[15],
						 keydata[16],keydata[17],keydata[18],keydata[19],keydata[20],keydata[21],keydata[22],keydata[23],
						 keydata[24],keydata[25],keydata[26],keydata[27],keydata[28], keydata[29],keydata[30],keydata[31]};
  unsigned int len = 0;
  unsigned char *out = aes.EncryptECB(po, lenChar, key, len);
  stringstream ss;
  for(int i =0; i<lenChar;i++){
  	ss << out[i];
  }
  string strOut(ss.str());
  delete[] out;
  return strOut;
}






unsigned char * AES::EncryptECB(unsigned char in[], unsigned int inLen, unsigned  char key[], unsigned int &outLen)
{
  outLen = GetPaddingLength(inLen);
  unsigned char *alignIn  = PaddingNulls(in, inLen, outLen);
  unsigned char *out = new unsigned char[outLen];
  for (unsigned int i = 0; i < outLen; i+= blockBytesLen)
  {
    EncryptBlock(alignIn + i, out + i, key);
  }
  
  delete[] alignIn; 
  return out;
}

string AES::decryptAes(unsigned char in[], string pKey)
{
	unsigned int len = 0;
	unsigned int lenChar = 208*sizeof(unsigned char);
	unsigned char key[32];
   std::string token;
   std::istringstream tokenStream(pKey);
   int count = 0;
   int trueValue = 0;
   while (std::getline(tokenStream, token, '.'))
   {
  
    stringstream geek(token); 
    geek >> trueValue;
    key[count] = trueValue;
    count++;
   }
   unsigned char * conjuroDecryted = DecryptECB(in, lenChar, key, len);
   stringstream ss;
  ss << conjuroDecryted;
  string ucs(ss.str());
   
   return ucs;
   
}

unsigned char * AES::DecryptECB(unsigned char in[], unsigned int inLen, unsigned  char key[], unsigned int &outLen)
{
  outLen = GetPaddingLength(inLen);
  unsigned char *alignIn  = PaddingNulls(in, inLen, outLen);
  unsigned char *out = new unsigned char[outLen];
  for (unsigned int i = 0; i < outLen; i+= blockBytesLen)
  {
    DecryptBlock(alignIn + i, out + i, key);
  }
  
  delete[] alignIn;
  return out;
}



unsigned char * AES::PaddingNulls(unsigned char in[], unsigned int inLen, unsigned int alignLen)
{
  unsigned char * alignIn = new unsigned char[alignLen];
  memcpy(alignIn, in, inLen);
  return alignIn;
}

unsigned int AES::GetPaddingLength(unsigned int len)
{
  return (len / blockBytesLen) * blockBytesLen;
}

void AES::EncryptBlock(unsigned char in[], unsigned char out[], unsigned  char key[])
{
  unsigned char *w = new unsigned char[4 * Nb * (Nr + 1)];
  KeyExpansion(key, w);
  unsigned char **state = new unsigned char *[4];
  state[0] = new unsigned  char[4 * Nb];
  int i, j, round;
  
  for (i = 0; i < 4; i++)
  {
    state[i] = state[0] + Nb * i;
  }


  for (i = 0; i < 4; i++)
  {
    for (j = 0; j < Nb; j++)
    {
      state[i][j] = in[i + 4 * j];
    }
  }

  AddRoundKey(state, w);//sumador xor

  for (round = 1; round <= Nr - 1; round++)// durante las 9 vueltas
  {
    SubBytes(state);
    ShiftRows(state);
    MixColumns(state);
    AddRoundKey(state, w + round * 4 * Nb);
  }

  // en la ronda 10
  SubBytes(state);
  ShiftRows(state);
  AddRoundKey(state, w + Nr * 4 * Nb);

 // extraccion del criptograma
  for (i = 0; i < 4; i++)
  {
    for (j = 0; j < Nb; j++)
    {
      out[i + 4 * j] = state[i][j];
    }
  }

  delete[] state[0];
  delete[] state;
  delete[] w;
}

void AES::DecryptBlock(unsigned char in[], unsigned char out[], unsigned  char key[])
{
  unsigned char *w = new unsigned char[4 * Nb * (Nr + 1)];
  KeyExpansion(key, w);
  unsigned char **state = new unsigned char *[4];
  state[0] = new unsigned  char[4 * Nb];
  int i, j, round;
  for (i = 0; i < 4; i++)
  {
    state[i] = state[0] + Nb * i;
  }


  for (i = 0; i < 4; i++)
  {
    for (j = 0; j < Nb; j++) {
      state[i][j] = in[i + 4 * j];
    }
  }

  AddRoundKey(state, w + Nr * 4 * Nb);

  for (round = Nr - 1; round >= 1; round--)
  {
    InvSubBytes(state);
    InvShiftRows(state);
    AddRoundKey(state, w + round * 4 * Nb);
    InvMixColumns(state);
  }

  InvSubBytes(state);
  InvShiftRows(state);
  AddRoundKey(state, w);

  //extracion del msg
  for (i = 0; i < 4; i++)
  {
    for (j = 0; j < Nb; j++) {
      out[i + 4 * j] = state[i][j];
    }
  }

  delete[] state[0];
  delete[] state;
  delete[] w;
}


//sustitucion en l matrix de estado mediante la tabla sbox
void AES::SubBytes(unsigned char **state)
{
  int i, j;
  unsigned char t;
  for (i = 0; i < 4; i++)
  {
    for (j = 0; j < Nb; j++)
    {
      t = state[i][j];
      state[i][j] = sbox[t / 16][t % 16];
    }
  }

}

// rotacion de las filas determinadas limitadas a su valor de bits 
void AES::ShiftRow(unsigned char **state, int i, int n)    // shift row i on n positions
{
  unsigned char t;
  int k, j;
  for (k = 0; k < n; k++)
  {
    t = state[i][0];
    for (j = 0; j < Nb - 1; j++)
    {
      state[i][j] = state[i][j + 1];
    }
    state[i][Nb - 1] = t;
  }
}

// llama a las rotaciones en las filas 
void AES::ShiftRows(unsigned char **state)
{
  ShiftRow(state, 1, 1);
  ShiftRow(state, 2, 2);
  ShiftRow(state, 3, 3);
}


unsigned char AES::xtime(unsigned char b)    // multiply on x
{
  unsigned char mask = 0x80, m = 0x1b;
  unsigned char high_bit = b & mask;
  b = b << 1;
  if (high_bit) {    // mod m(x)
    b = b ^ m;
  }
  return b;
}

unsigned char AES::mul_bytes(unsigned char a, unsigned char b)
{
  unsigned char c = 0, mask = 1, bit, d;
  int i, j;
  for (i = 0; i < 8; i++)
  {
    bit = b & mask;
    if (bit)
    {
      d = a;
      for (j = 0; j < i; j++)
      {    // multiply on x^i
        d = xtime(d);
      }
      c = c ^ d;    // xor to result
    }
    b = b >> 1;
  }
  return c;
}

// multiplica cada una de la matrix de estado por un polinomio 
void AES::MixColumns(unsigned char **state)
{
  unsigned char s[4], s1[4];
  int i, j;

  for (j = 0; j < Nb; j++)
  {
    for (i = 0; i < 4; i++)
    {
      s[i] = state[i][j];
    }

    s1[0] = mul_bytes(0x02, s[0]) ^ mul_bytes(0x03, s[1]) ^ s[2] ^ s[3];
    s1[1] = s[0] ^ mul_bytes(0x02, s[1]) ^ mul_bytes(0x03, s[2]) ^ s[3];
    s1[2] = s[0] ^ s[1] ^ mul_bytes(0x02, s[2]) ^ mul_bytes(0x03, s[3]);
    s1[3] = mul_bytes(0x03, s[0]) ^ s[1] ^ s[2] ^ mul_bytes(0x02, s[3]);
    for (i = 0; i < 4; i++)
    {
      state[i][j] = s1[i];
    }

  }

}

// sumador xor de la matriz de estado y la clave de cada vuelta 
void AES::AddRoundKey(unsigned char **state, unsigned char *key)
{
  int i, j;
  for (i = 0; i < 4; i++)
  {
    for (j = 0; j < Nb; j++)
    {
      state[i][j] = state[i][j] ^ key[i + 4 * j];
    }
  }
}


// subbyte aplicado a A
void AES::SubWord(unsigned char *a)
{
  int i;
  for (i = 0; i < 4; i++)
  {
    a[i] = sbox[a[i] / 16][a[i] % 16];
  }
}

// rota el ultimo byte de a
void AES::RotWord(unsigned char *a)
{
  unsigned char c = a[0];
  a[0] = a[1];
  a[1] = a[2];
  a[2] = a[3];
  a[3] = c;
}
// xor con la columna dada y la columna 3 posiciones atras ademas del xor 
void AES::XorWords(unsigned char *a, unsigned char *b, unsigned char *c)
{
  int i;
  for (i = 0; i < 4; i++)
  {
    c[i] = a[i] ^ b[i];
  }
}

//calculo del vector de rcon
void AES::Rcon(unsigned char * a, int n)
{
  int i;
  unsigned char c = 1;
  for (i = 0; i < n - 1; i++)
  {
    c = xtime(c);
  }

  a[0] = c;
  a[1] = a[2] = a[3] = 0;
}

// rotword subbyts xor 1-3 xor rcon
// genera las 10 subclaes de cada vuelta
void AES::KeyExpansion(unsigned char key[], unsigned char w[])
{
  unsigned char *temp = new unsigned char[4];
  unsigned char *rcon = new unsigned char[4];

  int i = 0;
  while (i < 4 * Nk)
  {
    w[i] = key[i];
    i++;
  }

  i = 4 * Nk;
  while (i < 4 * Nb * (Nr + 1))
  {
    temp[0] = w[i - 4 + 0];
    temp[1] = w[i - 4 + 1];
    temp[2] = w[i - 4 + 2];
    temp[3] = w[i - 4 + 3];

    if (i / 4 % Nk == 0)
    {
        RotWord(temp);
        SubWord(temp);
        Rcon(rcon, i / (Nk * 4));
      XorWords(temp, rcon, temp);
    }
    else if (Nk > 6 && i / 4 % Nk == 4)
    {
      SubWord(temp);
    }

    w[i + 0] = w[i - 4 * Nk] ^ temp[0];
    w[i + 1] = w[i + 1 - 4 * Nk] ^ temp[1];
    w[i + 2] = w[i + 2 - 4 * Nk] ^ temp[2];
    w[i + 3] = w[i + 3 - 4 * Nk] ^ temp[3];
    i += 4;
  }

  delete []rcon;
  delete []temp;

}


void AES::InvSubBytes(unsigned char **state)
{
  int i, j;
  unsigned char t;
  for (i = 0; i < 4; i++)
  {
    for (j = 0; j < Nb; j++)
    {
      t = state[i][j];
      state[i][j] = inv_sbox[t / 16][t % 16];
    }
  }
}

void AES::InvMixColumns(unsigned char **state)
{
  unsigned char s[4], s1[4];
  int i, j;

  for (j = 0; j < Nb; j++)
  {
    for (i = 0; i < 4; i++)
    {
      s[i] = state[i][j];
    }
    s1[0] = mul_bytes(0x0e, s[0]) ^ mul_bytes(0x0b, s[1]) ^ mul_bytes(0x0d, s[2]) ^ mul_bytes(0x09, s[3]);
    s1[1] = mul_bytes(0x09, s[0]) ^ mul_bytes(0x0e, s[1]) ^ mul_bytes(0x0b, s[2]) ^ mul_bytes(0x0d, s[3]);
    s1[2] = mul_bytes(0x0d, s[0]) ^ mul_bytes(0x09, s[1]) ^ mul_bytes(0x0e, s[2]) ^ mul_bytes(0x0b, s[3]);
    s1[3] = mul_bytes(0x0b, s[0]) ^ mul_bytes(0x0d, s[1]) ^ mul_bytes(0x09, s[2]) ^ mul_bytes(0x0e, s[3]);

    for (i = 0; i < 4; i++)
    {
      state[i][j] = s1[i];
    }
  }
}

void AES::InvShiftRows(unsigned char **state)
{
  ShiftRow(state, 1, Nb - 1);
  ShiftRow(state, 2, Nb - 2);
  ShiftRow(state, 3, Nb - 3);
}

void AES::XorBlocks(unsigned char *a, unsigned char * b, unsigned char *c, unsigned int len)
{
  for (unsigned int i = 0; i < len; i++)
  {
    c[i] = a[i] ^ b[i];
  }
}

#endif