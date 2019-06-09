#include "FileManagement.h"
ofstream myfile;
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
void FileManagement::openWrite(){
	myfile.open ("Save.txt");
}

void FileManagement::closeWrite(){
	myfile.close();
}

void FileManagement::write(string text){
  myfile << text + "\n";
}
string FileManagement::getText()
{
	return text;
}
