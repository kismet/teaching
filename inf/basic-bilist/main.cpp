#include <iostream>
#include "bilist.h"

using namespace  std;

int main() {
    Superhead_t lista;
    lista.head = nullptr;
    lista.tail = nullptr;
    insert(lista,10);
    insert(lista,20);
    append(lista, 50);
    append(lista, 150);
    print(lista);
    cout<<"La lista contiene "<<size(lista)<<" elementi"<<endl;

    Superhead_t cuore;
    cuore.head = nullptr;
    cuore.tail = nullptr;
    insert(cuore, 5);
    insert(cuore, 20);
    insert(cuore, 11);

    print(cuore);
    print_reverse(cuore);

    remove(cuore, 1);
    print(cuore);
    print_reverse(cuore);

    remove(cuore, 0);
    print(cuore);
    print_reverse(cuore);

    remove(cuore, 0);
    print(cuore);
    print_reverse(cuore);

}

