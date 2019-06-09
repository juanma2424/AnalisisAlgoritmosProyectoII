#include "FileManagement.h"

string FileManagement::readFile(char pPath[])
{
	string line = "";
    ifstream fe(pPath);
    string ss = "";
	
	
	while(getline(fe, ss)) {
		line = line+ss;
        
    }
   	fe.close();
   	return line;
}

string FileManagement::getText()
{
	return text;
}
