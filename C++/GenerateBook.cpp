#include "GenerateBook.h"
void GenerateBook::makeBook()
{
	string conjuro;
	Decrypt a = Decrypt();
	Encrypt encpt = Encrypt();
	#pragma omp parallel
    {
        #pragma omp for 
        for(int i = 0; i < 100; i++){
        	cout<<"----------------------"<<i<<"-----------------------------"<<endl;
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	book[1][i] = sha256(conjuro);
	    	string data = encpt.encryp("Mr and Mrs Dursley of number four Privet Drive were proud to say that they were perfectly normal thank you very much They were the last people youd expect to be involved in anyt");
	    	a.decryptData(data);
			cout<<"a"<<endl;
			break;	    	 
	    }	
	}
}
