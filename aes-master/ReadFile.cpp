// reading a text file
#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <sstream>
#include "structures.h"
#include "encrypt.cpp"
using namespace std;

string conjuros[100];
void read()
{
    string line;
    string str;
    ifstream myfile("test.txt");
    int i = 0;

    if (myfile.is_open())
    {
        while (getline(myfile, line) && i!=100)
        {
            str = str + line;
            istringstream hex_chars_stream(line);
            if (str.length() >= 200)
            {
                conjuros[i] = str;
                i++;
                str = "";
            }
        }
        myfile.close();
        //cout << i ;
    }
    else
        cout << "Unable to open file";
}


int main()
{
    read();
   
    for (int i = 0; i < 101; i++)
    {
       //encryp("aaaa");
       encryp(conjuros[38]);
       break;
    }

    
}