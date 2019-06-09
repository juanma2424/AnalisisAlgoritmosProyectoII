#include "FileManagement.h"
ofstream save;
ofstream conjuro;
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
       save.close();
	   conjuro.close();	
}

void FileManagement::write(string text, string pPath){
	if(pPath=="save"){
		save << text + "\n";
	}else{
	   conjuro << text + "\n";
	}
}
string FileManagement::getText()
{
	return text;
}
