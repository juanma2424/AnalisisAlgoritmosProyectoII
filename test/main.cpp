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
	return 0;
}
