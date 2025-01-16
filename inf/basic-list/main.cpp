#include <iostream>
#include "simple_list.h"

using namespace  std;

int main() {
    Node* lista = nullptr;
    Node** puntatoreLista = &lista;
    insert(puntatoreLista,10);
    insert(puntatoreLista,20);
    print(lista);
    cout<<"La lista contiene "<<size(lista)<<" elementi"<<endl;
}
