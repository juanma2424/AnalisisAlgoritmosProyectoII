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
        string padre = "";
        for(int i = 0; i < 3; i++){     
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	string conjEncrypted = aes.encriptAES((unsigned char*)conjuro.c_str());
//	    	file.write(conjEncrypted,"Conjuro");
//	    	file.write("Y-Y-Y","Conjuro");
//	     	file.write(sha256(conjuro),"Conjuro");
//	     	file.write("Z-Z-Z","Conjuro");
//	    	file.write(aes.getKey(),"save"); 
			padre+=conjEncrypted;
			padre+="Y-Y-Y";
			padre+=sha256(conjuro);
			padre+="Z-Z-Z";
			//file.m(conjEncrypted);
	    	//file.m("Y-Y-Y");
	     	//file.m(sha256(conjuro));
	     	//file.m("Z-Z-Z");
	    	file.write(aes.getKey(),"save"); 
	    //	cout<<"--------Llave-----------"<<endl;
	    //	cout<<aes.getKey()<<endl;
	    	cout<<"--------Texto encriptado-----------"<<endl;
	    	cout<<conjEncrypted<<endl;
	    	cout<<"--------Fin-----------"<<endl;
			cout<<aes.decryptAes((unsigned char*)conjEncrypted.c_str(),aes.getKey())<<endl;	 
	    }
//		file.write("X-X-X","save"); 
//		file.write("X-X-X","Conjuro");  
//		file.write("X-X-X","Conjuro"); 
		padre+= "X-X-X";
		padre+= "X-X-X";
		cout<<"---------------padre------------------" <<endl;
		cout<<padre<<endl;
		cout<<"---------------padre Fin------------------" <<endl;
		file.m(padre);
		file.write("X-X-X","save"); 
		file.m("X-X-X");  
		file.m("X-X-X"); 	
	    file.closeWrite();
	    cout<<"--------Finallllllllllllllllllllllllllllllll-----------"<<endl;
	    searchConjuro("Nose");
	    
	}
}
void GenerateBook::searchConjuro(string pKey){
	FileManagement file = FileManagement();
	file.b();	
	int start = 0;
	int fin = 0;
	//fin = book.find("Y-Y-Y");
//	cout<<book<<endl;
}
