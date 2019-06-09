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
	    	file.write(aes.encriptAES((unsigned char*)conjuro.c_str()),"conjuro");
	    	file.write("X--X","conjuro");
	     	file.write(sha256(conjuro),"conjuro");
	     	file.write("X---X","conjuro");
	    	file.write(aes.getKey(),"save");    	 
	    }	
	    file.closeWrite();
	}
}
	
