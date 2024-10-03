#include <iostream>
#include "simple_list.h"

using namespace  std;

void liste_in_cpp() {
    //C++ useremo gli operatori NEW e DELETE per allocare e deallocare memoria
    //dinamicamente
    Node* head = new Node;
    Node* prec = head;
    Node* current;

    //Questo codice crea una lista
    for (int i=0; i<10; i++) {
        current = new Node;
        current->info = i*i;
        prec->next = current;
        prec = current;
    }

    //Questo codice stampa una lista
    int i=0;
    Node* cursor = head->next;
    while(cursor!=NULL) {
        cout <<"Il nodo "<<i<<" contiene il valore "<<cursor->info<<endl ;
        i++;
        cursor = cursor->next;
    }
}

void liste_in_c() {
    //C si usano le funzioni malloc(...) e free(...) per allocare e deallocare memoria
    //dinamicamente


    //Qui è necessario fare un casting perchè la malloc restituisce un puntatore void
    Node* head = (Node* ) malloc(sizeof(node_t));
    Node* prec = head;
    Node* current;

    //Questo codice crea una lista di 10 elementi
    for (int i=0; i<10; i++) {
        current = (Node* ) malloc(sizeof(node_t));
        current->info = i*2;
        current->next = nullptr;
        prec->next = current;
        prec = current;
    }

    //Questo codice stampa una lista
    int i=0;
    Node* cursor = head->next;
    while(cursor!=nullptr) {
        cout <<"Il nodo "<<i<<" contiene il valore "<<cursor->info<<endl ;
        i++;
        cursor = cursor->next;
    }
}

int main() {
    liste_in_cpp();
    liste_in_c();

    Node* lista = new Node;

    insert(lista,10);
    insert(lista,20);
    insert(lista,30);
    print(lista);
    cout<<"La lista contiene "<<size(lista)<<" elementi"<<endl;
    Node* clonata = copy(lista);
    Node* riferimento = lista;

    Node* trovato = nullptr;
    trovato = find(lista, 5);
    if(trovato==nullptr) {
        cout<<"La lista NON contiene 5"<<endl;
    }else {
        cout<<"La lista CONTIENE 5"<<endl;
    }

    append(lista, 5);
    print(lista);
    cout<<"La lista contiene "<<size(lista)<<" elementi"<<endl;
    trovato = find(lista, 5);
    if(trovato!=nullptr) {
        cout<<"La lista NON contiene 5"<<endl;
    }else {
        cout<<"La lista CONTIENE 5"<<endl;
    }


    clear(lista);
    print(lista);
    cout<<"La lista contiene "<<size(lista)<<" elementi"<<endl;


    cout<<"Riferimento non e' una vera copia infatti ora contiene "<<size(riferimento)<<" elementi"<<endl;
    cout<<"Questo invece e' il contenuto della lista CLONATA con copy:";
    print(clonata);
    cout<<"La lista clonata contiene "<<size(clonata)<<" elementi"<<endl;
    trovato = find(clonata, 5);
    if(trovato==nullptr) {
        cout<<"La lista CLONATA NON contiene 5"<<endl;
    }else {
        cout<<"La lista CLONATA CONTIENE 5"<<endl;
    }

}
