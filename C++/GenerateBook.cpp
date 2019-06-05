#include "GenerateBook.h"

#include "Decrypt.h"
	
 Decrypt a = Dencrypt();	

void GenerateBook::makeBook()
{

	string conjuro;
   
//	Encrypt encpt = Encrypt();
   
	
	#pragma omp parallel
    {
        #pragma omp for 
        for(int i = 0; i < 100; i++){
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	
	    //	book[1][i] = sha256(conjuro);
	    //	 encpt.encryp(conjuro);
	    	// a.decryptData();
	    	
	    	 
	    	 break;
	    	//book[0][i] = Aes(conjuro);
	    }	
	}
}
