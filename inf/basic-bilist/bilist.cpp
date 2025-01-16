#include <iostream>
#include "bilist.h"
using namespace std;

BiNode* remove(Superhead_t& lista, int pos) {
    // res rappresenta il nodo tolto dalla lista, che assumiamo che NON ESISTA
    BiNode* res = nullptr;

    // Caso lista vuota
    if(lista.head == nullptr) {
        return res;
    }

    // Caso lista con 1 elemento
    if(lista.head == lista.tail) {
        if(pos == 0) {
            // Voglio restituire l'unico nodo presente in lista
            res = lista.head;
            lista.head = nullptr;
            lista.tail = nullptr;
        }
        // RES è diverso da nullptr solo se il riga #25 era vero
        return res;
    }

    // Caso lista generale (2 o più elementi)
    BiNode* cursor = lista.head;
    // Scorro fino a quando non trovo la posizione di interesse oppure non arrivo
    // in fondo alla lista
    while(cursor->next != nullptr && pos != 0) {
        cursor = cursor->next;
        pos--;
    }

    if(pos != 0) { // Se vero, vuol dire che pos era maggiore di size(lista)
        return res;
    }

    // Se pos è ZERO vuol dire che sto puntando a quello da rimuovere
    res = cursor;
    // Attacco il precedente al nuovo successivo
    cursor->prev->next = res->next;
    // Attacco il prossimo al nuovo prossimo
    cursor->next->prev = res->prev;
    return res;
}

void append(Superhead_t& lista, int value) {
    BiNode* nodo = new BiNode;
    nodo->info = value;
    nodo->next = nullptr;
    nodo->prev = nullptr;
    if(lista.head == nullptr) {
        lista.head = nodo;
        lista.tail = nodo;
    }else {
        lista.tail->next = nodo;
        nodo->prev = lista.tail;
        lista.tail = nodo;
    }
}


void insert(Superhead_t& lista, int value) {
    BiNode* nodo = new BiNode;
    nodo->info = value;
    nodo->next = nullptr;
    nodo->prev = nullptr;
    if(lista.head == nullptr) {
        lista.head = nodo;
        lista.tail = nodo;
    } else {
        // Aggiorno la coda in maniera "furba"
        lista.head->prev = nodo;
        // Aggiorno la lista vista dalla testa
        nodo->next = lista.head;
        lista.head = nodo;
    }
}

void print(Superhead_t lista) {
    while(lista.head != nullptr) {
        cout << lista.head->info << " ";
        lista.head = lista.head->next;
    }
    cout << endl;
}

void print_reverse(Superhead_t lista) {
    while(lista.tail != nullptr) {
        cout << lista.tail->info << " ";
        lista.tail = lista.tail->prev;
    }
    cout << endl;
}

int size(Superhead_t lista) {
    int c=0;
    while(lista.head != nullptr) {
        c++;
        lista.head = lista.head->next;
    }
    return c;
}