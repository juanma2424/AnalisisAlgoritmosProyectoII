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
	    	//cout<<"----------------------sha256-----------------------------"<<endl;
	    //	book[0][i] = aes.encriptAES((unsigned char*)conjuro.c_str());
	    	file.write(aes.encriptAES((unsigned char*)conjuro.c_str()),"conjuro");
	    	file.write("X--X","conjuro");
	    	
	    	//cout<<book[1][i];
	    	//cout<<"\n-------------------------------------------------------"<<endl;
	    	//cout<<"\n";
	    	//cout<<"\n";
	    	//cout<<"----------------------AES-----------------------------"<<endl;
	     //	book[1][i] = sha256(conjuro);
	     	file.write(sha256(conjuro),"conjuro");
	     	file.write("X---X","conjuro");
	    	//cout<<book[0][i];
			//cout<<"\n-------------------------------------------------------"<<endl; 
			//cout<<"\n";
	    	//cout<<"\n";	
			//cout<<"----------------------KEY-AES-----------------------------"<<endl;
	    //	book[2][i] = aes.getKey();
	    	file.write(aes.getKey(),"save");
	    	//cout<<aes.getKey();
			//cout<<"\n-------------------------------------------------------"<<endl; 
			//cout<<"\n";
	    	//cout<<"\n";	    	 
	    }	
	    file.closeWrite();
	}
}
	
