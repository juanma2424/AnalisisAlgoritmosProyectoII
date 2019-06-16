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
	string line = "";
    ifstream fe("Conjuro.txt");
    string text = "";
	int i = 0;
	
	while(getline(fe, line) ){
		text = text + line;
		cout<<line<<endl;
    }
   	fe.close();
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
void FileManagement::m(string text){
  
       
  if(!out) {   
    cout << "Cannot open file";   
    //return 1;   
  }   
     
  out.write((char *) &text, sizeof text);   
     
     
     
//  for(i=0; i<5; i++) 
//    n[i] = "aja";   
       
     
 // return 0;   
}

void FileManagement::b(){
	string n = "";
	ifstream in("test");   
  in.read((char *) &n, sizeof n);   
     
  //for(i=0; i<5; i++) // show values read from file 
  cout<<"---------------------------------------------------------Texto---------------------------------------------------------\n" ;
    cout << n << " \n";   
    cout<<"---------------------------------------------------------Texto FIn---------------------------------------------------------\n" ;
     
  in.close(); 
}
