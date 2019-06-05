#include "FileManagement.h"

string FileManagement::readFile(char pPath[])
{
	string line = "";
    ifstream fe(pPath);
    std::stringstream ss;
	
	while(!fe.eof()) {
		fe >> line;
		ss << line;	
	}
	line = ss.str();
   	fe.close();
   	return line;
}

string FileManagement::getText()
{
	return text;
}
