#include "GenerateBook.h"
#include "AES.h"
#include <iostream>
#include <cassert>
#include <cstring>

using namespace std;






void GenerateBook::makeBook()
{
	string conjuro;
	 AES aes(256);
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
	    	book[0][i] = aes.encriptAES((unsigned char*)conjuro.c_str()); 
	    	cout<<book[0][i];
			cout<<"\n-------------------------------------------------------"<<endl; 
			cout<<"\n";
	    	cout<<"\n";	
			cout<<"----------------------KEY-AES-----------------------------"<<endl;
	    //	book[0][i] = aes.encriptAES((unsigned char*)conjuro.c_str()); 
	    //	cout<<aes.getKey();
			cout<<"\n-------------------------------------------------------"<<endl; 
			cout<<"\n";
	    	cout<<"\n";	    	 
	    }	
	}
}
	
