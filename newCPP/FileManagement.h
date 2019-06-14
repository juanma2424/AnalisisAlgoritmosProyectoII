#ifndef FILEMANAGEMENT_H
#define FILEMANAGEMENT_H
#include <iostream>
#include <stdio.h> 
#include <fstream>
#include <sstream>
#include <string>
using namespace std;
ofstream save;
ofstream conjuro;

class FileManagement {
public:

    FileManagement() {
    }

    FileManagement(char pPath[]) {
        text = readFile(pPath);
    }

    void write(string text, string pPath) {
        if (pPath == "conjuro") {
            conjuro << text + "\n";
        } else {
            save << text + "\n";
        }
    }

  string getText() {
       return text;
    };

    void openWrite() {
        save.open("Save.txt");
        conjuro.open("Conjuro.txt");
    };

    void closeWrite() {
        save.close();
        conjuro.close();
    };

    void CleanFiles() {
        ofstream clean;
        clean.open("Save.txt", std::ofstream::out | std::ofstream::trunc);
        clean.open("Conjuro.txt", std::ofstream::out | std::ofstream::trunc);
        clean.close();
    };
   

    void readSecret() {
        string line;
        cout << "1";
        ifstream myfile("DataPLay.txt");
        if (myfile.is_open()) {
            while (getline(myfile, line)) {
                cout << line << '\n';
            }
            myfile.close();
        }
    };
private:

    string readFile(char pPath[]) {
        string line = "";
        ifstream fe(pPath);
        std::stringstream ss;

        while (!fe.eof()) {
            fe >> line;
            ss << line;
        }
        line = ss.str();
        fe.close();
        return line;
    };
    string text;
};

#endif
