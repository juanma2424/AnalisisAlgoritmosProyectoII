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
		void writeBook(string pText);
	private:
		string readFile(char pPath[]);
		string text;
};

#endif
