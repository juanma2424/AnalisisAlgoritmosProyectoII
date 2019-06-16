
#include <iostream>
#include <stdio.h> 
#include "FileManagement.h"
#include "GenerateBook.h"


using namespace std;
/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main() {
	FileManagement fileM = FileManagement("Texto.txt"); 
	GenerateBook geneB = GenerateBook(fileM.getText());
	geneB.makeBook();
	//geneB.searchConjuro("79,505,372,538,207,762,516,396,1000,502,743,183,319,865,999,529,1033,980,865,474,429,642,940,191,330,420,459,754,756,933,485,764");
	return 0;
}
