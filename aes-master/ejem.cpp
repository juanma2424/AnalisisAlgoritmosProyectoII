// #include "iostream"
// #include "string"

// using namespace std;

// int main()
// {
//     string salida1 = "Ejemplo de salida"; //El valor de esta variable se mostrará en pantalla
//     int numero = 2; //Este valor también se mostrará en pantalla.
//     string salida2 = "Desde AAP."; //Estos valores se concatenarán en una única salida

//     //Se concatenan y muestran los valores por pantalla con cout<<
//     cout << salida1 << " " << numero << ". " << salida2 << endl;

//    //return 0;
// }

#include "iostream"
#include "string"

using namespace std;

int main()
{
    // //Se muestra un mensaje por pantalla.
    // cout << "Hola Mundo Desde AAP." << endl;
    // string salida1 = "Ejemplo de salida"; //El valor de esta variable se mostrará en pantalla
    // int numero = 2; //Este valor también se mostrará en pantalla.
    // string salida2 = "Desde AAP."; //Estos valores se concatenarán en una única salida

    //  //Se concatenan y muestran los valores por pantalla con cout<<
    //   cout << salida1 << " " << numero << ". " << salida2 << endl;

    //    //return 0;

    cout << "Hola! Este es un ejemplo en C++"
         << "\n"
         << "Por favor ingrese su nombre:" << endl; //La instrucción \n es un salto de línea Mostrando los textos separados

    string nombre; //En esta variable estará almacenado el nombre ingresado.
    nombre = "holaaaaaaaaaaaaaaaaa"; //Se lee el nombre

    cout << "Bienvenido al sistema " << nombre << ". Gracias por usar nuestra aplicación" << endl;

    return 0;
}