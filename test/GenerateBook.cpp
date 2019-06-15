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
        for(int i = 0; i < 100; i++){     
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	string conjEncrypted = aes.encriptAES((unsigned char*)conjuro.c_str());
	    	file.write(conjEncrypted,"conjuro");
	    	file.write("X--X","conjuro");
	     	file.write(sha256(conjuro),"conjuro");
	     	file.write("X-Y-X","conjuro");
	    	file.write(aes.getKey(),"save"); 
	    	cout<<"--------Llave-----------"<<endl;
	    	cout<<aes.getKey()<<endl;
	    	cout<<"--------Texto encriptado-----------"<<endl;
	    	cout<<(unsigned char*)conjEncrypted.c_str()<<endl;
	    	cout<<"--------Fin-----------"<<endl;
			cout<<aes.decryptAes((unsigned char*)conjEncrypted.c_str(),aes.getKey())<<endl;	 
	    }
		file.write("X-X-X","save"); 
		file.write("X-X-X","conjuro");  
		file.write("X-X-X","conjuro");   	
	    file.closeWrite();
	}
}
