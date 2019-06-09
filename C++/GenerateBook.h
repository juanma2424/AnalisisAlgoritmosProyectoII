#ifndef GENERATEBOOK_H
#define GENERATEBOOK_H
#include <iostream>
#include <stdio.h> 
#include "Sha256.h"
#include <omp.h>
#include "Decrypt.h"
#include "Encrypt.h"
using namespace std;

class GenerateBook
{
	public:
		GenerateBook(string pText){
			text = pText;
			makeBook();
		}
		void getBook();
	private:
		void makeBook();
		string text;
		string book[2][100];
		static const int LENGTH_CONJURO = 180;
};

#endif
