//
// Created by stefy on 02/10/2024.
//

#ifndef SIMPLE_LIST_H
#define SIMPLE_LIST_H
#include <wchar.h>

typedef struct Node {
 //Questa struttura è la struttura di un Nodo per una LISTA SEMPLICE
 int info;
 Node* next = nullptr; //Mi assicuro che tutti i nodi creati sono inizializzati next = NULL
} node_t;

//Dichiarazione del Nodo che comporrà la mia lista
typedef struct BiNode {
 int info;
 BiNode *prev;
 BiNode *next;
} BiNode_t;

//Dichiarazione del tipo che identifica la Testa di una lista
typedef struct Superhead {
 BiNode *head;
 BiNode *tail;
} Superhead_t;


/**
 * Inserimento in testa ad una lista
 */
void insert(Superhead_t& lista, int value);

/**
 * Inserimento in coda ad una lista
 */
void append(Superhead_t& lista, int value);

/**
 * Inserimento in coda ad una lista prima della posizione specificata restituisce false se la dimensione
 * della lista è troppo piccola
 */
bool insert(Node* head, int pos, int value);

/**
 * Stampa il contenuto di una lista dal primo all'ultimo
 */
void print(Superhead_t lista);

/**
 * Stampa il contenuto di una lista dall'ultimo al primo
 */
void print_reverse(Superhead_t lista);


/**
 * Restituisce la dimensione della lista
 */
int size(Superhead_t head);

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

/**
 * Rimuove, se esiste, l'elemento in prima posizione dalla lista e lo restituisce altrimenti nullptr
 */
Node* remove_first(Node* head);

/**
 * Rimuove, se esiste, l'elemento in ultima posizione dalla lista e lo restituisce altrimenti nullptr
 */
Node* remove_last(Node* head);

/**
 * Rimuove, se esiste, l'elemento di posizione pos dalla lista e lo restituisce altrimenti nullptr
 */
BiNode* remove(Superhead_t& lista, int pos);

/**
 * Aggiunge a tutti i nodi della lista il valore value
 */
void add(Node* head,int value);

/**
 * Rimuove dalla lista src gli elementi della lista diff
 * (questa implementazione richiede scelte progettuali)
 */
void list_diff(Node* src, Node* diff);
#endif //SIMPLE_LIST_H
