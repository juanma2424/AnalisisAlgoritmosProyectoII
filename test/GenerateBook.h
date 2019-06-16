#ifndef GENERATEBOOK_H
#define GENERATEBOOK_H
#include <iostream>
#include <stdio.h> 
#include "Sha256.h"
#include <omp.h>
#include <iostream>
#include <ctime> 
using namespace std;

class GenerateBook
{
	public:
		GenerateBook(string pText){
			text = pText;
		}
		void getBook();
		void makeBook(bool);
		void searchConjuro();
	private:
		string text;
		const int LENGTH_CONJURO = 200;
};

#endif
