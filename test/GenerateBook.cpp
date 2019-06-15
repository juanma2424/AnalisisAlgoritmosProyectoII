#include "GenerateBook.h"
#include "AES.h"
#include <iostream>
#include <cassert>
#include <cstring>
#include "FileManagement.h"

using namespace std;


void GenerateBook::makeBook()
{
	string conjuro;
	FileManagement file = FileManagement();
	 AES aes(256);
	#pragma omp parallel
    {
        #pragma omp for
		file.CleanFiles(); 
        file.openWrite();
        for(int i = 0; i < 1; i++){     
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	
	    	string conjuroEncryted = aes.encriptAES((unsigned char*)conjuro.c_str());
	    	file.write(conjuroEncryted,"conjuro");
	    	file.write("X--X","conjuro");
	    	unsigned char *k = aes.DecryptECB((unsigned char*)conjuroEncryted.c_str(),(unsigned char*)aes.getKey().c_str());
	    	string sName(reinterpret_cast<char*>(k));
	    	cout<<k <<endl;
	    	ofstream conjuro3;
  conjuro3.open("ConjuroDec2.txt");
  
  conjuro3 << sName + "\n";
  conjuro3.close();
	     	file.write(sha256(conjuroEncryted),"conjuro");
	     	file.write("X-Y-X","conjuro");
	    	file.write(aes.getKey(),"save"); 	 
	    }
		file.write("X-X-X","save"); 
		file.write("X-X-X","conjuro");  
		file.write("X-X-X","conjuro");   	
	    file.closeWrite();
	}
}
	
