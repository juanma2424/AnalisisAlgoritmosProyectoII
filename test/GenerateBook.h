#ifndef GENERATEBOOK_H
#define GENERATEBOOK_H
#include <iostream>
#include <stdio.h> 
#include "Sha256.h"
#include <omp.h>
using namespace std;

class GenerateBook
{
	public:
		GenerateBook(string pText){
			text = pText;
			makeBook();
		}
		void getBook();
		void searchConjuro(string pKey);
	private:
		void makeBook();
		string text;
		string book[3][100];
		const int LENGTH_CONJURO = 200;
};

#endif
