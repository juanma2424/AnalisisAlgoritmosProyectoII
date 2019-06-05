#include "GenerateBook.h"
//#include "encrypt.cpp"

void GenerateBook::makeBook()
{
	string conjuro;
	
	#pragma omp parallel
    {
        #pragma omp for 
        for(int i = 0; i < 100; i++){
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	
	    	book[1][i] = sha256(conjuro);
	    	//book[0][i] = Aes(conjuro);
	    }	
	}
}
