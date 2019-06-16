#include "FileManagement.h"
ofstream save;
ofstream conjuro;
ofstream out("test"); 
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
	string text = "";
	ifstream in("test");   
  in.read((char *) &text, sizeof text);   
     
  cout<<"---------------------------------------------------------Texto---------------------------------------------------------\n" ;
    cout << text << " \n";   
    cout<<"---------------------------------------------------------Texto FIn---------------------------------------------------------\n" ;
     
  in.close(); 
  return text;
}

void FileManagement::openWrite(){
	save.open ("Save.txt");
	conjuro.open("Conjuro.txt");
	
}



void FileManagement::readSecret(){
  string line;
  cout<<"1";
  ifstream myfile ("DataPLay.txt");
  if (myfile.is_open())
  {
    while ( getline (myfile,line) )
    {
      cout << line << '\n';
    }
    myfile.close();
  }

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
	   out.close();
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
     
  out.write((char *) &pText, sizeof pText);   
}

