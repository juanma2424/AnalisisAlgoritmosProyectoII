// /**
//   Programa de demostración de cifrado / descifrado AES utilizando OpenSSL EVP apis
//   gcc -Wall openssl_aes.c -lcrypto
//   Este es un código de dominio público. 
//   Saju Pillai ( saju.pillai@gmail.com )
// **/
 
// #include <string.h>
// #include <stdio.h>
// #include <stdlib.h>
// #include <openssl / evp.h>
 
// /**
//  * Cree una clave de 256 bits y IV utilizando los datos clave suministrados. Se puede agregar sal para el gusto.
//  * Rellena los objetos ctx de cifrado y descifrado y devuelve 0 en caso de éxito
//  **/
// int aes_init ( unsigned  char  * key_data, int key_data_len, unsigned  char  * salt, EVP_CIPHER_CTX * e_ctx,
//              EVP_CIPHER_CTX * d_ctx )
// {
//   int i, nrounds =  5 ;
//    clave char sin firma [ 32 ] , iv [ 32 ] ;
 
//   / *
//    * Gen key & IV para el modo AES 256 CBC. Se utiliza un resumen SHA1 para marcar el material de clave suministrado.
//    * nrounds es el número de veces que tenemos el material hash. Más rondas son más seguras pero
//    * más lento.
//    * /
//   i = EVP_BytesToKey ( EVP_aes_256_cbc ( ) , EVP_sha1 ( ) , salt, key_data, key_data_len, nrounds, key, iv ) ;
//   si  ( i ! =  32 )  {
//     printf ( "El tamaño de la clave es% d bits; debe ser 256 bits \ n " , i ) ;
//     retorno  - 1 ;
//   }
 
//   EVP_CIPHER_CTX_init ( e_ctx ) ;
//   EVP_EncryptInit_ex ( e_ctx, EVP_aes_256_cbc ( ) , NULL , clave, iv ) ;
//   EVP_CIPHER_CTX_init ( d_ctx ) ;
//   EVP_DecryptInit_ex ( d_ctx, EVP_aes_256_cbc ( ) , NULL , clave, iv ) ;
 
//   devuelve  0 ;
// }
 
// / *
//  * Cifrar * len bytes de datos
//  * Todos los datos que entran y salen se consideran binarios (caracteres sin signo [])
//  * /
// unsigned  char  * aes_encrypt ( EVP_CIPHER_CTX * e, unsigned  char  * plaintext, int  * len )
// {
//   / * el len máximo del texto cifrado para un byte de texto sin formato es n + AES_BLOCK_SIZE -1 bytes * /
//   int c_len =  * len + AES_BLOCK_SIZE, f_len =  0 ;
//   unsigned  char  * ciphertext =  malloc ( c_len ) ;
 
//   / * permite reutilizar 'e' para múltiples ciclos de encriptación * /
//   EVP_EncryptInit_ex ( e, NULL , NULL , NULL , NULL ) ;
 
//   / * actualizar texto cifrado, c_len se rellena con la longitud del texto cifrado generado,
//     * len es el tamaño del texto plano en bytes * /
//   EVP_EncryptUpdate ( e, texto cifrado, & c_len, texto simple, * len ) ;
 
//   / * actualiza el texto cifrado con los bytes restantes finales * /
//   EVP_EncryptFinal_ex ( e, ciphertext + c_len, & f_len ) ;
 
//   * len = c_len + f_len;
//   devolver texto cifrado;
// }
 
// / *
//  * Descifrar * len bytes de texto cifrado
//  * /
// unsigned  char  * aes_decrypt ( EVP_CIPHER_CTX * e, char sin signo  * texto cifrado, int * len )  
// {
//   / * el texto sin formato siempre será igual o menor que la longitud del texto cifrado * /
//   int p_len =  * len, f_len =  0 ;
//   unsigned  char  * plaintext =  malloc ( p_len ) ;
 
//   EVP_DecryptInit_ex ( e, NULL , NULL , NULL , NULL ) ;
//   EVP_DecryptUpdate ( e, texto plano, & p_len, texto cifrado, * len ) ;
//   EVP_DecryptFinal_ex ( e, texto plano + p_len, & f_len ) ;
 
//   * len = p_len + f_len;
//   devolver texto en claro
// }
 
// int main ( int argc, char  ** argv )
// {
//   / * cifrado "opaco", descifrado de estructuras ctx que libcrypto utiliza para grabar
//      estado de las operaciones enc / dec * /
//   EVP_CIPHER_CTX en, de;
 
//   / * 8 bytes para saltear los datos clave durante la generación de claves. Esto es un ejemplo de
//      Compilado en sal. Acabamos de leer el patrón de bits creado por estos dos 4 bytes.
//      enteros en la pila como 64 bits de material de sal contiguo - 
//      Por supuesto, esto solo funciona si sizeof (int)> = 4 * /
//    sal int sin firmar [ ]  =  { 12345 , 54321 } ;
//   unsigned  char  * key_data;
//   int key_data_len, i;
//   char  * input [ ]  =  { "a" , "abcd" , "esto es una prueba" , "esta es una prueba más grande" ,
//                    " \ n ¿Quién eres? \ n Soy el 'Doctor'. \ n 'Doctor' ¿quién? \ n ¡Precisamente!" ,
//                    NULL } ;
 
//   / * los datos clave se leen de la lista de argumentos * /
//   key_data =  ( unsigned  char  * ) argv [ 1 ] ;
//   key_data_len =  strlen ( argv [ 1 ] ) ;
 
//   / * gen clave y iv. Inicia el objeto ctx cifrado * /
//   if  ( aes_init ( key_data, key_data_len, ( unsigned  char  * ) & salt, & en, & de ) )  {
//     printf ( "No se pudo inicializar el cifrado AES \ n " ) ;
//     retorno  - 1 ;
//   }
 
//   / * cifrar y descifrar cada cadena de entrada y comparar con el original * /
//   para  ( i =  0 ; entrada [ i ] ; i ++ )  {
//     char  * plaintext;
//     unsigned  char  * ciphertext;
//     int olen, len;
 
//     / * Las funciones enc / dec tratan con datos binarios y no con cadenas C. strlen () será
//        devuelve la longitud de la cadena sin contar el marcador de cadena '\ 0'. Nosotros siempre
//        pase el byte marcador a las funciones de cifrado / descifrado para que después del descifrado 
//        terminamos con una cadena C legal * /
//     olen = len =  strlen ( entrada [ i ] ) + 1 ;
 
//     ciphertext = aes_encrypt ( & en, ( unsigned  char  * ) input [ i ] , & len ) ;
//     plaintext =  ( char  * ) aes_decrypt ( & de, ciphertext, & len ) ;
 
//     if  ( strncmp ( texto plano, entrada [ i ] , olen ) ) 
//       printf ( "FAIL: enc / dec falló para \" % s \ " \ n " , entrada [ i ] ) ;
//     más 
//       printf ( "OK: enc / dec ok para \" % s \ " \ n " , texto plano ) ;
 
//     libre ( texto cifrado ) ;
//     libre ( texto plano ) ;
//   }
 
//   EVP_CIPHER_CTX_cleanup ( & en ) ;
//   EVP_CIPHER_CTX_cleanup ( & de ) ;
 
//   devuelve  0 ;
// }

// #include <./openssl/aes.h>
// #include <./openssl/evp.h>
// #include <./openssl/rand.h>
// #include <string>
// #include <sstream>
// #include <vector>
// #include <iostream>
 
// using std::string;
// using std::vector;
// using std::cout;
// using std::endl;
 
// void aes_init()
// {
//     static int init=0;
//     if (init==0)
//     {
//         EVP_CIPHER_CTX e_ctx, d_ctx;
 
//         //initialize openssl ciphers
//         OpenSSL_add_all_ciphers();
 
//         //initialize random number generator (for IVs)
//         int rv = RAND_load_file("/dev/urandom", 32);
//     }
// }
 
// std::vector<unsigned char> aes_128_gcm_encrypt(std::string plaintext, std::string key)
// {
//     aes_init();
 
//     size_t enc_length = plaintext.length()*3;
//     std::vector<unsigned char> output;
//     output.resize(enc_length,'\0');
 
//     unsigned char tag[AES_BLOCK_SIZE];
//     unsigned char iv[AES_BLOCK_SIZE];
//     RAND_bytes(iv, sizeof(iv));
//     std::copy( iv, iv+16, output.begin()+16);
 
//     int actual_size=0, final_size=0;
//     EVP_CIPHER_CTX* e_ctx = EVP_CIPHER_CTX_new();
//     //EVP_CIPHER_CTX_ctrl(e_ctx, EVP_CTRL_GCM_SET_IVLEN, 16, NULL);
//     EVP_EncryptInit(e_ctx, EVP_aes_128_gcm(), (const unsigned char*)key.c_str(), iv);
//     EVP_EncryptUpdate(e_ctx, &output[32], &actual_size, (const unsigned char*)plaintext.data(), plaintext.length() );
//     EVP_EncryptFinal(e_ctx, &output[32+actual_size], &final_size);
//     EVP_CIPHER_CTX_ctrl(e_ctx, EVP_CTRL_GCM_GET_TAG, 16, tag);
//     std::copy( tag, tag+16, output.begin() );
//     std::copy( iv, iv+16, output.begin()+16);
//     output.resize(32 + actual_size+final_size);
//     EVP_CIPHER_CTX_free(e_ctx);
//     return output;
// }
 
// std::string aes_128_gcm_decrypt(std::vector<unsigned char> ciphertext, std::string key)
// {
//     aes_init();
 
//     unsigned char tag[AES_BLOCK_SIZE];
//     unsigned char iv[AES_BLOCK_SIZE];
//     std::copy( ciphertext.begin(),    ciphertext.begin()+16, tag);
//     std::copy( ciphertext.begin()+16, ciphertext.begin()+32, iv);
//     std::vector<unsigned char> plaintext; plaintext.resize(ciphertext.size(), '\0');
 
//     int actual_size=0, final_size=0;
//     EVP_CIPHER_CTX *d_ctx = EVP_CIPHER_CTX_new();
//     EVP_DecryptInit(d_ctx, EVP_aes_128_gcm(), (const unsigned char*)key.c_str(), iv);
//     EVP_DecryptUpdate(d_ctx, &plaintext[0], &actual_size, &ciphertext[32], ciphertext.size()-32 );
//     EVP_CIPHER_CTX_ctrl(d_ctx, EVP_CTRL_GCM_SET_TAG, 16, tag);
//     EVP_DecryptFinal(d_ctx, &plaintext[actual_size], &final_size);
//     EVP_CIPHER_CTX_free(d_ctx);
//     plaintext.resize(actual_size + final_size, '\0');
 
//     return string(plaintext.begin(),plaintext.end());
// }
 
// int main(int argc, char **argv)
// {
//     aes_init();
 
//     //create a sample key
//     unsigned char key_bytes[16];
//     RAND_bytes(key_bytes, sizeof(key_bytes));
//     string key = string((char *)key_bytes, sizeof(key_bytes));
 
//     //text to encrypt
//     string plaintext= "elephants in space";
//     cout << plaintext << endl;
 
//     //encrypt
//     vector<unsigned char> ciphertext = aes_128_gcm_encrypt(plaintext, key);
 
//     //output
//     static const char *chars="0123456789ABCDEF";
//     for(int i=0; i<ciphertext.size(); i++)
//     {
//         cout << chars[ciphertext[i]/16];
//         cout << chars[ciphertext[i]%16];
//     }
//     cout << endl;
 
//     //decrypt
//     string out = aes_128_gcm_decrypt(ciphertext, key);
//     cout << out << endl;
// }

#include <conf.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <string.h>

void handleErrors(void)
{
    ERR_print_errors_fp(stderr);
    abort();
}



int encrypt(unsigned char *plaintext, int plaintext_len, unsigned char *key,
            unsigned char *iv, unsigned char *ciphertext)
{
    EVP_CIPHER_CTX *ctx;

    int len;

    int ciphertext_len;

    /* Create and initialise the context */
    if(!(ctx = EVP_CIPHER_CTX_new()))
        handleErrors();

    /*
     * Initialise the encryption operation. IMPORTANT - ensure you use a key
     * and IV size appropriate for your cipher
     * In this example we are using 256 bit AES (i.e. a 256 bit key). The
     * IV size for *most* modes is the same as the block size. For AES this
     * is 128 bits
     */
    if(1 != EVP_EncryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv))
        handleErrors();

    /*
     * Provide the message to be encrypted, and obtain the encrypted output.
     * EVP_EncryptUpdate can be called multiple times if necessary
     */
    if(1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len))
        handleErrors();
    ciphertext_len = len;

    /*
     * Finalise the encryption. Further ciphertext bytes may be written at
     * this stage.
     */
    if(1 != EVP_EncryptFinal_ex(ctx, ciphertext + len, &len))
        handleErrors();
    ciphertext_len += len;

    /* Clean up */
    EVP_CIPHER_CTX_free(ctx);

    return ciphertext_len;
}





int decrypt(unsigned char *ciphertext, int ciphertext_len, unsigned char *key,
            unsigned char *iv, unsigned char *plaintext)
{
    EVP_CIPHER_CTX *ctx;

    int len;

    int plaintext_len;

    /* Create and initialise the context */
    if(!(ctx = EVP_CIPHER_CTX_new()))
        handleErrors();

    /*
     * Initialise the decryption operation. IMPORTANT - ensure you use a key
     * and IV size appropriate for your cipher
     * In this example we are using 256 bit AES (i.e. a 256 bit key). The
     * IV size for *most* modes is the same as the block size. For AES this
     * is 128 bits
     */
    if(1 != EVP_DecryptInit_ex(ctx, EVP_aes_256_cbc(), NULL, key, iv))
        handleErrors();

    /*
     * Provide the message to be decrypted, and obtain the plaintext output.
     * EVP_DecryptUpdate can be called multiple times if necessary.
     */
    if(1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, ciphertext_len))
        handleErrors();
    plaintext_len = len;

    /*
     * Finalise the decryption. Further plaintext bytes may be written at
     * this stage.
     */
    if(1 != EVP_DecryptFinal_ex(ctx, plaintext + len, &len))
        handleErrors();
    plaintext_len += len;

    /* Clean up */
    EVP_CIPHER_CTX_free(ctx);

    return plaintext_len;
}








































int main (void)
{
    /*
     * Set up the key and iv. Do I need to say to not hard code these in a
     * real application? :-)
     */

    /* A 256 bit key */
    unsigned char *key = (unsigned char *)"01234567890123456789012345678901";

    /* A 128 bit IV */
    unsigned char *iv = (unsigned char *)"0123456789012345";

    /* Message to be encrypted */
    unsigned char *plaintext =
        (unsigned char *)"The quick brown fox jumps over the lazy dog";

    /*
     * Buffer for ciphertext. Ensure the buffer is long enough for the
     * ciphertext which may be longer than the plaintext, depending on the
     * algorithm and mode.
     */
    unsigned char ciphertext[128];

    /* Buffer for the decrypted text */
    unsigned char decryptedtext[128];

    int decryptedtext_len, ciphertext_len;

    /* Encrypt the plaintext */
    ciphertext_len = encrypt (plaintext, strlen ((char *)plaintext), key, iv,
                              ciphertext);

    /* Do something useful with the ciphertext here */
    printf("Ciphertext is:\n");
    BIO_dump_fp (stdout, (const char *)ciphertext, ciphertext_len);

    /* Decrypt the ciphertext */
    decryptedtext_len = decrypt(ciphertext, ciphertext_len, key, iv,
                                decryptedtext);

    /* Add a NULL terminator. We are expecting printable text */
    decryptedtext[decryptedtext_len] = '\0';

    /* Show the decrypted text */
    printf("Decrypted text is:\n");
    printf("%s\n", decryptedtext);


    return 0;
}