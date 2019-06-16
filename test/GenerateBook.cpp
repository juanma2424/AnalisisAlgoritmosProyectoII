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
		file.writeBook(padre);
		file.write("X-X-X","save"); 	
	    file.closeWrite();
	    cout<<"--------Finallllllllllllllllllllllllllllllll-----------"<<endl;
	    
	}
}
void GenerateBook::searchConjuro(string pKey){
	//AES aes(256);
	FileManagement file = FileManagement();
	string book = file.readBook();	
	/*int start = 0;
	int fin = 0;
	string auxAes = "";
	string auxSha256 = "";
	for(int index = 0; index<1;index++ ){
		fin = book.find("Y-Y-Y");
		auxAes = book.substr(start,fin);
		book = book.substr(fin+5);
		fin = book.find("Z-Z-Z");
		auxSha256 = book.substr(start,fin);
		book = book.substr(fin+5);
		cout<<"----------------------------Prueba-------------------------------"<<endl;
		//cout<<aes.decryptAes((unsigned char*)auxAes.c_str(),pKey)<<endl;
		cout<<"----------------------------Prueba Fin-------------------------------"<<endl;
	}*/
	
}
