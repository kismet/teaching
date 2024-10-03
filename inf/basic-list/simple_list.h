//
// Created by stefy on 02/10/2024.
//

#ifndef SIMPLE_LIST_H
#define SIMPLE_LIST_H
#include <wchar.h>

//Dichiarazione del Nodo che comporrà la mia lista
typedef struct Node {
 //Questa struttura è la struttura di un Nodo per una LISTA SEMPLICE
 int info;
 Node* next = nullptr; //Mi assicuro che tutti i nodi creati sono inizializzati next = NULL
} node_t;

/**
 * Inserimento in testa ad una lista
 */
void insert(Node* head, int value);

/**
 * Inserimento in coda ad una lista
 */
void append(Node* head, int value);

/**
 * Inserimento in coda ad una lista prima della posizione specificata restituisce false se la dimensione
 * della lista è troppo piccola
 */
bool insert(Node* head, int pos, int value);

/**
 * Stampa il contenuto di una lista
 */
void print(Node* head);

/**
 * Restituisce la dimensione della lista
 */
int size(Node* head);

/**
 * Inverte l'ordinamento della lista
 */
void reverse(Node* head);

/**
 * Cancella tutti gli elementi della lista
 */
void clear(Node* head);

/**
 * Crea una copia della lista
 */
Node* copy(Node* head);

/**
 * Cerca un elemento nella lista e restituisce il nodo a cui appartiene
 */
Node* find(Node* head, int value);

/**
 * Cerca e rimuove la prima occorrenza di un elemento dalla lista, se non lo trova restituisce NULL
 */
Node* remove_value(Node* head, int value);

/**
 * Rimuove l'elemento di posizione pos dalla lista, se non lo trova restituisce NULL
 */
Node* remove_node(Node* head, int pos);

/**
 * Confronta due liste left e right e restituisce ZERO se le liste sono uguali, un valore
 * poistivo se left è più grande di right, o un valore negativo nel caso constrario
 */
int confronta(Node* left, Node* right);

/**
 * Restituisce vero se e solo se la lista left contiene la lista lista right, cioè se
 * condividono gli stessi nodi in memoria
 */
bool contain(Node* left, Node* right);

/**
 * Ordina la lista in modo crescente
 */
void order(Node* head);


/**
 * Toglie gli elementi duplicati dalla lista
 */
void unique(Node* head);

/**
 * Crea una copia della lista eliminando gli elementi duplicati
 */
Node* listset(Node* head);

/**
 * Crea una copia della lista che contiene gli elementi unici di entrambe le liste a e b
 */
Node* listset_union(Node* a,Node* b);

/**
 * Crea una copia della lista eliminando gli elementi b contenuti in a
 */
Node* listset_diff(Node* a, Node* b);

/**
 * Unisce due liste ordinate creando una lista ordina nuova
 */
Node* merge(Node* left,Node* right);

/**
 * Aggiunge in fondo una lista ad un lista
 */
void append_list(Node* head, Node* list);

/**
 * Esegue passa-avanti in una lista
 */
void next(Node* head);

/**
 * Esegue passa-indietro in una lista
 */
void prev(Node* head);

/**
 * Crea una copia di una parte della lista da start ad end (escluso) con passo step
 * (simile alla slice di Python)
 */
Node* slice(Node* head, int start, int end,int step);


#endif //SIMPLE_LIST_H
