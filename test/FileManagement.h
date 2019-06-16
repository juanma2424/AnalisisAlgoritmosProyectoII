#ifndef FILEMANAGEMENT_H
#define FILEMANAGEMENT_H
#include <iostream>
#include <stdio.h> 
#include <fstream>
#include <sstream>
#include <string>
using namespace std;

class FileManagement
{
	public:
		FileManagement(){
		}
		FileManagement(char pPath[]){
			text = readFile(pPath);
		}
		void write(string text,string pPath);
		string readKey();
		void openWrite();
		void closeWrite();
		void CleanFiles();
		string getText();
		string readBook();
		string readConj();
		void writeBook(string pText);
	private:
		string readFile(char pPath[]);
		string text;
};
ofstream save;
ofstream conjuro;
ofstream pou("test"); 

string FileManagement::readFile(char pPath[])
{
	string line = "";
    ifstream fe(pPath);
    string espacio = "";
    string text = "";
	
	
	while(getline(fe, line)) {
		text = text + espacio + line;
		espacio = " ";
    }
   	fe.close();
   	return text;
}

string FileManagement::readBook()
{
	string textBook = "";
	ifstream in("test");   
  in.read((char *) &textBook, sizeof textBook);   
     
  in.close(); 
  return textBook;
}

string FileManagement::readKey()
{
	string textKey = "";

	string line = "";
    ifstream fe("key.txt");
	
	
	while(getline(fe, line)) {
		textKey = textKey + line;
    }
   	fe.close();
     
  fe.close(); 
  return textKey;
}

string FileManagement::readConj()
{
	string textKey = "";

	string line = "";
    ifstream fe("verConjuro.txt");
	
	
	while(getline(fe, line)) {
		textKey = textKey + line;
    }
   	fe.close();
     
  fe.close(); 
  return textKey;
}

void FileManagement::openWrite(){
	save.open ("Save.txt");
	conjuro.open("Conjuro.txt");
	
}

void FileManagement::CleanFiles(){
	 ofstream clean;
	 clean.open("Save.txt", std::ofstream::out | std::ofstream::trunc);
	 clean.open("Conjuro.txt", std::ofstream::out | std::ofstream::trunc);
	 clean.close();
	
}
void FileManagement::closeWrite(){
	   pou.close();
       save.close();
	   conjuro.close();	
	   
}

void FileManagement::write(string text, string pPath){
	if(pPath=="Conjuro"){
		conjuro << text + "\n";
	}else{
	   save << text + "\n";
	}
}
string FileManagement::getText()
{
	return text;
}


void FileManagement::writeBook(string pText){ 
     
  pou.write((char *) &pText, sizeof pText);   
}

#endif
