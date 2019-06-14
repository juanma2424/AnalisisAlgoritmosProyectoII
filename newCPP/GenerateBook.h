#ifndef GENERATEBOOK_H
#define GENERATEBOOK_H
#include <iostream>
#include <stdio.h> 

#include <omp.h>

#include "AES.h"

#include <cassert>
#include <cstring>
#include "FileManagement.h"


using namespace std;


string text;
    string book[3][100];
    const int LENGTH_CONJURO = 200;
    
class GenerateBook {
public:

    GenerateBook(string pText) {
        text = pText;
        makeBook();
    }
    void getBook();
private:

    void makeBook() {
        string conjuro;
        FileManagement file = FileManagement();
        AES aes(256);
#pragma omp parallel
        {
#pragma omp for
            file.CleanFiles();
            file.openWrite();
            for (int i = 0; i < 100; i++) {
                conjuro = text.substr(i*LENGTH_CONJURO, LENGTH_CONJURO);
                file.write(aes.encriptAES((unsigned char*) conjuro.c_str()), "conjuro");
                file.write("X--X", "conjuro");
               // file.write(sha256(conjuro), "conjuro");
                file.write("X-Y-X", "conjuro");
                file.write(aes.getKey(), "save");
            }
            file.write("X-X-X", "save");
            file.write("X-X-X", "conjuro");
            file.write("X-X-X", "conjuro");
            file.closeWrite();
        }
    };
    
};

#endif
