#include <iostream>
#include "AES.h"
#include <cassert>
#include <cstring>

using namespace std;

void TestECB(unsigned char po[])
{
  AES aes(256);

  unsigned char key[] = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x011,
    0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f };

  unsigned int len = 0;
  unsigned char *out = aes.EncryptECB(po, 208 * sizeof(unsigned char), key, len);
  unsigned char *innew = aes.DecryptECB(out, 208 * sizeof(unsigned char), key, len);
  cout << innew << endl;
  delete[] out;
  delete[] innew;
}




int main()
{
  
  string to = "Mr. and Mrs. Dursley of number four Privet Drive were proud to say that they were perfectly normal thank you very much. They were the last people youd expect to be involved in anything strange or mys.";
  
  cout<<to.size()<<endl;
  
	    	
			TestECB((unsigned char*)to.c_str()); 
			
	cout<<endl;
  return 0;
}
