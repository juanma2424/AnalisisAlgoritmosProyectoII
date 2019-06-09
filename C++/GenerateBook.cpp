#include "GenerateBook.h"
#include "AES.h"
#include <iostream>
#include <cassert>
#include <cstring>

using namespace std;

void TestECB(unsigned char po[])
{
  AES aes(256);


  unsigned char* keydata = aes.newKey(); 
  unsigned char key[] = {keydata[0],keydata[1],keydata[2],keydata[3],keydata[4],keydata[5],keydata[6],keydata[7],
                         keydata[8],keydata[9],keydata[10],keydata[11],keydata[12],keydata[13],keydata[14],keydata[15],
						 keydata[16],keydata[17],keydata[18],keydata[19],keydata[20],keydata[21],keydata[22],keydata[23],
						 keydata[24],keydata[25],keydata[26],keydata[27],keydata[28], keydata[29],keydata[30]};
  unsigned int len = 0;
  unsigned char *out = aes.EncryptECB(po, 208 * sizeof(unsigned char), key, len);
  unsigned char *innew = aes.DecryptECB(out, 208 * sizeof(unsigned char), key, len);
  //cout << innew << endl;
  delete[] out;
  delete[] innew;
}





	

void GenerateBook::makeBook()
{


   
	
	#pragma omp parallel
    {
        #pragma omp for 
        for(int i = 0; i < 100; i++){
        	
        	string to = "hola";
			TestECB((unsigned char*)to.c_str());  	 
	    	break;
	    	 //	book[1][i] = sha256(conjuro);
	    	//book[0][i] = Aes(conjuro);
	    }	
	}
}
