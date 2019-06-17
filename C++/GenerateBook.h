#ifndef GENERATEBOOK_H
#define GENERATEBOOK_H
#include <iostream>
#include <stdio.h> 
#include <omp.h>
#include <ctime> 
#include <cassert>
#include <cstring>
#include "Sha256.h"
#include "AES.h"
#include "FileManagement.h"



using namespace std;

class GenerateBook
{
	public:
		GenerateBook(string pText){
			text = pText;
		}
		void getBook();
		void makeBook(bool);
		void searchConjuro();
		void comparateDigest();
	private:
		string text;
		string auxBook;
		const int LENGTH_CONJURO = 200;
};




void GenerateBook::makeBook(bool entry)
{
	string conjuro;
	FileManagement file = FileManagement();
	 AES aes(256);
		file.CleanFiles(); 
        file.openWrite();
        string padre = "";
        for(int i = 0; i < 100; i++){     
	    	conjuro = text.substr(i*LENGTH_CONJURO,LENGTH_CONJURO);
	    	string conjEncrypted = aes.encriptAES((unsigned char*)conjuro.c_str());
			padre+=conjEncrypted;
			padre+="Y-Y-Y";
			padre+=sha256(conjuro);
			padre+="Z-Z-Z";
	    	file.write(aes.getKey(),"save"); 
	    }
		file.writeBook(padre);	
	    file.closeWrite();
	    if(entry){
	    	searchConjuro();
	    	comparateDigest();
	    }
	
}


void GenerateBook::searchConjuro(){
	AES aes(256);
	FileManagement files = FileManagement();
	
	unsigned t0, t1;
	t0=clock();
	bool refresh = true;
	while(!ifstream("key.txt")){
		t1 = clock();
		double time = (double(t1-t0)/CLOCKS_PER_SEC);
		if(time >= 720.0 && refresh){
			makeBook(false);
			refresh = false;
		}
	}
	string books = files.readBook();
	auxBook = books;
	string pKey = files.readKey();	
	int start = 0;
	int fin = 0;
	string auxAes = "";
	string auxSha256 = "";
	string AesDecrypted = "";
	#pragma omp parallel for
	for(int index = 0; index<100;index++ ){
		fin = books.find("Y-Y-Y");
		auxAes = books.substr(start,fin);
		books = books.substr(fin+5);
		fin = books.find("Z-Z-Z");
		auxSha256 = books.substr(start,fin);
		books = books.substr(fin+5);
		AesDecrypted = aes.decryptAes((unsigned char*)auxAes.c_str(),pKey);
		if(sha256(AesDecrypted) == auxSha256&&refresh){
			files.openWrite();
			files.write(AesDecrypted,"Conjuro");
			files.closeWrite();
		}
	}
	if(!refresh){
		files.openWrite();
		files.write("Game Over","Conjuro");
		files.closeWrite();
	}
	remove("key.txt");
	files.writeBook(auxBook);
}


void GenerateBook::comparateDigest(){
	AES aes(256);
	FileManagement files = FileManagement();
	bool refresh = true;
	while(!ifstream("verConjuro.txt")){
	}
	string books = files.readBook();
	string conjuroEn = files.readConj();	
	int start = 0;
	int fin = 0;
	string conjuroSha = sha256(conjuroEn);
	string auxSha256 = "";
	#pragma omp parallel for
	for(int index = 0; index<100;index++ ){
		fin = books.find("Y-Y-Y");
		books = books.substr(fin+5);
		fin = books.find("Z-Z-Z");
		auxSha256 = books.substr(start,fin);
		books = books.substr(fin+5);
		if(conjuroSha == auxSha256){
			files.openWrite();
			files.write("El conjuro si es el correcto","Conjuro");
			files.closeWrite();
			refresh = false;
		}
	}
	if(refresh){
		files.openWrite();
		files.write("No encontrado","Conjuro");
		files.closeWrite();
	}
	remove("verConjuro.txt");
}
#endif
