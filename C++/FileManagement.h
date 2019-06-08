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
		FileManagement(char pPath[]){
			text = readFile(pPath);
		}
		
		string getText();
	private:
		string readFile(char pPath[]);
		string text;
};

#endif
