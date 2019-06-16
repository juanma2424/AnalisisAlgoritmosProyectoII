#include "GenerateBook.h"
#include "AES.h"
#include <iostream>
#include <cassert>
#include <cstring>
#include "FileManagement.h"

using namespace std;


void GenerateBook::makeBook(bool entry)
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
        for(int i = 0; i < 100; i++){     
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
	    if(entry)
	    	searchConjuro("sad");
	}
	
}


void GenerateBook::searchConjuro(string pKeys){
	AES aes(256);
	FileManagement files = FileManagement();
	
	unsigned t0, t1;
	t0=clock();
	bool refresh = true;
	while(!ifstream("key.txt")){
		t1 = clock();
		double time = (double(t1-t0)/CLOCKS_PER_SEC);
		if(time >= 30.0 && refresh){
			makeBook(false);
			refresh = false;
		}
	}
	string books = files.readBook();
	string pKey = files.readKey();	
	cout<<"----------------------------Key-------------------------------"<<endl;
	cout<<pKey<<endl;
	cout<<"----------------------------Key Fin-------------------------------"<<endl;
	int start = 0;
	int fin = 0;
	string auxAes = "";
	string auxSha256 = "";
	string AesDecrypted = "";
	for(int index = 0; index<100;index++ ){
		fin = books.find("Y-Y-Y");
		auxAes = books.substr(start,fin);
		cout<<"----------------------------Aes-------------------------------"<<endl;
	cout<<auxAes<<endl;
	cout<<"----------------------------Aes Fin-------------------------------"<<endl;
		books = books.substr(fin+5);
		fin = books.find("Z-Z-Z");
		auxSha256 = books.substr(start,fin);
		books = books.substr(fin+5);
		cout<<"----------------------------Prueba-------------------------------"<<endl;
		AesDecrypted = aes.decryptAes((unsigned char*)auxAes.c_str(),pKey);
		cout<<AesDecrypted<<endl;
		cout<<"----------------------------Prueba Fin-------------------------------"<<endl;
		if(sha256(AesDecrypted) == auxSha256&&refresh){
			cout<<"--------------Encontrado-----------------"<<endl;
			files.openWrite();
			files.write(AesDecrypted,"Conjuro");
			files.closeWrite();
			break;
		}
	}
	if(!refresh){
		cout<<"--------------No encontrado-----------------"<<endl;
		files.openWrite();
		files.write("Game Over","Conjuro");
		files.closeWrite();
	}
}
