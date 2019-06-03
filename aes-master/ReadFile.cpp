// reading a text file
#include <iostream>
#include <fstream>
#include <string>
#include <iostream>
#include <cstring>
#include <fstream>
#include <sstream>
using namespace std;

void read()
{
    string line;
    string str;
    ifstream myfile("test.txt");
    string conjuros[100];
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

                // cout << "\n------------------------------------" << '\n';
                // cout << "\n"
                //      << str << '\n';
                // cout << "\n------------------------------------" << '\n';
                str = "";
            }
            //cout << line <<" X"<< '\n';
        }
        myfile.close();
        cout << i ;
        
    }

    else
        cout << "Unable to open file";

}