#include "GenerateBook.h"
#include "AES.h"
#include <iostream>
#include <cassert>
#include <cstring>

using namespace std;

string encriptAES(unsigned char po[])
{
  AES aes(256);
  unsigned char* keydata = aes.newKey(); 
  unsigned char key[] = {keydata[0],keydata[1],keydata[2],keydata[3],keydata[4],keydata[5],keydata[6],keydata[7],
                         keydata[8],keydata[9],keydata[10],keydata[11],keydata[12],keydata[13],keydata[14],keydata[15],
						 keydata[16],keydata[17],keydata[18],keydata[19],keydata[20],keydata[21],keydata[22],keydata[23],
						 keydata[24],keydata[25],keydata[26],keydata[27],keydata[28], keydata[29],keydata[30]};
  unsigned int len = 0;
  unsigned char *out = aes.EncryptECB(po, 208 * sizeof(unsigned char), key, len);
 // unsigned char *innew = aes.DecryptECB(out, 208 * sizeof(unsigned char), key, len);
  //cout << innew << endl;
  string strOut((char*) out);
  delete[] out;
  //delete[] innew;
  return strOut;
}




void GenerateBook::makeBook()
{
	string conjuro;
	
	#pragma omp parallel
    {
        #pragma omp for 
        for(int i = 0; i < 100; i++){
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	cout<<"----------------------sha256-----------------------------"<<endl;
	    	book[1][i] = sha256(conjuro);
	    	cout<<book[1][i];
	    	cout<<"\n-------------------------------------------------------"<<endl;
	    	cout<<"\n";
	    	cout<<"\n";
	    	cout<<"----------------------AES-----------------------------"<<endl;
	    	book[0][i] = encriptAES((unsigned char*)conjuro.c_str()); 
	    	cout<<book[0][i];
			cout<<"\n-------------------------------------------------------"<<endl; 
			cout<<"\n";
	    	cout<<"\n";	    	 
	    }	
	}
}
	



